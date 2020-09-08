package com.dianda.auth.service.impl;

import com.dianda.auth.dto.EditCompanyDto;
import com.dianda.auth.entity.ResCompany;
import com.dianda.auth.mapper.ResCompanyMapper;
import com.dianda.auth.service.IResCompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.auth.vo.ResCompanyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huachao
 * @since 2020-08-17
 */
@Service
public class ResCompanyServiceImpl extends ServiceImpl<ResCompanyMapper, ResCompany> implements IResCompanyService {
	
	@Resource
	ResCompanyMapper resCompanyMapper;
	
	@Override
	public ResCompanyVo find( ) {
		//  step 1 : find all
		//  step 2: return hierarchy data
		
		List<ResCompany> results = resCompanyMapper.selectList ( null );
		
		return null;
	}
	
	@Override
	public ResCompanyVo findByParentId( long parentId ) {
		return null;
	}
	
	@Override
	public ResCompany edit( EditCompanyDto company ) {
		return null;
	}
	
	@Override
	public ResCompany delete( long id ) {
		return null;
	}
}
