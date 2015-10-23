package com.xiaoy.foreground.resourcesmsg.bug.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoy.background.resourcesmsg.bug.service.BugService;
import com.xiaoy.background.systemmsg.menu.service.MenuService;
import com.xiaoy.base.entities.Bug;

@Controller("com.xiaoy.foreground.resourcesmsg.bug.controller.BugController")
@RequestMapping("/foreground/resourcesmsg/bug")
public class BugController {

	@Resource
	private BugService bugService;

	@Resource
	private MenuService menuService;

	@RequestMapping("/index")
	public String getBugList(Model model) {

		Bug bug = new Bug();
		String start = bug.getStart();
		String limit = bug.getLimit();

		List<Bug> bugs = bugService.findCollectionByCondition(bug, start, limit);
		for (Bug b : bugs) {
			if (menuService.findObjectById(b.getParentId()) != null) {
				b.setParentName(menuService.findObjectById(b.getParentId()).getMenuName());
			}
			if (menuService.findObjectById(b.getChildrenId()) != null) {
				b.setChildrenName(menuService.findObjectById(b.getChildrenId()).getMenuName());
			}
		}
		model.addAttribute("bugs", bugs);
		return "foreground/resourcesmsg/bug/bugList";
	}
}
