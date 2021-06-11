package com.dianda.shsedu.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.cache.dictionary.DictionaryCache;
import com.dianda.shsedu.bean.ColumnSelectParam;
import com.dianda.shsedu.dto.EditColumnDto;
import com.dianda.shsedu.dto.mappers.IEditColumnMapper;
import com.dianda.shsedu.entity.Columns;
import com.dianda.shsedu.mapper.ColumnsMapper;
import com.dianda.shsedu.service.IColumnService;
import com.dianda.shsedu.vo.ColumnVo;
import com.dianda.shsedu.vo.ColumnVoList;
import com.dianda.shsedu.vo.mappers.IColumnVoMapper;
import com.dianda.shserc.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DS("shsedu")
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnsMapper, Columns> implements IColumnService {

	@Resource
	ColumnsMapper mapper;

	DictionaryCache cache;

	@Autowired
	public ColumnServiceImpl(DictionaryCache cache) {
		this.cache = cache;
	}

	@Override
	public ColumnVoList findByPhrase(ColumnSelectParam selectParam) {
		QueryWrapper<Columns> wrapper = new QueryWrapper<> ( );
//		long roleId = menuSelectParams.getRoleId ( );
//		if ( roleId > 0 ) {
//			wrapper.eq ( "role_id" , roleId );
//		}

		//ArrayList<ResRoleVo> roleVoList = new ArrayList ( );
		List<Columns> columnList = mapper.selectColumns ( wrapper );
		List<ColumnVo> voList = new ArrayList<>( );
		for ( Columns column : columnList ) {
			ColumnVo vo = IColumnVoMapper.INSTANCE.mapFrom ( column );
			//vo.setRoles ( new ArrayList<> (  ) );

			boolean exist = voList.stream ( ).anyMatch ( x -> x.getId ( ) == column.getId ( ) );
			if(!exist){
				voList.add ( vo );
			}
			else{
				vo = voList.stream ( ).filter ( x -> x.getId ( ) == column.getId ( ) ).findFirst ( ).get ( );
			}

//			if ( column.getRoleId ( ) > 0 ) {
//				ResRoleVo roleVo = new ResRoleVo ( );
//				roleVo.setId ( menu.getRoleId ( ) );
//				roleVo.setRoleName ( menu.getRoleName ( ) );
//				vo.getRoles ().add ( roleVo );
//			}
		}

		ColumnVoList columnVoList = new ColumnVoList ( );
		columnVoList.setListData ( voList );

		return columnVoList;
	}

	@Override
	public ColumnVo findById(long id) {
		return null;
	}

	@Override
	public boolean edit(EditColumnDto editColumnDto) {
		Columns column = IEditColumnMapper.INSTANCE.mapFrom ( editColumnDto );
		Columns parent = mapper.selectById ( column.getParentId ( ) ); // check  whether the parent menu is  exists or not
		if ( column.getParentId ( ) > 0 && ObjectUtil.isNull ( parent ) ) {
			throw new GlobalException( Constant.ErrorCode.LOGIC_RESULT_INVALID , Constant.Error.PARENT_MENU_IS_NOT_EXISTS );
		}

		boolean result;
		if ( column.isNewOne ( ) ) {
			column.setAddTime ( LocalDateTime.now ( ) );
			result = mapper.insert ( column ) > 0;
		} else {
			column.setUpdateTime ( LocalDateTime.now ( ) );
			result = mapper.updateById ( column ) >= 0;
		}

		return result;
	}

}
