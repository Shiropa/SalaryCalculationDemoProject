package org.example.gradeChangingRecord.dataclass;

import lombok.Data;
import org.example.common.dataclass.IOidHolderRequestBodyDTO;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class GradeChangingRecordDTO implements IOidHolderRequestBodyDTO {

	private String oid;

	@NotNull
	private Date appliedDate;

	@NotNull
	private BigDecimal previous;

	@NotNull
	private BigDecimal present;

	@NotNull
	private String gradeOid;


}


