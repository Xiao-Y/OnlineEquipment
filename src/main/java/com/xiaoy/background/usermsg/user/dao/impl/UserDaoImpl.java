package com.xiaoy.background.usermsg.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xiaoy.background.usermsg.user.dao.UserDao;
import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.Role;
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
	public List<User> findUsersByCondition(User user, String start, String limit) {
		StringBuffer hql = new StringBuffer("");
		if (user != null && user.getRoles() != null && !user.getRoles().isEmpty()) {
			hql.append("from User u left join fetch u.roles role where 1=1 ");
		} else {
			hql.append("from User u where 1=1 ");
		}
		Map<String, Object> paramsMapValue = this.appendWher(hql, user);
		List<User> users = this.find(hql.toString(), paramsMapValue, start, limit);
		return users;
	}

	@Override
	public long countByCollection(User user) {
		StringBuffer hql = new StringBuffer("select count(*) ");
		if (user != null && user.getRoles() != null && !user.getRoles().isEmpty()) {
			hql.append("from User u left join u.roles role where 1=1 ");
		} else {
			hql.append("from User u where 1=1 ");
		}
		Map<String, Object> paramsMapValue = this.appendWher(hql, user);
		return this.countHQLByCollection(hql.toString(), paramsMapValue);
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
			if (user.getRoles() != null && user.getRoles().size() > 0) {
				Set<Role> roles = user.getRoles();
				StringBuffer str = new StringBuffer("");
				str.append(" and ( ");
				int i = 0;
				for (Role role : roles) {
					String roleIdIndex = "roleId" + i;
					str.append(" role.id = :");
					str.append(roleIdIndex);
					str.append(" or");
					map.put(roleIdIndex, role.getId());
					++i;
				}
				String hqlw = "";
				if(str.toString().endsWith(" or"))
				{
					str.append("or");
					hqlw = str.toString().replace("oror", "");
				}
				hqlWhere.append(hqlw);
				hqlWhere.append(" ) ");
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
				hqlWhere.append(" and u.createTime > timestamp(:createTiem1, '00 00:00:00') ");
				map.put("createTiem1", strDate);

				hqlWhere.append(" and u.createTime < timestamp(:createTiem2, '01 00:00:00') ");
				map.put("createTiem2", strDate);
			}

			// 更新时间
			if (user.getUpdateTime() != null) {
				String strDate = DateHelper.dateConverString(user.getUpdateTime());
				hqlWhere.append(" and u.updateTime > timestamp(:updateTime1, '00 00:00:00') ");
				map.put("updateTime1", strDate);

				hqlWhere.append(" and u.updateTime < timestamp(:updateTime2, '01 00:00:00') ");
				map.put("updateTime2", strDate);
			}

			hqlWhere.append(" order by u.updateTime desc");
			return map;
		}
		return null;
	}

}
