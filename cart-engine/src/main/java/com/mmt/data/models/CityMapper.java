package com.mmt.data.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "citymapper", catalog = "mmt")
public class CityMapper implements java.io.Serializable {

	private Integer ctyId;
	private String ctyName;
	private String ctyFltcode;
	private String ctyBuscode;
	private String ctyCarcode;
	private String ctyHtlcode;

	public CityMapper() {
	}

	public CityMapper(String ctyName) {
		this.ctyName = ctyName;
	}

	public CityMapper(String ctyName, String ctyFltcode, String ctyBuscode,
			String ctyCarcode, String ctyHtlcode) {
		this.ctyName = ctyName;
		this.ctyFltcode = ctyFltcode;
		this.ctyBuscode = ctyBuscode;
		this.ctyCarcode = ctyCarcode;
		this.ctyHtlcode = ctyHtlcode;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CTY_ID", unique = true, nullable = false)
	public Integer getCtyId() {
		return this.ctyId;
	}

	public void setCtyId(Integer ctyId) {
		this.ctyId = ctyId;
	}

	@Column(name = "CTY_NAME", nullable = false, length = 45)
	public String getCtyName() {
		return this.ctyName;
	}

	public void setCtyName(String ctyName) {
		this.ctyName = ctyName;
	}

	@Column(name = "CTY_FLTCODE", length = 3)
	public String getCtyFltcode() {
		return this.ctyFltcode;
	}

	public void setCtyFltcode(String ctyFltcode) {
		this.ctyFltcode = ctyFltcode;
	}

	@Column(name = "CTY_BUSCODE", length = 5)
	public String getCtyBuscode() {
		return this.ctyBuscode;
	}

	public void setCtyBuscode(String ctyBuscode) {
		this.ctyBuscode = ctyBuscode;
	}

	@Column(name = "CTY_CARCODE", length = 5)
	public String getCtyCarcode() {
		return this.ctyCarcode;
	}

	public void setCtyCarcode(String ctyCarcode) {
		this.ctyCarcode = ctyCarcode;
	}

	@Column(name = "CTY_HTLCODE", length = 15)
	public String getCtyHtlcode() {
		return this.ctyHtlcode;
	}

	public void setCtyHtlcode(String ctyHtlcode) {
		this.ctyHtlcode = ctyHtlcode;
	}

}
