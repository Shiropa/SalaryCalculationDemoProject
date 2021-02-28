package org.example.bankAccount.dataclass;

import lombok.Data;
import org.example.common.dataclass.IOidHolderRequestBodyDTO;
import org.example.common.dataclass.IRequestBodyDTO;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BalanceHolderDTO implements IRequestBodyDTO {

	@NotNull
	@DecimalMin("1")
	private BigDecimal balance;


}


