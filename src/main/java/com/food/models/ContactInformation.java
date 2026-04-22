package com.food.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

// Fixed: was missing @Embeddable — required because Restaurant uses @Embedded
@Embeddable
@Data
public class ContactInformation {

	private String email;
	private String mobile;
	private String twitter;
	private String instagram;
}
