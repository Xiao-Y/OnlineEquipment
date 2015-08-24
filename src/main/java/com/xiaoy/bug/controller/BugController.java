package com.xiaoy.bug.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoy.base.entities.Bug;
import com.xiaoy.base.entities.Menu;
import com.xiaoy.bug.service.BugService;
import com.xiaoy.menu.service.MenuService;
import com.xiaoy.util.JsonResult;
import com.xiaoy.util.MessageTips;
import com.xiaoy.util.Tools;

/**
 * bug控制器类
 * 
 * @author XiaoY
 * @date 2015年8月12日下午5:54:03
 */
@Controller
@RequestMapping("/bug")
public class BugController
{

	/**
	 * 上传图片的路径
	 */
	public final static String REALPATH = "resource/upload/image";

	@Resource
	private BugService bugService;

	@Resource
	private MenuService menuService;

	@RequestMapping("/index")
	public String index()
	{
		return "bug/index";
	}

	/**
	 * 获取bug列表
	 * 
	 * @return
	 * 
	 * @date 2015年8月12日下午6:00:49
	 */
	@RequestMapping("/getBugList")
	public @ResponseBody
	JsonResult getBugList(Bug bug, HttpServletRequest request)
	{
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");

		JsonResult json = new JsonResult();
		try
		{
			List<Bug> bugs = bugService.findCollectionByCondition(bug, start, limit);
			long total = bugService.countByCollection(bug);
			for (Bug b : bugs)
			{
				if (menuService.findObjectById(b.getParentId()) != null)
				{
					b.setParentName(menuService.findObjectById(b.getParentId()).getMenuName());
				}
				if (menuService.findObjectById(b.getChildrenId()) != null)
				{
					b.setChildrenName(menuService.findObjectById(b.getChildrenId()).getMenuName());
				}
			}
			json.setSuccess(true);
			json.setTotal(total);
			json.setRoot(bugs);
		} catch (Exception e)
		{
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 通过父id获取其下的子类集合
	 * 
	 * @param menu
	 * @return
	 * 
	 * @date 2015年8月13日上午11:04:34
	 */
	@RequestMapping(value = "/parentMenuList")
	public @ResponseBody
	JsonResult parentMenuList(HttpServletRequest request)
	{
		Menu menu = new Menu();
		menu.setParentId(Tools.getStringParameter(request, "parentId"));

		List<Menu> menus = menuService.findCollectionByCondition(menu, "", "");
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		json.setRoot(menus);
		return json;
	}

	/**
	 * 保存BUG
	 * 
	 * @param bug
	 * @return
	 */
	@RequestMapping(value = "/svaeBug", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult svaeBug(@RequestParam(value = "imgUrls", required = false) MultipartFile[] imgUrls, HttpServletRequest request)
	{
		String imgUrl = Tools.uploadFile(imgUrls, request, REALPATH);
		Bug bug = this.setParamBug(request);
		bug.setImgUrl(imgUrl);
		bug.setId(UUID.randomUUID().toString());
		bug.setCreateTime(new Date());
		bug.setUpdateTime(new Date());

		JsonResult json = new JsonResult();
		try
		{
			bugService.saveObject(bug);
			json.setSuccess(true);
			json.setMessage(MessageTips.SAVE_SUCCESS);
		} catch (Exception e)
		{
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 向Bug对象中放入值
	 * 
	 * @param request
	 * @return
	 */
	private Bug setParamBug(HttpServletRequest request)
	{
		Bug bug = new Bug();
		bug.setParentId(Tools.getStringParameter(request, "parentId"));
		bug.setChildrenId(Tools.getStringParameter(request, "childrenId"));
		bug.setSeverity(Tools.getStringParameter(request, "severity"));
		bug.setStatus(Tools.getStringParameter(request, "status"));
		bug.setReappear(Tools.getStringParameter(request, "reappear"));
		bug.setBugType(Tools.getStringParameter(request, "bugType"));
		bug.setNote(Tools.getStringParameter(request, "note"));
		bug.setReviseExplain(Tools.getStringParameter(request, "reviseExplain"));
		bug.setTitle(Tools.getStringParameter(request, "title"));
		return bug;
	}

	/**
	 * 更新BUG
	 * 
	 * @param bug
	 * @return
	 */
	@RequestMapping(value = "/updateBug", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult updateBug(@RequestParam(value = "imgUrls", required = false) MultipartFile[] imgUrls, HttpServletRequest request)
	{
		String imgUrl = Tools.uploadFile(imgUrls, request, REALPATH);
		JsonResult json = new JsonResult();
		try
		{
			Bug bug = this.setParamBug(request);
			bug.setId(Tools.getStringParameter(request, "id"));
			bug.setImgUrl(imgUrl);
			bug.setUpdateTime(new Date());
			bugService.updateBug(bug);
			json.setSuccess(true);
			json.setMessage(MessageTips.UPDATE_SUCCESS);
		} catch (Exception e)
		{
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 删除BUG
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteBug/{id}", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult deleteBug(@PathVariable("id") String id)
	{
		JsonResult json = new JsonResult();
		try
		{
			bugService.deleteObjectByid(id);
			json.setMessage(MessageTips.DELETE_SUCCESS);
			json.setSuccess(true);
		} catch (Exception e)
		{
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 根据id获取图片名数组
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getImage/{id}")
	public @ResponseBody
	JsonResult getImage(@PathVariable("id") String id)
	{
		JsonResult json = new JsonResult();
		try
		{
			Bug bug = bugService.findObjectById(id);
			if (bug != null)
			{
				String strImg = bug.getImgUrl();
				if (!StringUtils.isEmpty(strImg))
				{
					String[] image = strImg.split(",");
					json.setRoot(image);
					json.setSuccess(true);
				}
			}

		} catch (Exception e)
		{
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}
}
