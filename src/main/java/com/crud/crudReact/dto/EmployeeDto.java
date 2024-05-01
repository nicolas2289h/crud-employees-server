package com.crud.crudReact.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagen;
}
