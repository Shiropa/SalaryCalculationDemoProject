package org.example.gradeEmpMapping.services;


import lombok.extern.slf4j.Slf4j;
import org.example.common.service.BaseService;
import org.example.gradeEmpMapping.database.entities.GradeEmpMapping;
import org.example.gradeEmpMapping.database.repositories.GradeEmpMappingRepository;
import org.example.gradeEmpMapping.dataclass.GradeEmpMappingDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GradeEmpMappingService extends BaseService<GradeEmpMapping, GradeEmpMappingDTO> {

    private final GradeEmpMappingRepository repository;

    public GradeEmpMappingService(GradeEmpMappingRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public String getCurrentGradeByEmployeeOid(String empOid) {
        List<GradeEmpMapping> list = repository.findAllByEmployeeOidAndIsDeletedOrderByAppliedDateDesc(empOid, "No");
        if (list.isEmpty()) return null;
        return  list.get(0).getGrade().getOid();
    }

    public List<GradeEmpMapping> getByEmployeeOid(String empOid) {
        List<GradeEmpMapping> list = repository.findAllByEmployeeOidAndIsDeletedOrderByAppliedDateDesc(empOid, "No");
        if (list.isEmpty()) return new ArrayList<>();
        return list;
    }


}


