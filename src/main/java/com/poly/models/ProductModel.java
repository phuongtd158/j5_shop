package com.poly.models;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.poly.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private int id;

    @NotNull(message = "Không được để trống tên")
    @NotBlank(message = "Không được để trống tên")
    @Length(max = 255, message = "Không được nhập quá 255 ký tự")
    private String name;

    private String image;

    @NotNull(message = "Không được để trống giá")
    @Min(value = 0, message = "Giá phải lớn hơn 0")
    private double price;

    private Date createDate;

    @NotNull
    private int available;

    @NotNull(message = "Không được để trống danh mục")
    private Category categoryById;
    
    private MultipartFile imageFile;
}
