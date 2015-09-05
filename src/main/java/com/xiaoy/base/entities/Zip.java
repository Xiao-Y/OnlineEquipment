package com.xiaoy.base.entities;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 邮政编码信息表
 * 
 * @author XiaoY
 * @date: 2015年9月5日 下午2:33:39
 */
public class Zip {

	private int id;// 编号
	private String provinceName;// 省中文名称
	private String provincePinyin;// 省名称的拼音
	private String provinceAbb;// 省拼音写缩
	private String provinceSimple;// 省的简称
	private String cityName;// 城市中文名称
	private String shortName;// 城市中文简称
	private String cityPinyin;// 城市名称拼音
	private String cityAbb;// 市名称拼音缩写
	private String county;// 县城
	private String countyPinyin;// 县城名称拼音
	private String countyAbb;// 县名称拼音缩写
	private String areaCode;// 号区
	private String zip;// 邮政编码

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false, length = 100)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "PROVINCE_NAME", length = 20)
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "PROVINCE_PINYIN", length = 50)
	public String getProvincePinyin() {
		return provincePinyin;
	}

	public void setProvincePinyin(String provincePinyin) {
		this.provincePinyin = provincePinyin;
	}

	@Column(name = "PROVINCE_ABB", length = 20)
	public String getProvinceAbb() {
		return provinceAbb;
	}

	public void setProvinceAbb(String provinceAbb) {
		this.provinceAbb = provinceAbb;
	}

	@Column(name = "PROVINCE_SIMPLE", length = 10)
	public String getProvinceSimple() {
		return provinceSimple;
	}

	public void setProvinceSimple(String provinceSimple) {
		this.provinceSimple = provinceSimple;
	}

	@Column(name = "CITY_NAME", length = 50)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "SHORT_NAME", length = 20)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "CITY_PINYIN", length = 50)
	public String getCityPinyin() {
		return cityPinyin;
	}

	public void setCityPinyin(String cityPinyin) {
		this.cityPinyin = cityPinyin;
	}

	@Column(name = "CITY_ABB", length = 20)
	public String getCityAbb() {
		return cityAbb;
	}

	public void setCityAbb(String cityAbb) {
		this.cityAbb = cityAbb;
	}

	@Column(name = "COUNTY", length = 20)
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Column(name = "COUNTY_PINYIN", length = 50)
	public String getCountyPinyin() {
		return countyPinyin;
	}

	public void setCountyPinyin(String countyPinyin) {
		this.countyPinyin = countyPinyin;
	}

	@Column(name = "COUNTY_ABB", length = 20)
	public String getCountyAbb() {
		return countyAbb;
	}

	public void setCountyAbb(String countyAbb) {
		this.countyAbb = countyAbb;
	}

	@Column(name = "AREA_CODE", length = 20)
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "ZIP", length = 10)
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
