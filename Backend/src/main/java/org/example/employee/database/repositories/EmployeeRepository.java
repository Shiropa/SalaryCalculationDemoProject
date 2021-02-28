package org.example.employee.database.repositories;

import org.example.common.database.repositories.ServiceRepository;
import org.example.employee.database.entities.Employee;

public interface EmployeeRepository extends ServiceRepository<Employee> {

    boolean existsEmployeeByEmployeeIdAndIsDeleted(int empId, String isDeleted);

    boolean existsEmployeeByBankAccountOidAndIsDeleted(String bankOid, String isDeleted);

    boolean existsEmployeeByOidNotAndBankAccountOidAndIsDeleted(String oid, String bankOid, String isDeleted);

}
