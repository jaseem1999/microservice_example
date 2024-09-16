package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private AddressClient addressClient;
	
	@PostMapping("/add")
	public ResponseEntity<String> addEmployee(@RequestBody EmpFullDto emp) {

	    try {
	        // Create and save EmployeeDTO
	        EmployeeDTO employee = new EmployeeDTO();
	        employee.setName(emp.getName());
	        employee.setDesigination(emp.getDesigination());
	        
	        // Save employee and get the generated ID
	        employee = empRepo.save(employee);
	        
	        // Create AddressDTOReq and associate it with the Employee
	        AddressDTOReq address = emp.getAddress();
	        address.setEid(employee.getId());  // Set employee ID for the address
	        
	        // Send the address to another service using FeignClient
	        ResponseEntity<AddressDTOReq> response = addressClient.addAddress(address);
	        
	        // Check if the address creation was successful
	        if (response.getStatusCode().is2xxSuccessful()) {
	            return ResponseEntity.ok("Employee and Address added successfully");
	        } else {
	            return ResponseEntity.status(response.getStatusCode()).body("Failed to add Address");
	        }
	        
	    } catch (Exception e) {
	        // In case of any exception, log the error and return a failed response
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Employee and Address");
	    }
	    
	}

	@GetMapping("/get/{id}")
	public EmpFullDto getById(@PathVariable("id") int id) {
		EmployeeDTO emp = empRepo.findById(id).orElse(null);
		
		EmpFullDto employee = new EmpFullDto();
		
		try {
			AddressDTOReq address =addressClient.getAddressbyId(emp.getId());
			employee.setName(emp.getName());
			employee.setId(emp.getId());
			employee.setDesigination(emp.getDesigination());
			employee.setAddress(address);
			
			return employee;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	
}
