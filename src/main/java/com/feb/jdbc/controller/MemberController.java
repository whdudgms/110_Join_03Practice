package com.feb.jdbc.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feb.jdbc.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	
	@GetMapping("index.do")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("resultMsg","indexOk");

		mv.setViewName("login");
		return mv;
	}
	/**
	 * 
	 * @param params meberName, passwd
	 * @param response
	 * @return result메세지를 포함한 mv
	 */
	@PostMapping("join.do")
	public ModelAndView join(@RequestParam HashMap<String,String> params, HttpServletResponse response) {
		System.out.println(params);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		if(memberService.join(params)) {
			mv.addObject("resultMsg","joinOk");
		}else {
			mv.addObject("resultMsg","joinFail");
		}
		return mv;
	}
	
	/**
	 * 
	 * @param params  memberName passwd1  passwd2
	 * @param response 
	 * @return result메세지를 포함한 mv
	 */

	@PostMapping("login.do")
	public ModelAndView login(@RequestParam HashMap<String,String> params, HttpServletResponse response) {
		System.out.println(params);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		if(memberService.login(params)) {
			mv.addObject("resultMsg","loginOk");
		}else {
			mv.addObject("resultMsg","loginFail");
		}
		return mv;
	}
	
}
