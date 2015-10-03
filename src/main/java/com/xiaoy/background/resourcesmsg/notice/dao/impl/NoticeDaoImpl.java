package com.xiaoy.background.resourcesmsg.notice.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.xiaoy.background.resourcesmsg.notice.dao.NoticeDao;
import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.Notice;
import com.xiaoy.util.DateHelper;

@Repository
public class NoticeDaoImpl extends CommonDaoImpl<Notice> implements NoticeDao {

	@Override
	public List<Notice> getNoticeList(Notice notice) {
		String start = notice.getStart();
		String limit = notice.getLimit();
		StringBuffer hqlWhere = new StringBuffer();
		Map<String, Object> paramsMapValue = this.appendWhere(notice, hqlWhere);
		return super.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, start, limit);
	}

	@Override
	public long getTotal(Notice notice) {
		StringBuffer hqlWhere = new StringBuffer();
		Map<String, Object> paramsMapValue = this.appendWhere(notice, hqlWhere);
		return super.countByCollection(hqlWhere.toString(), paramsMapValue);
	}

	/**
	 * 拼接HQL语句
	 * 
	 * @param hqlWhere
	 *            条件查询语句
	 * @return
	 * @date 2015年10月3日 下午9:26:53
	 */
	private Map<String, Object> appendWhere(Notice notice, StringBuffer hqlWhere) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 公告内容
		if (!StringUtils.isEmpty(notice.getNotice())) {
			hqlWhere.append(" and notice like :notice ");
			map.put("notice", "%" + notice.getNotice() + "%");
		}
		// 公告标题
		if (!StringUtils.isEmpty(notice.getNoticeTit())) {
			hqlWhere.append(" and noticeTit like :noticeTit ");
			map.put("noticeTit", "%" + notice.getNoticeTit() + "%");
		}
		// 公告人
		if (!StringUtils.isEmpty(notice.getNoticeName())) {
			hqlWhere.append(" and noticeName like :noticeName ");
			map.put("noticeName", "%" + notice.getNoticeName() + "%");
		}
		// 创建时间
		if (notice.getCreateTime() != null) {
			String strDate = DateHelper.dateConverString(notice.getCreateTime());
			hqlWhere.append(" and createTime > timestamp(:createTiem1, '00 00:00:00') ");
			map.put("createTiem1", strDate);

			hqlWhere.append(" and createTime < timestamp(:createTiem2, '01 00:00:00') ");
			map.put("createTiem2", strDate);
		}

		// 更新时间
		if (notice.getUpdateTime() != null) {
			String strDate = DateHelper.dateConverString(notice.getUpdateTime());
			hqlWhere.append(" and updateTime > timestamp(:updateTime1, '00 00:00:00') ");
			map.put("updateTime1", strDate);

			hqlWhere.append(" and updateTime < timestamp(:updateTime2, '01 00:00:00') ");
			map.put("updateTime2", strDate);
		}
		
		hqlWhere.append(" order by updateTime desc");
		return map;
	}
}
