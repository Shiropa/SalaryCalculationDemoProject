package org.example.gradeChangingRecord.database.repositories;

import org.example.common.database.repositories.ServiceRepository;
import org.example.gradeChangingRecord.database.entities.GradeChangingRecord;

import java.util.List;

public interface GradeChangingRecordRepository extends ServiceRepository<GradeChangingRecord> {

    List<GradeChangingRecord> findAllByGradeOidAndIsDeletedOrderByAppliedDateDesc(String empOid, String isDeleted);
}
