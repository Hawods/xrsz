package org.hawods.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hawods.pan on 2016/5/27 0025.
 */
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

	public static final String ID_PROPERTY_NAME = "id";

	public static final String CREATE_DATE_PROPERTY_NAME = "createDate";

	public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";

	public static final String VERSION_PROPERTY_NAME = "version";

	private ID id;

	private Date createDate;

	private Date modifyDate;

	private Long version;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public boolean isNew() {
		return getId() == null;
	}

	@Override
	public String toString() {
		return String.format("Entity of type %s with id: %s", getClass().getName(), getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		BaseEntity<?> other = (BaseEntity<?>) obj;
		return getId() != null ? getId().equals(other.getId()) : false;
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += getId() != null ? getId().hashCode() * 31 : 0;
		return hashCode;
	}

}
