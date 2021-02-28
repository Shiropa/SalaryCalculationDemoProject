package org.example.common.dataclass;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BooleanValueHolderDTO  {

    private boolean value;

}
