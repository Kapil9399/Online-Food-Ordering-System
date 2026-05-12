package com.food.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	private Long price;
	
	@ManyToOne//	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Category foodCategory;

	/*@Column(length=1000)
	@ElementCollection
	private List<String> images;
	*/

	@ElementCollection
	@CollectionTable(name = "food_images", joinColumns = @JoinColumn(name = "food_id"))
	@Column(name = "image_url", columnDefinition = "TEXT")
	private List<String> images;

	private boolean available;
	
	@ManyToOne
	private Restaurant restaurant;
	
	private boolean isVegetarian;
	
	private boolean isSeasonal;
	
	@ManyToMany
	private List<IngredientsItem> ingredients = new ArrayList<>();
	
	private Date creationDate;
}
