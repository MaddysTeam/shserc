package com.dianda.shserc.service;

import com.dianda.shserc.dto.EditCompanyDto;
import com.dianda.shserc.entity.ResCompany;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.vo.ResCompanyVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author huachao
 * @since 2020-08-17
 */
public interface IResCompanyService extends IService<ResCompany> {
	
	ResCompanyVo find( );
	
	ResCompanyVo findByParentId( long parentId );
	
	ResCompany edit( EditCompanyDto company );
	
	ResCompany delete( long id );
	
}
