package org.example.grade.services;


import lombok.extern.slf4j.Slf4j;
import org.example.common.service.BaseService;
import org.example.grade.database.entities.Grade;
import org.example.grade.database.repositories.GradeRepository;
import org.example.grade.helper.dataclass.BasicSalaryAddDTO;
import org.example.grade.helper.dataclass.GradeDTO;
import org.example.gradeChangingRecord.dataclass.GradeChangingRecordDTO;
import org.example.gradeChangingRecord.services.GradeChangingRecordService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GradeService extends BaseService<Grade, GradeDTO> {

    private final GradeRepository repository;
    private final GradeChangingRecordService gradeChangingRecordService;

    public GradeService(GradeRepository repository, GradeChangingRecordService gradeChangingRecordService) {
        super(repository);
        this.repository = repository;
        this.gradeChangingRecordService = gradeChangingRecordService;
    }

    @Override
    public List<GradeDTO> getList() {
        return super.getList().stream().sorted(Comparator.comparing(GradeDTO::getGradeNo)).collect(Collectors.toList());
    }

    public List<GradeDTO> createBasicSalary(BasicSalaryAddDTO basicSalaryAdd) {
        BigDecimal lastGradeSalary = basicSalaryAdd.getBasicSalary();
        List<GradeDTO> list = new ArrayList<>();
        for (GradeDTO e : convertForRead(repository.findAllByIsDeletedOrderByGradeNoDesc("No"))) {
            addGradeChangingRecord(lastGradeSalary, e);
            e.setBasicSalary(lastGradeSalary);
            list.add(update(e));
            lastGradeSalary = lastGradeSalary.add(basicSalaryAdd.getIncreased());
        }

        return list.stream().sorted(Comparator.comparing(GradeDTO::getGradeNo)).collect(Collectors.toList());
    }

    private void addGradeChangingRecord(BigDecimal lastGradeSalary, GradeDTO e) {
        gradeChangingRecordService.create(new GradeChangingRecordDTO() {{
            setAppliedDate(new Date());
            setGradeOid(e.getOid());
            setPrevious(e.getBasicSalary());
            setPresent(lastGradeSalary);
        }});
    }


}


