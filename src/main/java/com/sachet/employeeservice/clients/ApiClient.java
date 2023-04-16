package com.sachet.employeeservice.clients;

import com.sachet.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiClient {

    @GetMapping("api/v1/department/code/{code}")
    DepartmentDto getDepartment(@PathVariable String code);

}
