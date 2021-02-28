package org.example.gradeChangingRecord.services;


import lombok.extern.slf4j.Slf4j;
import org.example.common.service.BaseService;
import org.example.gradeChangingRecord.database.entities.GradeChangingRecord;
import org.example.gradeChangingRecord.database.repositories.GradeChangingRecordRepository;
import org.example.gradeChangingRecord.dataclass.GradeChangingRecordDTO;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GradeChangingRecordService extends BaseService<GradeChangingRecord, GradeChangingRecordDTO> {

    private final GradeChangingRecordRepository repository;

    public GradeChangingRecordService(GradeChangingRecordRepository repository) {
        super(repository);
        this.repository = repository;
    }

//    public String getCurrentGradeByEmployeeOid(String empOid) {
//        return  repository.findAllByGradeOidAndIsDeletedOrderByAppliedDateDesc(empOid, "No").get(0).getGrade().getOid();
//    }


}


