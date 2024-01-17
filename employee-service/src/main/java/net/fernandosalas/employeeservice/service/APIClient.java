package net.fernandosalas.employeeservice.service;

import net.fernandosalas.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);
}
