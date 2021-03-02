package com.dianda.shserc.controller;


import com.dianda.shserc.bean.BulletinSelectParams;
import com.dianda.shserc.service.IBulletinService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.BulletinVo;
import com.dianda.shserc.vo.BulletinVoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bulletin")
public class BulletinController extends BaseController {

	@Resource
	IBulletinService service;

	@PostMapping("/list")
	public JsonResult findByParam(BulletinSelectParams params) {
		BulletinVoList bulletinVoList = service.find(params);
		return JsonResult.success(bulletinVoList);
	}

	@PostMapping(path = "/{id}")
	public JsonResult findById(@PathVariable long id) {
		BulletinVo bulletinVo = service.findById(id);
		return JsonResult.success(bulletinVo);
	}


}
