package com.crud.crudReact.service;

import com.crud.crudReact.dto.EmployeeDto;
import com.crud.crudReact.entity.Employee;
import com.crud.crudReact.mapper.EmployeeMapper;
import com.crud.crudReact.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Employee createEmployee(String firstName, String lastName, String email, MultipartFile imagen) throws IOException {

        // VERIFICA QUE EL ARCHIVO SEA SI O SI UNA IMAGEN
        if (!imagen.getContentType().startsWith("image")) {
            throw new IllegalArgumentException("El archivo no es una imagen");
        }

        // Verifica que el tamaño de la imagen sea menor a 2MB
        if (imagen.getSize() > 5 * 1024 * 1024) { // 5MB en bytes
            throw new IllegalArgumentException("El tamaño de la imagen excede el límite permitido de 2MB");
        }

        // Convertir la imagen de perfil a un arreglo de bytes
        byte[] imagenBytes = imagen.getBytes();

        // Crear el empleado con los datos proporcionados
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setImagen(imagenBytes);

//        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        employeeRepository.save(employee);

        return employee;
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