package com.example.demo;

public class EmpFullDto {
	private int id;
	private String name;
	private String desigination;
	
	private AddressDTOReq address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesigination() {
		return desigination;
	}

	public void setDesigination(String desigination) {
		this.desigination = desigination;
	}

	public AddressDTOReq getAddress() {
		return address;
	}

	public void setAddress(AddressDTOReq address) {
		this.address = address;
	}
	
	
}
