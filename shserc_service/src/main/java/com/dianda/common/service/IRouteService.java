package com.dianda.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.common.entity.Route;
import com.dianda.common.vo.RouteVo;

import java.util.List;

public interface IRouteService {

	RouteVo getRoute(long rootId,long[] ownIds);

	List<RouteVo> getRoutes(long parentId);
}
