package com.example.home.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @Id
    private Integer num;


    private String id;

    private String psw;

    private Timestamp user_time;
}
