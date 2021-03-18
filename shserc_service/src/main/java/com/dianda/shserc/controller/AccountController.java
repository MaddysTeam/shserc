package com.dianda.shserc.controller;


import com.dianda.shserc.dto.ChangePasswordDto;
import com.dianda.shserc.dto.LoginDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.service.IAccountService;
import com.dianda.shserc.service.IResRoleService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.ResUserVo;
import com.dianda.shserc.vo.mappers.IUserVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Account controller
 *
 * @author huachao
 * @since 2020-08-05
 */
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

	@Autowired
	IAccountService accountService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult login(@RequestBody @Valid @Validated LoginDto loginDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return JsonResult.error("validate error");
		}
		//try to login
		loginDto = accountService.login(loginDto);
		if (loginDto.getIsSuccess()) {
			// get login user info
			ResUserVo resUserVo = IUserVoMapper.INSTANCE.mapFrom(super.getLoginUserInfo());
			//set token to user vo and transfer to frontend
			resUserVo.setToken(loginDto.getToken());

			return JsonResult.success(resUserVo, "success");
		} else {
			return JsonResult.error(loginDto.getMessage());
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	//@RequiresRoles( "admin" )
	public JsonResult logout() {
		boolean result = accountService.logout();
		if (result)
			return JsonResult.success();
		else
			return JsonResult.error();
	}

	public JsonResult changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return JsonResult.error();
		}

		changePasswordDto = accountService.changePassword(changePasswordDto);
		if (changePasswordDto.getIsSuccess()) {
			return JsonResult.success(changePasswordDto, "success");
		} else {
			return JsonResult.error(changePasswordDto.getMessage());
		}
	}

}

