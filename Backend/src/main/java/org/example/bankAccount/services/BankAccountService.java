package org.example.bankAccount.services;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.bankAccount.database.entities.BankAccount;
import org.example.bankAccount.database.repositories.BankAccountRepository;
import org.example.bankAccount.dataclass.BalanceHolderDTO;
import org.example.bankAccount.dataclass.BankAccountAndBalanceHolderDTO;
import org.example.bankAccount.dataclass.BankAccountDTO;
import org.example.common.dataclass.BooleanValueHolderDTO;
import org.example.common.dataclass.OidSetRequestBodyDTO;
import org.example.common.service.BaseService;
import org.example.employee.dataclass.EmployeeDTO;
import org.example.employee.services.EmployeeService;
import org.example.helper.ServiceExceptionHolder;
import org.example.transaction.dataclass.TransactionsDTO;
import org.example.transaction.services.TransactionsService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BankAccountService extends BaseService<BankAccount, BankAccountDTO> {

    private final BankAccountRepository repository;
    private final TransactionsService transactionsService;
    private final EmployeeService employeeService;

    public BankAccountService(BankAccountRepository repository, TransactionsService transactionsService, @Lazy EmployeeService employeeService) {
        super(repository);
        this.repository = repository;
        this.transactionsService = transactionsService;
        this.employeeService = employeeService;
    }

    @Override
    protected BankAccountDTO convertForRead(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = super.convertForRead(bankAccount);
        bankAccountDTO.setTotalDebit(transactionsService.getTotalDebit(bankAccount.getOid()));
        bankAccountDTO.setTotalCredit(transactionsService.getTotalCredit(bankAccount.getOid()));
        return bankAccountDTO;
    }

    @Override
    protected BankAccount convertForCreate(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = super.convertForCreate(bankAccountDTO);
        if (bankAccountDTO.getForEmployee().equals("No") && repository.existsBankAccountByForEmployeeAndIsDeleted("No", "No"))
            throw new ServiceExceptionHolder.InvalidRequestException("Bank account already added");
        bankAccount.setCurrentBalance(BigDecimal.ZERO);
        return bankAccount;
    }

    @Override
    protected void convertForUpdate(BankAccountDTO bankAccountDTO, BankAccount bankAccount) {
        bankAccountDTO.setCurrentBalance(bankAccount.getCurrentBalance());
        super.convertForUpdate(bankAccountDTO, bankAccount);
    }

    public BankAccountDTO getMainAccount() {
        List<BankAccount> bankAccount = repository.findByForEmployeeAndIsDeleted("No", "No");
        if (bankAccount.isEmpty())
            return null;
        return convertForRead(bankAccount.get(0));
    }

    public List<BankAccountDTO> geEmployeeAccounts() {
        List<BankAccount> bankAccount = repository.findByForEmployeeAndIsDeleted("Yes", "No");
        if (bankAccount.isEmpty())
            return new ArrayList<>();
        return convertForRead(bankAccount);
    }

    public BankAccountDTO addBalanceInMainAccount(BalanceHolderDTO balanceHolderDTO) {
        BankAccount bankAccount = repository.findByForEmployeeAndIsDeleted("No", "No").get(0);
        bankAccount.setCurrentBalance(bankAccount.getCurrentBalance().add(balanceHolderDTO.getBalance()));
        addTransaction(bankAccount.getOid(), balanceHolderDTO.getBalance(), BigDecimal.ZERO);
        return convertForRead(bankAccount);
    }

    private void addTransaction(String bankOid, BigDecimal debit, BigDecimal credit) {
        transactionsService.create(new TransactionsDTO() {{
            setBankAccountOid(bankOid);
            setTransactionDate(new Date());
            setDebit(debit);
            setCredit(credit);
        }});
    }

    public List<BankAccountDTO> transferSalary(OidSetRequestBodyDTO request) {

        List<EmployeeDTO> employeeDTO = employeeService.getByOids(request.getOids());
        BigDecimal total = employeeDTO.stream().map(m -> m.getSalary().getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BankAccount comAcc = repository.findByForEmployeeAndIsDeleted("No", "No").get(0);
        if (total.compareTo(comAcc.getCurrentBalance()) > 0)
            throw new ServiceExceptionHolder.InvalidRequestException("Insufficient Balance in main account");

        comAcc.setCurrentBalance(comAcc.getCurrentBalance().subtract(total));
        repository.save(comAcc);

        addTransaction(comAcc.getOid(), BigDecimal.ZERO, total);

        return employeeDTO.stream().map(e -> {
            BankAccount empAccount = getByOidForRead(e.getBankAccountOid());
            empAccount.setCurrentBalance(empAccount.getCurrentBalance().add(e.getSalary().getTotal()));
            addTransaction(empAccount.getOid(), e.getSalary().getTotal(), BigDecimal.ZERO);
            return convertForRead(empAccount);
        }).collect(Collectors.toList());
    }


    public List<BankAccountDTO> getNonAssignedBankList() {
        List<BankAccount> bankAccount = repository.findByForEmployeeAndIsDeleted("Yes", "No");
        if (bankAccount.isEmpty())
            return new ArrayList<>();
        return convertForRead(bankAccount);
    }

    @Override
    public BooleanValueHolderDTO delete(@NonNull String oid) {
        if (employeeService.isAssignBankAccountOid(oid))
            throw new ServiceExceptionHolder.InvalidRequestException("Already assign this bank");
        return super.delete(oid);
    }
}


