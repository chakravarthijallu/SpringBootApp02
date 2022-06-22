package com.example.SpringBootApp02.App01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
		
	}

	@PostMapping("/employees")
	public ResponseEntity<List<Employee>> findAll(){
		return ResponseEntity.ok(employeeRepository.findAll());
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> findEmployeeId(@PathVariable(value = "id") Integer id){
		
		Employee employee = employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee Not Found"+id));
				return ResponseEntity.ok().body(employee);
				
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") Integer id){
		Employee employee = employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee Not Found"+id)
				);
		employeeRepository.delete(employee);
		return ResponseEntity.ok().build();
	}

}
