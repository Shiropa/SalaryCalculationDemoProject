package org.example.employee.controllers;

import org.example.common.controller.BaseController;
import org.example.employee.database.entities.Employee;
import org.example.employee.dataclass.EmployeeDTO;
import org.example.employee.services.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee/")
public class EmployeeController extends BaseController<Employee, EmployeeDTO> {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		super(service);
		this.service = service;
	}


}


