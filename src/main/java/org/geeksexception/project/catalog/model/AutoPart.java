package org.geeksexception.project.catalog.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.geeksexception.project.catalog.enums.AutoPartStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "AUTO_PART")
public class AutoPart implements Serializable {
	
	private static final long serialVersionUID = 1153982262969808841L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "AUTO_PART_ID", nullable = false)
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	@NotEmpty(message = "Name must not be empty")
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = true, length = 1000)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private AutoPartStatus status;
	
	@Column(name = "QUANTITY", nullable = false)
	@NotNull(message = "Quantity must not be null")
	private Integer quantity;
	
	@Column(name = "LIST_PRICE", nullable = true)
	private BigDecimal listPrice;
	
	@Column(name = "ACTUAL_PRICE", nullable = true)
	private BigDecimal actualPrice;
	
	@Column(name = "UNIVERSAL", nullable = false)
	@NotNull(message = "Universal must not be null")
	private Boolean universal;
	
	@ManyToOne
	@JoinColumn(name = "SECTION_ID", nullable = false)
	private Section section;
	
	@ManyToMany
	@JoinTable(name = "AUTO_PART_ATTRIBUTES", 
		joinColumns = {@JoinColumn(name="AUTO_PART_ID")},
		inverseJoinColumns = {@JoinColumn(name="ATTRIBUTE_ID")})
	@Fetch(FetchMode.JOIN)
	private List<Attribute> attributes;
	
	@ElementCollection
	@Column(name = "FILE_LOCATION")
	private List<String> imageFileNames;
	
	@ManyToMany
	@JoinTable(name = "AUTO_PART_CARS", 
		joinColumns = {@JoinColumn(name="AUTO_PART_ID")},
		inverseJoinColumns = {@JoinColumn(name="CAR_ID")})
	private List<Car> compatibleCars;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_ADDED", nullable = false)
	private Date dateAdded;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_APPROVED", nullable = true)
	private Date dateApproved;
	
	public AutoPart() { }
	
	@PrePersist
	public void prePersist() {
		this.dateAdded = new Date();
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AutoPartStatus getStatus() {
		return status;
	}

	public void setStatus(AutoPartStatus status) {
		this.status = status;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}

	public boolean isUniversal() {
		return universal;
	}

	public void setUniversal(boolean universal) {
		this.universal = universal;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<String> getImageFileNames() {
		return imageFileNames;
	}

	public void setImageFileNames(List<String> imageFileNames) {
		this.imageFileNames = imageFileNames;
	}

	public List<Car> getCompatibleCars() {
		return compatibleCars;
	}

	public void setCompatibleCars(List<Car> compatibleCars) {
		this.compatibleCars = compatibleCars;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(Date dateApproved) {
		this.dateApproved = dateApproved;
	}
	
}