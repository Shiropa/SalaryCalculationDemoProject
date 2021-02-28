package org.example.grade.database.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Table(name = "grade")
public class Grade extends BaseEntity {

	private String gradeName;
	private int gradeNo;
	private BigDecimal basicSalary;

//	@ManyToOne
//	@JoinColumn(name = "payee_oid")
//	private Payee payee;
//
//	@ManyToOne
//	@JoinColumn(name = "identification_method_oid")
//	private IdentificationMethod identificationMethod;
//
//	@ManyToOne
//	@JoinColumn(name = "financial_method_oid")
//	private FinancialMethod financialMethod;

}

