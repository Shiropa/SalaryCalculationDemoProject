package org.example.gradeEmpMapping.database.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.database.entities.BaseEntity;
import org.example.employee.database.entities.Employee;
import org.example.grade.database.entities.Grade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "grade_employee_mapping")
public class GradeEmpMapping extends BaseEntity {

	private Date appliedDate;

	@ManyToOne
	@JoinColumn(name = "employee_oid")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "grade_oid")
	private Grade grade;

}