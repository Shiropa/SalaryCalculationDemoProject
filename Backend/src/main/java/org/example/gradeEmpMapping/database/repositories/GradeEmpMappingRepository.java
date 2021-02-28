package org.example.gradeEmpMapping.database.repositories;

import org.example.common.database.repositories.ServiceRepository;
import org.example.gradeEmpMapping.database.entities.GradeEmpMapping;

import java.util.List;

public interface GradeEmpMappingRepository extends ServiceRepository<GradeEmpMapping> {

    List<GradeEmpMapping> findAllByEmployeeOidAndIsDeletedOrderByAppliedDateDesc(String empOid, String isDeleted);
}
