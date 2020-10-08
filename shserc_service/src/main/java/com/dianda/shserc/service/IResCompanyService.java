package com.dianda.shserc.service;

import com.dianda.shserc.dto.EditCompanyDto;
import com.dianda.shserc.entity.ResCompany;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.vo.ResCompanyVo;

/**
 * company service
 *
 * @author huachao
 * @since 2020-08-17
 */
public interface IResCompanyService extends IService<ResCompany> {
	
	ResCompanyVo find( );
	
	ResCompanyVo findChildren( long parentId );
	
	ResCompany edit( EditCompanyDto company, long opertaorId );
	
	ResCompany delete( long id );
	
}
