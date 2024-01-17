package net.fernandosalas.employeeservice.service;

import net.fernandosalas.employeeservice.dto.APIResponseDto;
import net.fernandosalas.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
