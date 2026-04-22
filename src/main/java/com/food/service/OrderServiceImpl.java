package com.food.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.models.Address;
import com.food.models.Cart;
import com.food.models.CartItem;
import com.food.models.Order;
import com.food.models.OrderItem;
import com.food.models.Restaurant;
import com.food.models.User;
import com.food.repository.AddressRepository;
import com.food.repository.OrderRepository;
import com.food.repository.OrderitemRepository;
import com.food.repository.UserRepository;
import com.food.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderitemRepository orderitemRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private CartService cartService;

	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {

		Address shippAddress = order.getDeliveryAddress();
		Address savedAddress = addressRepository.save(shippAddress);

		if (!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}

		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

		Order createdOrder = new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreateAt(new Date());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setDeliveryAddress(savedAddress);
		createdOrder.setRestaurant(restaurant);

		Cart cart = cartService.findCartByUserId(user.getId());

		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem cartItem : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());

			OrderItem savedOrderItem = orderitemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}

		Long totalPrice = cartService.calculateCartTotals(cart);

		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(totalPrice);
		createdOrder.setTotalAmount(totalPrice);
		createdOrder.setTotalItem(orderItems.size());

		Order savedOrder = orderRepository.save(createdOrder);
		restaurant.getOrders().add(savedOrder);

		return savedOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {

		Order order = findOrderById(orderId);

		List<String> validStatuses = List.of(
				"PENDING", "CONFIRMED", "PREPARING",
				"OUT_FOR_DELIVERY", "DELIVERED", "COMPLETED", "CANCELLED"
		);

		if (validStatuses.contains(orderStatus)) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}

		throw new Exception("Please select a valid order status. Valid: " + validStatuses);
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);

		if (orderStatus != null && !orderStatus.isEmpty()) {
			orders = orders.stream()
					.filter(order -> order.getOrderStatus().equals(orderStatus))
					.collect(Collectors.toList());
		}
		return orders;
	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if (optionalOrder.isEmpty()) {
			throw new Exception("Order not found with id: " + orderId);
		}
		return optionalOrder.get();
	}
}
