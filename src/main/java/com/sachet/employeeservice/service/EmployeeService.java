package com.sachet.employeeservice.service;

import com.sachet.employeeservice.dto.EmployeeDto;
import com.sachet.employeeservice.entity.Employee;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employee);

    EmployeeDto getEmployeeById(Long id);

}
