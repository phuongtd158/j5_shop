package com.poly.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {

    private Integer id;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String fullname;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

//	@NotNull
//	@NotBlank
//	private String passwordConfirm;

    private String photo;

    @NotNull
    private Integer admin;

    @NotNull
    private Integer activated;
}
