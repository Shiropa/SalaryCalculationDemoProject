package org.example.grade.helper.dataclass;

import lombok.Data;
import org.example.common.dataclass.IOidHolderRequestBodyDTO;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class GradeDTO implements IOidHolderRequestBodyDTO {

	private String oid;

	@NotBlank
	private String gradeName;

	@NotNull
	private int gradeNo;

	@NotNull
	@DecimalMin("0")
	private BigDecimal basicSalary;


}


