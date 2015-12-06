package com.xiaoy.foreground.resourcesmsg.bug.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.background.resourcesmsg.bug.service.BugService;
import com.xiaoy.background.resourcesmsg.dictionary.controller.DictionaryType;
import com.xiaoy.background.systemmsg.menu.service.MenuService;
import com.xiaoy.base.entities.Bug;
import com.xiaoy.util.CheckBox;
import com.xiaoy.util.MySortASC;
import com.xiaoy.util.Tools;

@Controller("com.xiaoy.foreground.resourcesmsg.bug.controller.BugController")
@RequestMapping("/foreground/resourcesmsg/bug")
public class BugController {

	@Resource
	private BugService bugService;

	@Resource
	private MenuService menuService;

	// 工具服务
	@Resource
	private Tools tools;
	
	@RequestMapping("/index")
	public String index(){
		return "foreground/resourcesmsg/bug/bugList";
	}

	@ResponseBody
	@RequestMapping("/getBugList")
	public Map<String, Object> getBugList(@ModelAttribute("bug") Bug bug, Model model,HttpServletRequest request) {
		String start = Tools.getStringParameter(request, "iDisplayStart");
		String limit = Tools.getStringParameter(request, "iDisplayLength");
		String sEcho = Tools.getStringParameter(request, "sEcho");
		//String start = bug.getStart();
		//String limit = bug.getLimit();

		List<Bug> bugs = bugService.findCollectionByCondition(bug, start, limit);
		for (Bug b : bugs) {
			if (menuService.findObjectById(b.getParentId()) != null) {
				b.setParentName(menuService.findObjectById(b.getParentId()).getMenuName());
			}
			if (menuService.findObjectById(b.getChildrenId()) != null) {
				b.setChildrenName(menuService.findObjectById(b.getChildrenId()).getMenuName());
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sEcho", sEcho);//总记录数
		map.put("iTotalRecords", 20);//总记录数
		map.put("iTotalDisplayRecords", 12);//过滤后的总记录数
		map.put("aaData", bugs);//具体的数据对象数组
		return map;
	}

	/**
	 * BUG严重程度
	 * 
	 * @param request
	 * @return
	 * @date 2015年10月27日 下午11:38:04
	 */
	@Deprecated
	@ResponseBody
	@RequestMapping(value = "/getSeverity", method = RequestMethod.GET)
	public List<CheckBox> getSeverity() {
		List<CheckBox> list = tools.getCheckBox(DictionaryType.BUG_MODEL_CODE_BUG,
				DictionaryType.BUG_FIELD_CODE_SEVERITY);
		Collections.sort(list, new MySortASC());
		return list;
	}

	/**
	 * 向页面发送bug类型下拉列表中的数据
	 * 
	 * @return
	 * @date 2015年10月28日 下午9:54:40
	 */
	@Deprecated
	@ResponseBody
	@RequestMapping(value = "/getBugType", method = RequestMethod.GET)
	public List<CheckBox> getBugType() {
		List<CheckBox> list = tools.getCheckBox(DictionaryType.BUG_MODEL_CODE_BUG,
				DictionaryType.BUG_FIELD_CODE_BUGTYPE);
		Collections.sort(list, new MySortASC());
		return list;
	}

	/**
	 * 重现规律
	 * 
	 * @param request
	 * @return
	 * @date 2015年10月28日 下午9:56:53
	 */
	@Deprecated
	@ResponseBody
	@RequestMapping("/getReappear")
	public List<CheckBox> getReappear() {
		List<CheckBox> list = tools.getCheckBox(DictionaryType.BUG_MODEL_CODE_BUG,
				DictionaryType.BUG_FIELD_CODE_REAPPEAR);
		Collections.sort(list, new MySortASC());
		return list;
	}

	/**
	 * BUG状态
	 * 
	 * @param request
	 * @return
	 * 
	 * @date 2015年9月1日上午11:21:45
	 */
	@Deprecated
	@ResponseBody
	@RequestMapping("/getStatus")
	public List<CheckBox> getStatus() {
		List<CheckBox> list = tools
				.getCheckBox(DictionaryType.BUG_MODEL_CODE_BUG, DictionaryType.BUG_FIELD_CODE_STATUS);
		Collections.sort(list, new MySortASC());
		return list;
	}
}
