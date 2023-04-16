package com.sachet.employeeservice.clients;

import com.sachet.employeeservice.dto.DepartmentDto;
import com.sachet.employeeservice.dto.OrganisationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ORGANISATION-SERVICE")
public interface ApiClientOrganisation {

    @GetMapping("api/v1/organisation/code/{code}")
    OrganisationDto getOrganisation(@PathVariable String code);

}
