package org.example.gradeEmpMapping.dataclass;

import lombok.Data;
import org.example.common.dataclass.IOidHolderRequestBodyDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class GradeEmpMappingDTO implements IOidHolderRequestBodyDTO {

	private String oid;

	@NotNull
	private Date appliedDate;

	@NotBlank
	private String gradeOid;

	@NotBlank
	private String employeeOid;


}


