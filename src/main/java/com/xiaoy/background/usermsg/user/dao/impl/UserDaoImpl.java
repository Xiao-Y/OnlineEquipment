package com.xiaoy.background.usermsg.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xiaoy.background.usermsg.user.dao.UserDao;
import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.User;
import com.xiaoy.util.DateHelper;

@Repository
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getUserIdByName(String name) {
		String hql = "select id from User where username like :username";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setParameter("username", name);
		List<Object> list = query.list();
		return list;
	}

	@Override
	public User findByName(String loginName) {
		String hql = "select User from User where username = :username";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setParameter("username", loginName);
		return (User) query.uniqueResult();
	}

	@Override
	public List<User> findCollectionByCondition(User user, String start, String limit) {
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appendWher(hqlWhere, user);
		return super.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, start, limit);
	}

	@Override
	public long countByCollection(User user) {
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appendWher(hqlWhere, user);
		return super.countByCollection(hqlWhere.toString(), paramsMapValue);
	}

	/**
	 * 拼接查询语句
	 * 
	 * @param hqlWhere
	 * @param user
	 * @return
	 */
	private Map<String, Object> appendWher(StringBuffer hqlWhere, User user) {
		if (user != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(user.getUsername())) {
				hqlWhere.append(" and username like :username");
				map.put("username", "%" + user.getUsername() + "%");
			}
			if (!StringUtils.isEmpty(user.getAddress())) {
				hqlWhere.append(" and address like :address");
				map.put("address", "%" + user.getAddress() + "%");
			}
			if(user.getRoles() != null && user.getRoles().size() > 0){
				hqlWhere.append(" and roles = :roles");
				map.put("roles", user.getRoles());
			}
			// 创建时间
			if (user.getBirthday() != null) {
				String strDate = DateHelper.dateConverString(user.getBirthday());
				hqlWhere.append(" and birthday = timestamp(:birthday, '00 00:00:00') ");
				map.put("birthday", strDate);
			}
			// 创建时间
			if (user.getCreateTime() != null) {
				String strDate = DateHelper.dateConverString(user.getCreateTime());
				hqlWhere.append(" and createTime > timestamp(:createTiem1, '00 00:00:00') ");
				map.put("createTiem1", strDate);

				hqlWhere.append(" and createTime < timestamp(:createTiem2, '01 00:00:00') ");
				map.put("createTiem2", strDate);
			}

			// 更新时间
			if (user.getUpdateTime() != null) {
				String strDate = DateHelper.dateConverString(user.getUpdateTime());
				hqlWhere.append(" and updateTime > timestamp(:updateTime1, '00 00:00:00') ");
				map.put("updateTime1", strDate);

				hqlWhere.append(" and updateTime < timestamp(:updateTime2, '01 00:00:00') ");
				map.put("updateTime2", strDate);
			}

			hqlWhere.append(" order by updateTime desc");
			return map;
		}
		return null;
	}

}
