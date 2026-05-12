package com.food.dto;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class RestaurantDto {

	private String title;
	
	//@Column(length=1000)
	//private List<String> images;

	@Column(columnDefinition = "TEXT")   // ✅ fixes the truncation
	private String images;

	/*@ElementCollection
	@CollectionTable(name = "restaurant_dto_images", joinColumns = @JoinColumn(name = "restaurant_dto_id"))
	@Column(name = "image_url", columnDefinition = "TEXT")
	private List<String> images;*/

	private String description;
	
	private Long id;
}
