package com.dianda.auth.controller;


import com.dianda.auth.service.IResUserService;
import com.dianda.auth.util.json.JsonResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huachao
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/auth/res-user")
public class ResUserController {

	@Resource
	IResUserService resUserService;
	
	@RequestMapping( method= RequestMethod.POST )
	public JsonResult findByPhrase(){
	   return null;
	}

}

