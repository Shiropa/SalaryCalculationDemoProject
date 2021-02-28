package org.example.employee.services;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.bankAccount.services.BankAccountService;
import org.example.common.dataclass.BooleanValueHolderDTO;
import org.example.common.service.BaseService;
import org.example.employee.database.entities.Employee;
import org.example.employee.database.repositories.EmployeeRepository;
import org.example.employee.dataclass.EmployeeDTO;
import org.example.employee.dataclass.SalaryDTO;
import org.example.grade.helper.dataclass.GradeDTO;
import org.example.grade.services.GradeService;
import org.example.gradeEmpMapping.dataclass.GradeEmpMappingDTO;
import org.example.gradeEmpMapping.services.GradeEmpMappingService;
import org.example.helper.IdGeneratorComponent;
import org.example.helper.ServiceExceptionHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService extends BaseService<Employee, EmployeeDTO> {

    private final EmployeeRepository repository;
    private final IdGeneratorComponent idGeneratorComponent;
    private final GradeEmpMappingService gradeEmpMappingService;
    private final BankAccountService bankAccountService;
    private final GradeService gradeService;

    public EmployeeService(EmployeeRepository repository, IdGeneratorComponent idGeneratorComponent,
                           GradeEmpMappingService gradeEmpMappingService, BankAccountService bankAccountService, GradeService gradeService) {
        super(repository);
        this.repository = repository;
        this.idGeneratorComponent = idGeneratorComponent;
        this.bankAccountService = bankAccountService;
        this.gradeEmpMappingService = gradeEmpMappingService;
        this.gradeService = gradeService;
    }

    @Override
    protected EmployeeDTO convertForRead(Employee employee) {
        EmployeeDTO employeeDTO = super.convertForRead(employee);
        employeeDTO.setBankAccount(bankAccountService.getByOid(employeeDTO.getBankAccountOid()));
        GradeDTO grade = gradeService.getByOid(gradeEmpMappingService.getCurrentGradeByEmployeeOid(employeeDTO.getOid()));
        employeeDTO.setGrade(grade);
        employeeDTO.setGradeOid(grade.getOid());
        setSalaryInfo(employeeDTO, grade);
        return employeeDTO;
    }

    private void setSalaryInfo(EmployeeDTO employeeDTO, GradeDTO grade) {
        BigDecimal houseRent = grade.getBasicSalary().multiply(new BigDecimal(".20"));
        BigDecimal medicalAllowance = grade.getBasicSalary().multiply(new BigDecimal(".15"));
        employeeDTO.setSalary(new SalaryDTO() {{
            setBasic(grade.getBasicSalary());
            setHouseRent(houseRent);
            setMedicalAllowance(medicalAllowance);
            setTotal(grade.getBasicSalary().add(houseRent).add(medicalAllowance));
        }});
    }

    @Override
    protected Employee convertForCreate(EmployeeDTO employeeDTO) {
        Employee employee = super.convertForCreate(employeeDTO);
        if (repository.existsEmployeeByBankAccountOidAndIsDeleted(employeeDTO.getBankAccountOid(), "No"))
            throw new ServiceExceptionHolder.InvalidRequestException("Bank Account already assign");
        employee.setBankAccount(bankAccountService.getByOidForRead(employeeDTO.getBankAccountOid()));
        int randomNumber = idGeneratorComponent.generateRandomNumber();
        while (repository.existsEmployeeByEmployeeIdAndIsDeleted(randomNumber, "No")) {
            randomNumber = idGeneratorComponent.generateRandomNumber();
        }
        employee.setEmployeeId(randomNumber);
        return employee;
    }

    @Override
    protected void convertForUpdate(EmployeeDTO employeeDTO, Employee employee) {
        employee.setBankAccount(bankAccountService.getByOidForRead(employeeDTO.getBankAccountOid()));
        if (repository.existsEmployeeByOidNotAndBankAccountOidAndIsDeleted(employeeDTO.getOid(), employeeDTO.getBankAccountOid(), "No"))
            throw new ServiceExceptionHolder.InvalidRequestException("Bank Account already assign");
        super.convertForUpdate(employeeDTO, employee);
    }

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employee = createEntity(employeeDTO);
        createGradeMapping(employee.getOid(), employeeDTO.getGradeOid());
        return convertForRead(employee);
    }

    private void createGradeMapping(String empOid, String gradeOid) {
        gradeEmpMappingService.create(new GradeEmpMappingDTO() {{
            setEmployeeOid(empOid);
            setGradeOid(gradeOid);
            setAppliedDate(new Date());
        }});
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        String grade = gradeEmpMappingService.getCurrentGradeByEmployeeOid(employeeDTO.getOid());
        if (!grade.equals(employeeDTO.getGradeOid()))
            createGradeMapping(employeeDTO.getOid(), employeeDTO.getGradeOid());
        EmployeeDTO emp = super.update(employeeDTO);
        return emp;
    }

    @Override
    public BooleanValueHolderDTO delete(@NonNull String oid) {
        gradeEmpMappingService.getByEmployeeOid(oid).forEach(e -> gradeEmpMappingService.delete(e.getOid()));
        return super.delete(oid);
    }

    public boolean isAssignBankAccountOid(String bankAccountOid) {
        return repository.existsEmployeeByBankAccountOidAndIsDeleted(bankAccountOid, "No");
    }
}


