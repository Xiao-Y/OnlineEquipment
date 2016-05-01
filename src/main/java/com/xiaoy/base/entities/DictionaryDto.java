package com.xiaoy.base.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaoy.base.entities.base.Dictionary;

/**
 * 数据字典
 * 
 * @author XiaoY
 * @date: 2015年9月20日 下午1:23:15
 */
@Entity
@Table(name = "DICTIONARY")
public class DictionaryDto extends Dictionary implements Serializable {

	private static final long serialVersionUID = 2502088694584721393L;

	@Override
	public String toString() {
		return "DictionaryDto []" + super.toString();
	}
}
