package org.geeksexception.project.catalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "TALENT_IMAGE")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@identity", scope = Image.class)
public class Image implements Serializable {
	
	private static final long serialVersionUID = 4423958681415358043L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "TALENT_IMAGE_ID", nullable = false)
	private Long id;
	
	@Column(name = "FILE_LOCATION", nullable = false)
	@NotEmpty(message = "File must not be empty")
	private String fileLocation;
	
	@Column(name = "THUMBNAIL_FILE_LOCATION", nullable = false)
	@NotEmpty(message = "Thumbnail file must not be empty")
	private String thumbnailFileLocation;
	
	@ManyToOne
	@JoinColumn(name="AUTO_PART_ID")
	private AutoPart autoPart;
	
	public Image() { }
	
	public Image(String fileLocation, String thumbnailFileLocation) { this.fileLocation = fileLocation; this.thumbnailFileLocation = thumbnailFileLocation; }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	public String getThumbnailFileLocation() {
		return thumbnailFileLocation;
	}

	public void setThumbnailFileLocation(String thumbnailFileLocation) {
		this.thumbnailFileLocation = thumbnailFileLocation;
	}

	public AutoPart getAutoPart() {
		return autoPart;
	}

	public void setAutoPart(AutoPart autoPart) {
		this.autoPart = autoPart;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileLocation == null) ? 0 : fileLocation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (fileLocation == null) {
			if (other.fileLocation != null)
				return false;
		} else if (!fileLocation.equals(other.fileLocation))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}