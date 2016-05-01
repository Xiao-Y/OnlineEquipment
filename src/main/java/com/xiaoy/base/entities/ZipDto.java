package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaoy.base.entities.base.Zip;

/**
 * 邮政编码信息表
 * 
 * @author XiaoY
 * @date: 2015年9月5日 下午2:33:39
 */
@Entity
@Table(name = "ZIP")
public class ZipDto extends Zip implements Serializable {

	private static final long serialVersionUID = 756274707097314642L;

	@Override
	public String toString() {
		return "ZipDto [toString()=" + super.toString() + "]";
	}
}
