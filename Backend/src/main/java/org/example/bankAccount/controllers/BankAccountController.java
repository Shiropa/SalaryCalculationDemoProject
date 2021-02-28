package org.example.bankAccount.controllers;

import org.example.bankAccount.database.entities.BankAccount;
import org.example.bankAccount.dataclass.BalanceHolderDTO;
import org.example.bankAccount.dataclass.BankAccountAndBalanceHolderDTO;
import org.example.bankAccount.dataclass.BankAccountDTO;
import org.example.bankAccount.services.BankAccountService;
import org.example.common.controller.BaseController;
import org.example.common.dataclass.OidSetRequestBodyDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bank-account/")
public class BankAccountController extends BaseController<BankAccount, BankAccountDTO> {

	private final BankAccountService service;

	public BankAccountController(BankAccountService service) {
		super(service);
		this.service = service;
	}

	@Transactional
	@PostMapping(path="main-acc-balance-add", produces = "application/json")
	public @ResponseBody
	BankAccountDTO addBalanceInMainAccount(@RequestBody BalanceHolderDTO d) {
		return service.addBalanceInMainAccount(d);
	}

	@Transactional
	@PostMapping(path="transfer-salary", produces = "application/json")
	public @ResponseBody
	List<BankAccountDTO> transferSalary(@RequestBody OidSetRequestBodyDTO request) {
		return service.transferSalary(request);
	}

	@GetMapping(path="get-main-account", produces = "application/json")
	public @ResponseBody BankAccountDTO getMainAccount() {
		return service.getMainAccount();
	}

	@GetMapping(path="get-employee-accounts", produces = "application/json")
	public @ResponseBody List<BankAccountDTO> geEmployeeAccounts() {
		return service.geEmployeeAccounts();
	}


}


