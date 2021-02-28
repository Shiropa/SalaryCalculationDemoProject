package org.example.common.database.entities;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    private String oid;
    private String isDeleted;
    private Date createdOn;
    private Date updatedOn;
}
