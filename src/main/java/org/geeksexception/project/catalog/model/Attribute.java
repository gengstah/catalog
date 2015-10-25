package org.geeksexception.project.catalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ATTRIBUTE")
public class Attribute implements Serializable {
	
	private static final long serialVersionUID = -7080541247557812019L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "ATTRIBUTE_ID", nullable = false)
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "VALUE", nullable = true)
	private String value;
	
	public Attribute() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}