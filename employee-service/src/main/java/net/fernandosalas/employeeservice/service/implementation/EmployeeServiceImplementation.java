package net.fernandosalas.employeeservice.service.implementation;

import lombok.AllArgsConstructor;
import net.fernandosalas.employeeservice.dto.APIResponseDto;
import net.fernandosalas.employeeservice.dto.DepartmentDto;
import net.fernandosalas.employeeservice.dto.EmployeeDto;
import net.fernandosalas.employeeservice.entity.Employee;
import net.fernandosalas.employeeservice.exception.EmailAlreadyExistsException;
import net.fernandosalas.employeeservice.exception.ResourceNotFoundException;
import net.fernandosalas.employeeservice.repository.EmployeeRepository;
import net.fernandosalas.employeeservice.service.APIClient;
import net.fernandosalas.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient webClient;

    @Autowired
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employeeDto.getEmail());

        if (employeeOptional.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for employee:");
        }

        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        // RestTemplate
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"
//                + employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();

        // WebClient
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        // Feign Client
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
