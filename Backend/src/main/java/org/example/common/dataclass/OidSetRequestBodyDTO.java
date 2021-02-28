package org.example.common.dataclass;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class OidSetRequestBodyDTO implements IRequestBodyDTO {

    @NotEmpty
    private Set<@NotBlank String> oids;

}
