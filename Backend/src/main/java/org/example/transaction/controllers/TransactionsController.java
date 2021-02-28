package org.example.transaction.controllers;

import org.example.common.controller.BaseController;
import org.example.transaction.database.entities.Transactions;
import org.example.transaction.dataclass.TransactionsDTO;
import org.example.transaction.services.TransactionsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions/")
public class TransactionsController extends BaseController<Transactions, TransactionsDTO> {

	private final TransactionsService service;

	public TransactionsController(TransactionsService service) {
		super(service);
		this.service = service;
	}


}


