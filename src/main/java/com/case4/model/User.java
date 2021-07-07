package com.case4.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "userName"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
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
    @Pattern(regexp = "^[a-z0-9]{4,15}", message = "Nhập chữ cái thường hoặc số từ 4 đến 15 kí tự!")
    private String username;

    @NotNull(message = "Vui lòng nhập mật khẩu của bạn!")
    @Pattern(regexp = "^[a-z0-9]{6,15}", message = "Nhập chữ cái thường hoặc số từ 6 đến 15 kí tự!")
    private String password;

    private String gender;

    @NotNull
    @ManyToOne
    private Role role;


}
