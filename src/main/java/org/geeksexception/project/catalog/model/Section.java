package org.geeksexception.project.catalog.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SECTION")
public class Section implements Serializable {
	
	private static final long serialVersionUID = -3807610038754350329L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "SECTION_ID", nullable = false)
	private Long id;
	
	@Column(name = "SECTION_NAME", nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "HEADER_ID")
	private Header header;
	
	@ManyToMany
	@JoinTable(name = "SECTION_ATTRIBUTES", 
		joinColumns = {@JoinColumn(name="SECTION_ID")},
		inverseJoinColumns = {@JoinColumn(name="ATTRIBUTE_ID")})
	private List<Attribute> defaultAttributes;
	
	public Section() { }

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

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public List<Attribute> getDefaultAttributes() {
		return defaultAttributes;
	}

	public void setDefaultAttributes(List<Attribute> defaultAttributes) {
		this.defaultAttributes = defaultAttributes;
	}
	
}