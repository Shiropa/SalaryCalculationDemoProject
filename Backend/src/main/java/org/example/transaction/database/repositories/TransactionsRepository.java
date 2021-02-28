package org.example.transaction.database.repositories;

import org.example.common.database.repositories.ServiceRepository;
import org.example.transaction.database.entities.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface TransactionsRepository extends ServiceRepository<Transactions> {

    String common =  "where t.is_deleted = :isDeleted " +
            "and t.bank_account_oid = :bankAccountOid ";

    @Query(value = "select COALESCE(sum(t.debit), 0) from accounts.transactions t " + common, nativeQuery = true)
    BigDecimal findTotalDebit(@Param("bankAccountOid") String bankAccountOid,
                              @Param("isDeleted") String isDeleted);

    @Query(value = "select COALESCE(sum(t.credit), 0) from accounts.transactions t " + common, nativeQuery = true)
    BigDecimal findTotalCredit(@Param("bankAccountOid") String bankAccountOid,
                              @Param("isDeleted") String isDeleted);
}
