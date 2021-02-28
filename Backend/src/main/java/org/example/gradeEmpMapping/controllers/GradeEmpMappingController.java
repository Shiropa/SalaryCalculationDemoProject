package org.example.gradeEmpMapping.controllers;

import org.example.common.controller.BaseController;
import org.example.gradeEmpMapping.database.entities.GradeEmpMapping;
import org.example.gradeEmpMapping.dataclass.GradeEmpMappingDTO;
import org.example.gradeEmpMapping.services.GradeEmpMappingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("grade-employee-mapping/")
public class GradeEmpMappingController extends BaseController<GradeEmpMapping, GradeEmpMappingDTO> {

	private final GradeEmpMappingService service;

	public GradeEmpMappingController(GradeEmpMappingService service) {
		super(service);
		this.service = service;
	}


}


