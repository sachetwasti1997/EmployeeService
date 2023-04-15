package com.sachet.employeeservice.service;

import com.sachet.employeeservice.dto.EmployeeDto;
import com.sachet.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employee);
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto getEmployeeByEmail(String email);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployeeById(EmployeeDto employeeDto);
    EmployeeDto updateEmployeeByEmail(String email, EmployeeDto employeeDto);
    void deleteEmployeeByEmail(String email);

}
