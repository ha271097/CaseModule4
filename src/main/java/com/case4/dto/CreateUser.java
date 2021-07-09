package com.case4.dto;

import com.case4.model.Role;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CreateUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Vui lòng nhập user của bạn!")
    @Pattern(regexp = "^[\\D]+", message = "Hãy đảm bảo bạn nhập đúng tên!")
    private String name;

    @NotNull(message = "Vui lòng nhập email của bạn!")
    @Email(message = "Không đúng định dạng email!")
    private String email;

    @NotNull(message = "Vui lòng nhập tên tài khoản của bạn!")
    @Pattern(regexp = "^[a-z0-9]{6,15}", message = "Nhập chữ cái thường hoặc số từ 6 đến 15 kí tự!")
    private String username;

    @NotNull(message = "Vui lòng nhập mật khẩu của bạn!")
    @Pattern(regexp = "^[a-z0-9]{6,15}", message = "Nhập chữ cái thường hoặc số từ 6 đến 15 kí tự!")
    private String password;

}
