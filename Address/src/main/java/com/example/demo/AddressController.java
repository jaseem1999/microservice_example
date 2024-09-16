package com.example.demo;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private AddressRepository addressRepo;
	
	
	@PostMapping("/add")
	public ResponseEntity<AddressDTO> addAddAddress(@RequestBody AddressDTO address){
		addressRepo.save(address);
		return ResponseEntity.ok(address);
	}
	
	@GetMapping("/get/{eid}")
	public AddressDTO getAddressDTO(@PathVariable("eid") int eid) {
		AddressDTO address = addressRepo.findByEid(eid);
		return address;
	}
	
}
