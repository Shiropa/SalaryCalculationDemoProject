package org.example.bankAccount.database.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.database.entities.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "bank_account")
public class BankAccount extends BaseEntity {

	private String accountType;
	private String forEmployee;
	private String accountNumber;
	private String accountName;
	private String bankName;
	private String branchName;
	private BigDecimal currentBalance;

}

