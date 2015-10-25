package org.geeksexception.project.catalog.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CAR")
public class Car implements Serializable {
	
	private static final long serialVersionUID = 4359203415434967195L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "CAR_ID", nullable = false)
	private Long id;
	
	@Column(name = "YEAR", nullable = false)
	@NotNull(message = "Year must not be null")
	private Integer year;
	
	@Column(name = "MAKE", nullable = false)
	@NotEmpty(message = "Make must not be empty")
	private String make;
	
	@Column(name = "MODEL", nullable = false)
	@NotEmpty(message = "Model must not be empty")
	private String model;
	
	@Column(name = "SUBMODEL", nullable = false)
	@NotEmpty(message = "Submodel must not be empty")
	private String submodel;
	
	@Column(name = "ENGINE", nullable = false)
	@NotEmpty(message = "Engine must not be empty")
	private String engine;
	
	@ManyToMany(mappedBy = "compatibleCars")
	private List<AutoPart> compatibleAutoParts;
	
	public Car() { }
	
	public Car(int year, String make, String model, String submodel, String engine) {
		this.year = year;
		this.make = make;
		this.model = model;
		this.submodel = submodel;
		this.engine = engine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSubmodel() {
		return submodel;
	}

	public void setSubmodel(String submodel) {
		this.submodel = submodel;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public List<AutoPart> getCompatibleAutoParts() {
		return compatibleAutoParts;
	}

	public void setCompatibleAutoParts(List<AutoPart> compatibleAutoParts) {
		this.compatibleAutoParts = compatibleAutoParts;
	}
	
}