package com.xiaoy.background.resourcesmsg.notice.service;

import java.util.List;

import com.xiaoy.base.entities.Notice;
import com.xiaoy.base.service.CommonService;

/**
 * 公告信息服务
 * 
 * @author XiaoY
 * @date: 2015年10月3日 下午9:13:12
 */
public interface NoticeService extends CommonService<Notice> {

	/**
	 * 根据条件查询出现公告信息
	 * 
	 * @param notice
	 * @return
	 * @date 2015年10月3日 下午9:12:08
	 */
	List<Notice> getNoticeList(Notice notice);

	/**
	 * 根据条件统计公告记录数
	 * 
	 * @param notice
	 * @return
	 * @date 2015年10月3日 下午9:12:43
	 */
	long getTotal(Notice notice);

}
