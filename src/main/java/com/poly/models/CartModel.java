package com.poly.models;

import java.io.Serializable;

import com.poly.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartModel implements Serializable{
	private Product product;
	private int quantity;
}
