package com.capgemini.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractVersionControlEntity {

	@Version
	@Column(name = "version", columnDefinition = "LONG DEFAULT 1", nullable = false)
	private long version = 1L;
	
	@Column(name = "record_creation_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(name = "record_last_modification", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
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
}
