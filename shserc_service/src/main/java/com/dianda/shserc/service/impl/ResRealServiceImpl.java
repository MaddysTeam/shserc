package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.RealSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditRealDto;
import com.dianda.shserc.dto.RegistRealDto;
import com.dianda.shserc.dto.mappers.IEditRealMapper;
import com.dianda.shserc.entity.ResReal;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.mapper.ResRealMapper;
import com.dianda.shserc.service.IResRealService;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.basic.StringUtil;
import com.dianda.shserc.validators.NotNull;
import com.dianda.shserc.vo.ResRealVo;
import com.dianda.shserc.vo.ResRealVoList;
import com.dianda.shserc.vo.mappers.IRealVoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class ResRealServiceImpl extends ServiceImpl<ResRealMapper, ResReal> implements IResRealService {
	
	@Resource
	ResRealMapper mapper;
	
	
	@Override
	public boolean edit( @Valid @NotNull EditRealDto model ) {
		int result = 0;
		ResReal resReal = IEditRealMapper.INSTANCE.mapFrom ( model );
		ResRealVo resRealVo = findByIdCard ( resReal.getIdCard ( ) ); // search by id card
		String cardPassword = resReal.getCardPassword ( );
		if ( ! StringUtil.isNullOrEmpty ( cardPassword ) )
			resReal.setCardPassword ( com.dianda.shserc.util.basic.EncoderUtil.SHA ( cardPassword ) );
		
		if ( resReal.isNewOne ( ) && ObjectUtil.isNull ( resRealVo ) ) {
			result = mapper.insert ( resReal );
			return result > 0;
		} else {
			resReal.setCardPassword ( null );
			result = mapper.updateById ( resReal );
			return result >= 0;
		}
	}
	
	@Override
	public boolean register( RegistRealDto model ) {
		return false;
	}
	
	@Override
	public ResRealVo findById( long id ) {
		QueryWrapper<ResReal> queryWrapper = new QueryWrapper<> ( );
		queryWrapper.eq ( "r.id" , id );
		ResReal resReal = mapper.selectSingle ( queryWrapper );
		ResRealVo vo = ObjectUtil.isNull ( resReal ) ? null : IRealVoMapper.INSTANCE.mapFrom ( resReal );
		
		return vo;
	}
	
	@Override
	public ResRealVo findByIdCard( String idCard ) {
		QueryWrapper<ResReal> queryWrapper = new QueryWrapper<> ( );
		queryWrapper.eq ( "id_card" , idCard );
		ResReal resReal = mapper.selectOne ( queryWrapper );
		ResRealVo vo = ObjectUtil.isNull ( resReal ) ? null : IRealVoMapper.INSTANCE.mapFrom ( resReal );
		
		return vo;
	}
	
	@Override
	public ResRealVoList find( RealSelectParams params ) {
		QueryWrapper<ResReal> queryWrapper = new QueryWrapper<> ( );
		long companyId = params.getCompanyId ( );
		String phrase = params.getSearchPhrase ( );
		
		if ( companyId > 0 ) {
			queryWrapper = queryWrapper.eq ( "company_id" , companyId );
		}
		if ( ! StringUtil.isNullOrEmpty ( phrase ) ) {
			queryWrapper = queryWrapper.and ( wrapper ->
					wrapper.like ( "real_name" , phrase )
							.or ( )
							.like ( "id_card" , phrase )
							.or ( )
							.like ( "card_no" , phrase ) );
		}
		
		// get paged list data
		
		int current = params.getCurrent ( );
		int size = params.getSize ( );
		IPage<ResReal> page = new Page<> ( current , size );
		page = mapper.selectReal ( page , queryWrapper );
		List<ResReal> listData = page.getRecords ( );
		List<ResRealVo> listVoData = new ArrayList<> ( );
		for ( ResReal resReal : listData ) {
			listVoData.add ( IRealVoMapper.INSTANCE.mapFrom ( resReal ) );
		}
		
		ResRealVoList voList = new ResRealVoList ( );
		voList.setListData ( listVoData );
		voList.setCurrent ( current );
		voList.setSize ( size );
		voList.setTotal ( page.getTotal ( ) );
		
		return voList;
	}
	
}
