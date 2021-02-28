package org.example.employee.database.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.bankAccount.database.entities.BankAccount;
import org.example.common.database.entities.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "employee")
public class Employee extends BaseEntity {

	private int employeeId;
	private String employeeName;
	private String address;
	private int mobile;

	@ManyToOne
	@JoinColumn(name = "bank_account_oid")
	private BankAccount bankAccount;

}

