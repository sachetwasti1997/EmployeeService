package com.sachet.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganisationDto {
    private Long id;
    private String organisationName;
    private String organisationDescription;
    private String organisationCode;
    private LocalDateTime createdDate;
    private String message;
}
