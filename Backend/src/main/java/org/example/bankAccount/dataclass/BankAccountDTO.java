package org.example.bankAccount.dataclass;

import lombok.Data;
import org.example.common.dataclass.IOidHolderRequestBodyDTO;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BankAccountDTO implements IOidHolderRequestBodyDTO {

	private String oid;

	@NotBlank
	private String accountType;

	@NotBlank
	private String forEmployee;;

	@NotBlank
	private String accountNumber;

	@NotBlank
	private String accountName;

	@NotBlank
	private String bankName;

	@NotBlank
	private String branchName;

	private BigDecimal currentBalance;

	private BigDecimal totalDebit;

	private BigDecimal totalCredit;


}


