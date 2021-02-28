package org.example.transaction.database.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.bankAccount.database.entities.BankAccount;
import org.example.common.database.entities.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "transactions")
public class Transactions extends BaseEntity {

	private Date transactionDate;
	private BigDecimal debit;
	private BigDecimal credit;

	@ManyToOne
	@JoinColumn(name = "bank_account_oid")
	private BankAccount bankAccount;

}

