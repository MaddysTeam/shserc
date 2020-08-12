package com.dianda.auth.controller;


import com.dianda.auth.service.IResUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}

