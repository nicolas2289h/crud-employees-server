package com.crud.crudReact.service;

import com.crud.crudReact.dto.EmployeeDto;
import com.crud.crudReact.entity.Employee;
import com.crud.crudReact.mapper.EmployeeMapper;
import com.crud.crudReact.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Empleado no encontrado"));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> listEmployees = employeeRepository.findAll();
        return listEmployees.stream().map(item -> EmployeeMapper.mapToEmployeeDto(item)).collect(Collectors.toList());
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Empleado con " + id + " no encontrado."));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    public String createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        employeeRepository.save(employee);
        return "Empleado guardado";
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Elemento no encontrado."));
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDto> findByFirstNameStartingWith(String name) {
        List<Employee> listEmployees = employeeRepository.findByFirstNameStartingWith(name);
        if (listEmployees.isEmpty()) {
            throw new NoSuchElementException("No se encontraron empleados.");
        }
        return listEmployees.stream().map(item -> EmployeeMapper.mapToEmployeeDto(item)).collect(Collectors.toList());
    }
}