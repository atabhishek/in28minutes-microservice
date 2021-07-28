package com.in28Minutes.microservices.limitmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Limit {
	
	private int minimum;
	private int maximum;

}
