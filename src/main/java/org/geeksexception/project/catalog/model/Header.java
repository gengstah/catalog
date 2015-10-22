package org.geeksexception.project.catalog.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "HEADER")
public class Header implements Serializable {
	
	private static final long serialVersionUID = -5187476245502926665L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "HEADER_ID", nullable = false)
	private Long id;
	
	@Column(name = "HEADER_NAME", nullable = false)
	@NotEmpty(message = "Header name must not be empty")
	private String name;
	
	@OneToMany(mappedBy = "header")
	private List<Section> sections;
	
	public Header() { }

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

}