package com.royesta.webapp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author Roy Royesta (royesta lab)
 * @param <T> Type of ID
 */
@MappedSuperclass
public abstract class AbstractEntity<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(updatable = false, nullable =false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(nullable=false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updated;
	
	@Version
	private int version;
	
	public abstract void setId(T id);
	public abstract T getId();
	
	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
    }
    
    protected void onUpdate() {
		updated = new Date();
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getCreated() {
		return this.created;
	}
	
	public Date getUpdated() {
		return this.updated;
	}
	
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	public int getVersion() {
		return this.version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity<T> that = (AbstractEntity<T>) o;

        if (version != that.version) return false;
        if (created != null ? !created.equals(that.getCreated()) : that.getCreated() != null) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (updated != null ? !updated.equals(that.getUpdated()) : that.getUpdated() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }
}
