package net.fernandosalas.deparmentservice.service.implementation;

import lombok.AllArgsConstructor;
import net.fernandosalas.deparmentservice.dto.DepartmentDto;
import net.fernandosalas.deparmentservice.entity.Department;
import net.fernandosalas.deparmentservice.exception.DepartmentNotFoundException;
import net.fernandosalas.deparmentservice.repository.DepartmentRepository;
import net.fernandosalas.deparmentservice.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImplementation implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Optional<Department> department = Optional.ofNullable(departmentRepository.findByDepartmentCode(departmentCode));
        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department with code: " + departmentCode + " was not found.");
        }
        return modelMapper.map(department, DepartmentDto.class);
    }

}
