package com.example.demo;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "address" , url = "http://localhost:8082/api/address")
public interface AddressClient {
	
	@PostMapping("/add")
	public ResponseEntity<AddressDTOReq> addAddress(AddressDTOReq address);
	
	@GetMapping("/get/{eid}")
	public AddressDTOReq getAddressbyId(@PathVariable("eid") int eid);
}
