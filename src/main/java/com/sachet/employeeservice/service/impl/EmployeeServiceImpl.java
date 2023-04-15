package com.sachet.employeeservice.service.impl;

import com.sachet.employeeservice.dto.EmployeeDto;
import com.sachet.employeeservice.entity.Employee;
import com.sachet.employeeservice.repository.EmployeeRepository;
import com.sachet.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employee) {
        Employee employeeToSave = modelMapper.map(employee, Employee.class);
        return modelMapper.map(employeeRepository.save(employeeToSave), EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String email) {
        return null;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).toList();
    }

    @Override
    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public EmployeeDto updateEmployeeByEmail(String email, EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public void deleteEmployeeByEmail(String email) {

    }
}
