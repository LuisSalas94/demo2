package net.fernandosalas.deparmentservice.service;

import net.fernandosalas.deparmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);
}
