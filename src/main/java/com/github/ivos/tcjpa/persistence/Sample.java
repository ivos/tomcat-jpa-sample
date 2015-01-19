package com.github.ivos.tcjpa.persistence;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ForeignKey;

/**
 * Sample entity.
 */
@Entity
@SequenceGenerator(name = "sample_seq", sequenceName = "sample_seq", allocationSize = 1)
public class Sample {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sample_seq")
	private Long id;

	@Version
	private long version;

	@NotNull
	@Size(max = 100)
	private String value;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@ForeignKey(name = "sample__related")
	@Valid
	private Related related;

	public Sample() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Related getRelated() {
		return related;
	}

	public void setRelated(Related related) {
		this.related = related;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Sample other = (Sample) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sample [id=" + id + ", version=" + version + ", value=" + value
				+ "]";
	}

}
