package com.xiaoy.background.resourcesmsg.bug.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.xiaoy.annotations.SystemControllerLog;
import com.xiaoy.background.resourcesmsg.bug.service.BugService;
import com.xiaoy.background.resourcesmsg.dictionary.controller.DictionaryType;
import com.xiaoy.background.systemmsg.menu.service.MenuService;
import com.xiaoy.base.entities.Bug;
import com.xiaoy.base.entities.Dictionary;
import com.xiaoy.base.entities.Menu;
import com.xiaoy.util.CheckBox;
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
@RequestMapping("background/resourcesmsg/bug")
public class BugController {

	// 工具服务
	@Resource
	private Tools tools;

	@Resource
	private BugService bugService;

	@Resource
	private MenuService menuService;

	@RequestMapping("/index")
	public String index() {
		return "background/resourcesmsg/bug/index";
	}

	/**
	 * 获取bug列表
	 * 
	 * @return
	 * 
	 * @date 2015年8月12日下午6:00:49
	 */
	@ResponseBody
	@RequestMapping("/getBugList")
	public JsonResult getBugList(Bug bug, HttpServletRequest request) {
		String start = Tools.getStringParameter(request, "start", "");
		String limit = Tools.getStringParameter(request, "limit", "");

		JsonResult json = new JsonResult();
		try {
			List<Bug> bugs = bugService.findCollectionByCondition(bug, start, limit);
			long total = bugService.countByCollection(bug);
			for (Bug b : bugs) {
				if (menuService.findObjectById(b.getParentId()) != null) {
					b.setParentName(menuService.findObjectById(b.getParentId()).getMenuName());
				}
				if (menuService.findObjectById(b.getChildrenId()) != null) {
					b.setChildrenName(menuService.findObjectById(b.getChildrenId()).getMenuName());
				}
			}
			json.setSuccess(true);
			json.setTotal(total);
			json.setRoot(bugs);
		} catch (Exception e) {
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 获取所有的父类集合，（parentId为-1的）
	 * 
	 * @param menu
	 * @return
	 * 
	 * @date 2015年8月13日上午11:04:34
	 */
	@ResponseBody
	@RequestMapping(value = "/parentMenuList")
	public JsonResult parentMenuList(HttpServletRequest request) {
		Menu menu = new Menu();
		menu.setParentId(Tools.getStringParameter(request, "parentId"));

		List<Menu> menus = menuService.findCollectionByCondition(menu, "", "");
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		json.setRoot(menus);
		return json;
	}

	/**
	 * 通过父id获取其下的子类集合
	 * 
	 * @param parentId
	 * @return
	 * 
	 * @date 2015年8月13日上午11:04:34
	 */
	@ResponseBody
	@RequestMapping(value = "/getChildMenuListByParentId")
	public JsonResult getChildMenuListByParentId(HttpServletRequest request) {
		String parentId = Tools.getStringParameter(request, "parentId");
		List<Menu> menus = menuService.getChildMenuListByParentId(parentId);
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
	@ResponseBody
	@RequestMapping(value = "/svaeBug", method = RequestMethod.POST)
	@SystemControllerLog(module = "资源管理", function = "BUG管理", operation = "保存BUG")
	public JsonResult svaeBug(@RequestParam(value = "imgUrls", required = false) MultipartFile[] imgUrls,
			HttpServletRequest request) {
		// 获取bug图片的路径
		String bugRealPath = Tools.getSystemConfigString(request, "bugRealPath");
		String imgUrl = Tools.uploadFile(imgUrls, request, bugRealPath);
		Bug bug = this.setParamBug(request);
		bug.setImgUrl(imgUrl);
		bug.setId(UUID.randomUUID().toString());
		bug.setCreateTime(new Date());
		bug.setUpdateTime(new Date());
		JsonResult json = new JsonResult();
		try {
			bugService.saveObject(bug);
			json.setSuccess(true);
			json.setMessage(MessageTips.SAVE_SUCCESS);
		} catch (Exception e) {
			Tools.deleteFile(request, imgUrl, bugRealPath);
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
	private Bug setParamBug(HttpServletRequest request) {
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
	@ResponseBody
	@RequestMapping(value = "/updateBug", method = RequestMethod.POST)
	@SystemControllerLog(module = "资源管理", function = "BUG管理", operation = "更新BUG")
	public JsonResult updateBug(@RequestParam(value = "imgUrls", required = false) MultipartFile[] imgUrls,
			HttpServletRequest request) {
		// 获取bug图片的路径
		String bugRealPath = Tools.getSystemConfigString(request, "bugRealPath");
		String imgUrl = Tools.uploadFile(imgUrls, request, bugRealPath);
		// 拼接图片名
		JsonResult json = new JsonResult();
		try {
			Bug bug = this.setParamBug(request);
			bug.setId(Tools.getStringParameter(request, "id"));
			bug.setImgUrl(imgUrl);
			bug.setUpdateTime(new Date());
			bugService.updateBug(bug);

			json.setSuccess(true);
			json.setMessage(MessageTips.UPDATE_SUCCESS);
		} catch (Exception e) {
			Tools.deleteFile(request, imgUrl, bugRealPath);
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
	@ResponseBody
	@RequestMapping(value = "/deleteBug/{id}", method = RequestMethod.POST)
	@SystemControllerLog(module = "资源管理", function = "BUG管理", operation = "删除BUG")
	public JsonResult deleteBug(@PathVariable("id") String id, HttpServletRequest request) {
		JsonResult json = new JsonResult();
		try {
			String imgUrls = bugService.findObjectById(id).getImgUrl();
			bugService.deleteObjectByid(id);
			// 获取bug图片的路径
			String bugRealPath = Tools.getSystemConfigString(request, "bugRealPath");
			Tools.deleteFile(request, imgUrls, bugRealPath);
			json.setMessage(MessageTips.DELETE_SUCCESS);
			json.setSuccess(true);
		} catch (Exception e) {
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
	JsonResult getImage(@PathVariable("id") String id, HttpServletRequest request) {
		JsonResult json = new JsonResult();
		try {
			Bug bug = bugService.findObjectById(id);
			if (bug != null) {
				String strImg = bug.getImgUrl();
				if (!StringUtils.isEmpty(strImg)) {
					String imageSplit = Tools.getSystemConfigString(request, "imageSplit");
					String[] image = strImg.split(imageSplit);
					json.setRoot(image);
					json.setSuccess(true);
				}
			}
		} catch (Exception e) {
			json.setMessage(MessageTips.SERVICE_ERRER);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 向页面发送bug类型下拉列表中的数据
	 * 
	 * @param request
	 * @return
	 * 
	 * @date 2015年9月1日上午11:16:37
	 */
	@ResponseBody
	@RequestMapping("/getBugType")
	public JsonResult getBugType(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		// 2015-10-01 by XiaoY 修改：废弃的方法-------start
		// List<CheckBox> list = Tools.getCheckBox(request, "bug", "bugType");
		List<CheckBox> list = tools.getCheckBox(DictionaryType.BUG_MODEL_CODE_BUG,
				DictionaryType.BUG_FIELD_CODE_BUGTYPE);
		// 2015-10-01 by XiaoY 修改：废弃的方法-------end
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}

	/**
	 * 向页面发送bug优先级下拉列表中的数据
	 * 
	 * @param request
	 * @return
	 * 
	 * @date 2015年9月1日上午11:18:28
	 */
	@RequestMapping("/getPriority")
	public @ResponseBody
	JsonResult getPriority(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		// 2015-10-01 by XiaoY 修改：废弃的方法-------start
		// List<CheckBox> list = Tools.getCheckBox(request, "bug", "priority");
		List<CheckBox> list = tools.getCheckBox(DictionaryType.BUG_MODEL_CODE_BUG,
				DictionaryType.BUG_FIELD_CODE_PRIORITY);
		// 2015-10-01 by XiaoY 修改：废弃的方法-------end
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}

	/**
	 * 重现规律
	 * 
	 * @param request
	 * @return
	 * 
	 * @date 2015年9月1日上午11:21:45
	 */
	@RequestMapping("/getReappear")
	public @ResponseBody
	JsonResult getReappear(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		// 2015-10-01 by XiaoY 修改：废弃的方法-------start
		// List<CheckBox> list = Tools.getCheckBox(request, "bug", "reappear");
		List<CheckBox> list = tools.getCheckBox(DictionaryType.BUG_MODEL_CODE_BUG,
				DictionaryType.BUG_FIELD_CODE_REAPPEAR);
		// 2015-10-01 by XiaoY 修改：废弃的方法-------end
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}

	/**
	 * BUG严重程度
	 * 
	 * @param request
	 * @return
	 * 
	 * @date 2015年9月1日上午11:21:45
	 */
	@RequestMapping("/getSeverity")
	public @ResponseBody
	JsonResult getSeverity(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		// 2015-10-01 by XiaoY 修改：废弃的方法-------start
		// List<CheckBox> list = Tools.getCheckBox(request, "bug", "severity");
		List<CheckBox> list = tools.getCheckBox(DictionaryType.BUG_MODEL_CODE_BUG,
				DictionaryType.BUG_FIELD_CODE_SEVERITY);
		// 2015-10-01 by XiaoY 修改：废弃的方法-------end
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}

	/**
	 * BUG状态
	 * 
	 * @param request
	 * @return
	 * 
	 * @date 2015年9月1日上午11:21:45
	 */
	@RequestMapping("/getStatus")
	public @ResponseBody
	JsonResult getStatus(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		// 2015-10-01 by XiaoY 修改：废弃的方法-------start
		// List<CheckBox> list = Tools.getCheckBox(request, "bug", "status");
		List<CheckBox> list = tools
				.getCheckBox(DictionaryType.BUG_MODEL_CODE_BUG, DictionaryType.BUG_FIELD_CODE_STATUS);
		// 2015-10-01 by XiaoY 修改：废弃的方法-------end
		json.setRoot(list);
		json.setSuccess(true);
		return json;
	}

	/**
	 * 查看bug详细信息
	 * 
	 * @param request
	 * @param id
	 *            bugid
	 * @return
	 * @date 2015年10月2日 上午12:07:45
	 */
	@ResponseBody
	@RequestMapping("/getBugViewById/{id}")
	public Map<String, Object> getBugViewById(HttpServletRequest request, @PathVariable("id") String id) {
		Bug bug = bugService.findObjectById(id);
		Map<String, Object> mapResult = new HashMap<String, Object>();
		if (bug != null) {
			String status = bug.getStatus();
			if (!StringUtils.isEmpty(status)) {
				// 2015-10-01 by XiaoY 修改：废弃的方法-----start
				// PropertyModel propertyModel = ReadPropertyXML.getReadPropertyXML(request, "bug", "status", status);
				// Map<String, Map<String, String>> map = propertyModel.getDatas();
				// Map<String, String> map2 = map.get("status");
				// status = map2.get(status);
				Dictionary dictionary = tools.getDictionaryByModelCodeAndFieldCodeAndValueFiel(
						DictionaryType.BUG_MODEL_CODE_BUG, DictionaryType.BUG_FIELD_CODE_STATUS, status);
				if (dictionary != null) {
					status = dictionary.getDisplayField();
				}
				// 2015-10-01 by XiaoY 修改：废弃的方法-----end
				bug.setStatus(status);
			}

			String severity = bug.getSeverity();
			if (!StringUtils.isEmpty(severity)) {
				// 2015-10-01 by XiaoY 修改：废弃的方法-----start
				// PropertyModel propertyModel = ReadPropertyXML.getReadPropertyXML(request, "bug", "severity", severity);
				// Map<String, Map<String, String>> map = propertyModel.getDatas();
				// Map<String, String> map2 = map.get("severity");
				// severity = map2.get(severity);
				Dictionary dictionary = tools.getDictionaryByModelCodeAndFieldCodeAndValueFiel(
						DictionaryType.BUG_MODEL_CODE_BUG, DictionaryType.BUG_FIELD_CODE_SEVERITY, severity);
				if (dictionary != null) {
					severity = dictionary.getDisplayField();
				}
				// 2015-10-01 by XiaoY 修改：废弃的方法-----end
				bug.setSeverity(severity);
			}

			String reappear = bug.getReappear();
			if (!StringUtils.isEmpty(reappear)) {
				// 2015-10-01 by XiaoY 修改：废弃的方法-----start
				// PropertyModel propertyModel = ReadPropertyXML.getReadPropertyXML(request, "bug", "reappear", reappear);
				// Map<String, Map<String, String>> map = propertyModel.getDatas();
				// Map<String, String> map2 = map.get("reappear");
				// reappear = map2.get(reappear);
				Dictionary dictionary = tools.getDictionaryByModelCodeAndFieldCodeAndValueFiel(
						DictionaryType.BUG_MODEL_CODE_BUG, DictionaryType.BUG_FIELD_CODE_REAPPEAR, reappear);
				if (dictionary != null) {
					reappear = dictionary.getDisplayField();
				}
				// 2015-10-01 by XiaoY 修改：废弃的方法-----end
				bug.setReappear(reappear);
			}

			String bugType = bug.getBugType();
			if (!StringUtils.isEmpty(bugType)) {
				// 2015-10-01 by XiaoY 修改：废弃的方法-----start
				// PropertyModel propertyModel = ReadPropertyXML.getReadPropertyXML(request, "bug", "bugType", bugType);
				// Map<String, Map<String, String>> map = propertyModel.getDatas();
				// Map<String, String> map2 = map.get("bugType");
				// bugType = map2.get(bugType);
				Dictionary dictionary = tools.getDictionaryByModelCodeAndFieldCodeAndValueFiel(
						DictionaryType.BUG_MODEL_CODE_BUG, DictionaryType.BUG_FIELD_CODE_BUGTYPE, bugType);
				if (dictionary != null) {
					bugType = dictionary.getDisplayField();
				}
				// 2015-10-01 by XiaoY 修改：废弃的方法-----end
				bug.setBugType(bugType);
			}
			String parentId = bug.getParentId();
			String parentName = menuService.findObjectById(parentId).getMenuName();
			bug.setParentName(parentName);
			String childrenId = bug.getChildrenId();
			String childrenName = menuService.findObjectById(childrenId).getMenuName();
			bug.setChildrenName(childrenName);
			mapResult.put("success", true);
			mapResult.put("data", bug);
		}
		return mapResult;
	}
}
