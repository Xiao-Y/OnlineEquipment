package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 邮政编码信息表
 * 
 * @author XiaoY
 * @date: 2015年9月5日 下午2:33:39
 */
@Entity
@Table(name = "ZIP")
public class Zip implements Serializable{

	private static final long serialVersionUID = 8612608297217687300L;
	
	private String id;// 行政码
	private String name;// 全称
	private String parentId;// 上级行政码
	private String shortName;// 简称
	private String levelType;// 等级
	private String cityCode;// 城市Code
	private String zipCode;// 邮编
	private String mergerName;// 完整名称
	private String lng;// 经度
	private String lat;// 纬度
	private String pinyin;// 名称拼音

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false, length = 10)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PARENT_ID", length = 20)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "SHORT_NAME", length = 20)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "LEVEL_TYPE", length = 20)
	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	@Column(name = "CITY_CODE", length = 20)
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Column(name = "ZIP_CODE", length = 20)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "MERGER_NAME", length = 80)
	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	@Column(name = "LNG", length = 20)
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Column(name = "LAT", length = 20)
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Column(name = "PIN_YIN", length = 50)
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	public String toString() {
		return "Zip [id=" + id + ", name=" + name + ", parentId=" + parentId + ", shortName=" + shortName + ", levelType=" + levelType + ", cityCode="
				+ cityCode + ", zipCode=" + zipCode + ", mergerName=" + mergerName + ", lng=" + lng + ", lat=" + lat + ", pinyin=" + pinyin + "]";
	}
}
