package org.example.transaction.services;


import lombok.extern.slf4j.Slf4j;
import org.example.common.service.BaseService;
import org.example.transaction.database.entities.Transactions;
import org.example.transaction.database.repositories.TransactionsRepository;
import org.example.transaction.dataclass.TransactionsDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class TransactionsService extends BaseService<Transactions, TransactionsDTO> {

    private final TransactionsRepository repository;

    public TransactionsService(TransactionsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public BigDecimal getTotalDebit(String bankAccountOid) {
        return  repository.findTotalDebit(bankAccountOid, "No");
    }

    public BigDecimal getTotalCredit(String bankAccountOid) {
        return  repository.findTotalCredit(bankAccountOid, "No");
    }


}


