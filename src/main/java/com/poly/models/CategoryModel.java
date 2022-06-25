package com.poly.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
	private int id;

	@NotNull(message = "Không được để trống tên danh mục")
	@NotBlank(message = "Không được để trống tên danh mục")
	@Length(max = 255, message = "Không được nhập quá 255 ký tự")
	private String name;
}
