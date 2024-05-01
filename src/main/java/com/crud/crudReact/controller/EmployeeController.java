package com.crud.crudReact.controller;

import com.crud.crudReact.dto.EmployeeDto;
import com.crud.crudReact.entity.Employee;
import com.crud.crudReact.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

//@CrossOrigin("*") // CORS PARA CONECTAR CON REACT
@RestController
@Validated
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        try {
            EmployeeDto employeeDto = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        try {
            List<EmployeeDto> listEmployees = employeeService.getAllEmployees();
            return new ResponseEntity<>(listEmployees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestParam("firstName") String firstName,
                                                 @RequestParam("lastName") String lastName,
                                                 @RequestParam("email") String email,
                                                 @RequestParam("imagen") MultipartFile imagen) throws IOException {
        ;
        return new ResponseEntity<>(employeeService.createEmployee(firstName, lastName, email, imagen), HttpStatus.CREATED);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Empleado eliminado", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Empleado no encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
        try {
            EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeDto, id);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/{name}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByName(@PathVariable String name) {
        try {
            List<EmployeeDto> listEmployees = employeeService.findByFirstNameStartingWith(name);
            return new ResponseEntity<>(listEmployees, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
