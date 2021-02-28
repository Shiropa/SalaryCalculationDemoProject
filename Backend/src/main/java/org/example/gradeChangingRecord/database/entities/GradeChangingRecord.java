package org.example.gradeChangingRecord.database.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.database.entities.BaseEntity;
import org.example.employee.database.entities.Employee;
import org.example.grade.database.entities.Grade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "grade_changing_record")
public class GradeChangingRecord extends BaseEntity {

	private Date appliedDate;

	private BigDecimal previous;

	private BigDecimal present;

	@ManyToOne
	@JoinColumn(name = "grade_oid")
	private Grade grade;

}