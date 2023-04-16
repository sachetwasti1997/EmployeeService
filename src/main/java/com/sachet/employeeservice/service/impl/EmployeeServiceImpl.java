package com.sachet.employeeservice.service.impl;

import com.sachet.employeeservice.clients.ApiClient;
import com.sachet.employeeservice.dto.ApiResDto;
import com.sachet.employeeservice.dto.DepartmentDto;
import com.sachet.employeeservice.dto.EmployeeDto;
import com.sachet.employeeservice.entity.Employee;
import com.sachet.employeeservice.exception.EmailAlreadyExist;
import com.sachet.employeeservice.exception.ResourceNotFoundException;
import com.sachet.employeeservice.repository.EmployeeRepository;
import com.sachet.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private WebClient webClient;
    private ApiClient apiClient;

    private DepartmentDto callDepartmentService(String operation, DepartmentDto departmentDto, String departmentCode) {
        DepartmentDto res = null;
        String url = "http://localhost:8080/api/v1/department/code/"+departmentCode;
        if (operation.equals("GET")) {
//            res = webClient
//                    .get()
//                    .uri(url)
//                    .retrieve()
//                    .bodyToMono(DepartmentDto.class)
//                    .block();
//            return res;
            res = apiClient.getDepartment(departmentCode);
            return res;
        }
        return null;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employee) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employee.getEmail());
        if (employeeOptional.isPresent()) {
            throw new EmailAlreadyExist("Employee with email "+employee.getEmail()+" already exist");
        }
        Employee employeeToSave = modelMapper.map(employee, Employee.class);
        return modelMapper.map(employeeRepository.save(employeeToSave), EmployeeDto.class);
    }

    @Override
    public ApiResDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id.toString())
        );
        DepartmentDto departmentDto = callDepartmentService(
                "GET",
                null,
                employee.getDepartmentCode()
        );
        EmployeeDto em = modelMapper.map(employee, EmployeeDto.class);
        ApiResDto apiResDto = new ApiResDto();
        apiResDto.setEmployee(em);
        apiResDto.setDepartment(departmentDto);
        return apiResDto;
    }

    @Override
    public ApiResDto getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Email", email));
        DepartmentDto departmentDto = callDepartmentService(
                "GET",
                null,
                employee.getDepartmentCode()
        );
        EmployeeDto em = modelMapper.map(employee, EmployeeDto.class);
        ApiResDto apiResDto = new ApiResDto();
        apiResDto.setEmployee(em);
        apiResDto.setDepartment(departmentDto);
        return apiResDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).toList();
    }

    @Override
    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", employeeDto.getId().toString())
        );
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployeeByEmail(String email, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Email", email)
        );
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }

    @Override
    public void deleteEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Email", email)
        );
        employeeRepository.delete(employee);
    }
}
