package org.example.bankAccount.database.repositories;

import org.example.bankAccount.database.entities.BankAccount;
import org.example.common.database.repositories.ServiceRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends ServiceRepository<BankAccount> {

    boolean existsBankAccountByForEmployeeAndIsDeleted(String forEmployee, String isDeleted);

    List<BankAccount> findByForEmployeeAndIsDeleted(String forEmployee, String isDeleted);

    @Query(value = "select * from accounts.bank_account bc " +
            "where bc.is_deleted = 'No' " +
            "and bc.for_employee = 'Yes' " +
            "and bc.oid not in ( " +
            "select e.bank_account_oid  " +
            "from accounts.employee e  " +
            "where e.is_deleted = 'No')", nativeQuery = true)
    List<BankAccount> findNonAssignedBankAccount();

}
