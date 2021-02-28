package org.example.grade.controllers;

import org.example.common.controller.BaseController;
import org.example.grade.database.entities.Grade;
import org.example.grade.helper.dataclass.BasicSalaryAddDTO;
import org.example.grade.helper.dataclass.GradeDTO;
import org.example.grade.services.GradeService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("grade/")
public class GradeController extends BaseController<Grade, GradeDTO> {

	private final GradeService service;

	public GradeController(GradeService service) {
		super(service);
		this.service = service;
	}

	@Transactional
	@PostMapping(path="insert-salary", produces = "application/json")
	public @ResponseBody List<GradeDTO> create(@RequestBody BasicSalaryAddDTO d) {
		return service.createBasicSalary(d);
	}


}


