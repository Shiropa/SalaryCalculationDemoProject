package org.example.employee.dataclass;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalaryDTO  {

	private BigDecimal total;
	private BigDecimal basic;
	private BigDecimal houseRent;
	private BigDecimal medicalAllowance;


}


