package org.example.grade.database.repositories;

import org.example.common.database.repositories.ServiceRepository;
import org.example.grade.database.entities.Grade;
import org.example.gradeEmpMapping.database.entities.GradeEmpMapping;

import java.util.List;

public interface GradeRepository extends ServiceRepository<Grade> {

    List<Grade> findAllByIsDeletedOrderByGradeNoDesc(String isDeleted);

}
