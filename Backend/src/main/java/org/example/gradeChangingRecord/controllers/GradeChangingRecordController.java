package org.example.gradeChangingRecord.controllers;

import org.example.common.controller.BaseController;
import org.example.gradeChangingRecord.database.entities.GradeChangingRecord;
import org.example.gradeChangingRecord.dataclass.GradeChangingRecordDTO;
import org.example.gradeChangingRecord.services.GradeChangingRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("grade-changing-record/")
public class GradeChangingRecordController extends BaseController<GradeChangingRecord, GradeChangingRecordDTO> {

	private final GradeChangingRecordService service;

	public GradeChangingRecordController(GradeChangingRecordService service) {
		super(service);
		this.service = service;
	}


}


