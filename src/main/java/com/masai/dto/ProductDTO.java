package com.masai.dto;



import lombok.Data;

@Data
public class ProductDTO {

	private Long id;
	private String name;
	private Double price;
	private String description;
	private Double weight;
	private String imageName;
	private Integer categoryId;
	
}
