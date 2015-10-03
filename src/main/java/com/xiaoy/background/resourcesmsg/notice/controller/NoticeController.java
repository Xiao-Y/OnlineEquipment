package com.xiaoy.background.resourcesmsg.notice.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.background.resourcesmsg.notice.service.NoticeService;
import com.xiaoy.base.entities.Notice;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.LoginHelper;
import com.xiaoy.util.MessageTips;
import com.xiaoy.util.Tools;

/**
 * 公告信息控制器类
 * 
 * @author XiaoY
 * @date: 2015年10月3日 下午9:13:46
 */
@Controller
@RequestMapping("/background/resourcesmsg/notice")
public class NoticeController {

	@Resource
	private NoticeService noticeService;

	@RequestMapping("/index")
	public String index() {
		return "background/resourcesmsg/notice/index";
	}

	/**
	 * 显示公告信息列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNoticeList")
	public JsonResult getnNoticeList(HttpServletRequest request) {
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");

		Notice notice = new Notice();
		notice.setStart(start);
		notice.setLimit(limit);

		List<Notice> list = noticeService.getNoticeList(notice);
		long total = noticeService.getTotal(notice);

		JsonResult json = new JsonResult();
		json.setSuccess(true);
		json.setRoot(list);
		json.setTotal(total);
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/saveNotice", method = RequestMethod.POST)
	public JsonResult saveNotice(Notice notice) {
		notice.setId(UUID.randomUUID().toString());
		notice.setNoticeName(LoginHelper.getLoginUserId());
		notice.setCreateTime(new Date());
		notice.setUpdateTime(new Date());
		JsonResult json = new JsonResult();
		try {
			noticeService.saveObject(notice);
			json.setMessage(MessageTips.SAVE_SUCCESS);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMessage(MessageTips.SERVICE_ERRER);
			json.setSuccess(false);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/updateNotice", method = RequestMethod.POST)
	public JsonResult updateNotice(Notice notice) {
		notice.setNoticeName(LoginHelper.getLoginUserId());
		notice.setUpdateTime(new Date());
		JsonResult json = new JsonResult();
		try {
			noticeService.updateObject(notice);
			json.setMessage(MessageTips.UPDATE_SUCCESS);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMessage(MessageTips.SERVICE_ERRER);
			json.setSuccess(false);
		}
		return json;
	}
}
