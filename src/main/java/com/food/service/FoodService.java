package com.food.service;

import java.util.List;

import com.food.models.Category;
import com.food.models.Food;
import com.food.models.Restaurant;
import com.food.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest req,Category category, Restaurant restaurant);

	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood(Long restaurantId,
										boolean isVegetarian, 
										boolean isNonveg, 
										boolean isSeasonal, 
										String foodCategory
										);
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvailabilityStatus(Long foodId) throws Exception;
	
}
