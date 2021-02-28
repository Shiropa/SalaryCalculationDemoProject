package org.example.employee.dataclass;

import lombok.Data;
import org.example.bankAccount.database.entities.BankAccount;
import org.example.bankAccount.dataclass.BankAccountDTO;
import org.example.common.dataclass.IOidHolderRequestBodyDTO;
import org.example.grade.helper.dataclass.GradeDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeDTO implements IOidHolderRequestBodyDTO {

	private String oid;

	private int employeeId;

	@NotBlank
	private String employeeName;

	@NotBlank
	private String address;

	@NotNull
	private int mobile;

	@NotBlank
	private String bankAccountOid;

	@NotBlank
	private String gradeOid;

	private BankAccountDTO bankAccount;

	private GradeDTO grade;

	private SalaryDTO salary;


}


