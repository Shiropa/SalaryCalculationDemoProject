package org.example.transaction.dataclass;

import lombok.Data;
import org.example.common.dataclass.IOidHolderRequestBodyDTO;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionsDTO implements IOidHolderRequestBodyDTO {

	private String oid;

	@NotNull
	private Date transactionDate;

	@NotNull
	private BigDecimal debit;

	@NotNull
	@DecimalMin("0")
	private BigDecimal credit;

	@NotBlank
	private String bankAccountOid;


}


