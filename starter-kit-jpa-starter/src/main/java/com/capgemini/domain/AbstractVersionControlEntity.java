package com.capgemini.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractVersionControlEntity {

	@Version
	@Column(name = "version", columnDefinition = "Integer DEFAULT 1", nullable = false)
	private Integer version = 1;
	
	@Column(name = "record_creation_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", 
			nullable = false, unique = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(name = "record_last_modification", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, unique = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@PrePersist
	public void setDatesAndVersionBeforeSave() {
		setCreateDateBeforeSave();
		setLastModifiedDateBeforeSave();
	}
	
	private void setCreateDateBeforeSave() {
		if (this.createDate == null) {
			this.createDate = new Date();
		}
	}
	
	private void setLastModifiedDateBeforeSave() {
		if (this.lastModifiedDate == null) {
			this.lastModifiedDate = new Date();
		}
	}
	
	@PreUpdate
	public void setLastModifiedDateBeforeUpdate() {
		this.lastModifiedDate = new Date();
	}
}
