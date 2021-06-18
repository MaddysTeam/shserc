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
import com.dianda.common.common.Constant;
import org.apache.logging.log4j.core.appender.db.ColumnMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DS("shsedu")
@Service("ShseduColumnService")
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
		long ownerId=selectParam.getOwnerId();
		if(ownerId>0){
			wrapper.inSql("id", "select newsId from owned_columns where ownerId="+ownerId);
		}

		List<Columns> columnList = mapper.selectColumns ( wrapper );
		List<ColumnVo> voList = new ArrayList<>( );
		for ( Columns column : columnList ) {
			ColumnVo vo = IColumnVoMapper.INSTANCE.mapFrom ( column );
			voList.add(vo);
		}

		ColumnVoList columnVoList = new ColumnVoList ( );
		columnVoList.setListData ( voList );

		return columnVoList;
	}

	@Override
	public ColumnVo findById(long id) {
		Columns column= mapper.selectById(id);
		return IColumnVoMapper.INSTANCE.mapFrom(column);
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
