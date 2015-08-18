package com.palm.lingcai.controller;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.palm.lingcai.entity.TestDemo;
import com.palm.lingcai.service.TestDemoService;

@Controller
@RequestMapping(value = "")
public class TestDemoController {
	private static Logger logger = LoggerFactory.getLogger(TestDemoController.class);
	@Autowired
	private TestDemoService testDemoService;

	/**
	 * 页面跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("meetingContent", testDemoService.get(id));
		model.addAttribute("action", "update");
		return "meetingContent/meetingContentForm";
	}

	/**
	 * 页面重定向跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		testDemoService.get(id);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/meetingContent";
	}

	/**
	 * ajax 返回参数
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/testAjax")
	public String testAjax(HttpServletRequest request) {
		return "";
	}

	/**
	 * ajax 返回参数
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getInteractionCount")
	public String getInteractionCount(@RequestParam("id") Long id, ServletRequest request) {
		Object count = testDemoService.get(id);
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("result", true);
		resultMap.put("count", count);
		return JSON.toJSONString(resultMap);
	}

	@ResponseBody
	@RequestMapping("/testMem")
	public String testMemcached() {
		testDemoService.aboutMem();
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/testRedis")
	public String testRedis() {
		testDemoService.aboutRedis();
		return "";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("testDemo", new TestDemo());
		model.addAttribute("action", "create");
		return "testDemo/testDemoForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid TestDemo newTestDemo, RedirectAttributes redirectAttributes) {
		testDemoService.insert(newTestDemo);
		redirectAttributes.addFlashAttribute("message", "创建成功");
		return "redirect:/admin/testDemo";
	}

	@RequestMapping(value = "update2/{id}", method = RequestMethod.GET)
	public String updateForm2(@PathVariable("id") Long id, Model model) {
		model.addAttribute("testDemo", testDemoService.get(id));
		model.addAttribute("action", "update");
		return "testDemo/testDemoForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("testDemo") TestDemo testDemo, RedirectAttributes redirectAttributes) {
		testDemoService.update(testDemo);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/admin/testDemo";
	}

	@RequestMapping(value = "delete2/{id}")
	public String delete2(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		testDemoService.delete(id);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/testDemo";
	}
}
