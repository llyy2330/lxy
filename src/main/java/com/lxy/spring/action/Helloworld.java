package com.lxy.spring.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/helloworld")
public class Helloworld {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("helloworld");
		return mv;
	}

	@RequestMapping(value = "/test",method = RequestMethod.POST)
	@ResponseBody
	public String helloTest() {
		
		return "ok";
	}

}