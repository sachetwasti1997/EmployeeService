package com.sachet.employeeservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    @NotEmpty(message = "Employee First Name cannot be null or empty!")
    private String firstName;
    @NotEmpty(message = "Employee Last Name cannot be null or empty!")
    private String lastName;
    @NotEmpty(message = "Employee Email cannot be null or empty!")
    @Email
    private String email;
    private String departmentCode;
    private String organisationCode;

}
