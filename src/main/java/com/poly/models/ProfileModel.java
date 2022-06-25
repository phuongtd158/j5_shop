package com.poly.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {
	private Integer id;

	@NotNull(message = "Username không được để trống")
	@NotBlank(message = "Username không được để trống")
	@Length(max = 255, message = "Không được nhập quá 255 ký tự")
	private String username;

	@NotNull(message = "Họ tên không được để trống")
	@NotBlank(message = "Họ tên không được để trống")
	@Length(max = 255, message = "Không được nhập quá 255 ký tự")
	private String fullname;

	@NotNull(message = "Email không được để trống")
	@NotBlank(message = "Email không được để trống")
	@Email(message = "Email phải đúng định dạng")
	@Length(max = 255, message = "Không được nhập quá 255 ký tự")
	private String email;

	private String photo;

	private MultipartFile imageFile;
}
