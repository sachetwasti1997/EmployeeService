package com.sachet.employeeservice.controller;

import com.sachet.employeeservice.dto.EmployeeDto;
import com.sachet.employeeservice.entity.Employee;
import com.sachet.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable String email) {
        return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<EmployeeDto> updateEmployeeByEmail(@PathVariable String email
            , @RequestBody @Valid EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployeeByEmail(email, employeeDto));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable Long id,
                                                          @RequestBody @Valid EmployeeDto employeeDto) {
        employeeDto.setId(id);
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDto));
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<String> deleteEmployeeByEmail(@PathVariable String email) {
        employeeService.deleteEmployeeByEmail(email);
        return ResponseEntity.ok("Successfully deleted employee with email "+email);
    }
}
