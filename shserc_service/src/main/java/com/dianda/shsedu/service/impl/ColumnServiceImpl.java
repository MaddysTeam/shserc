package com.dianda.shsedu.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shsedu.bean.ColumnSelectParam;
import com.dianda.shsedu.dto.EditColumnDto;
import com.dianda.shsedu.entity.Columns;
import com.dianda.shsedu.mapper.ColumnsMapper;
import com.dianda.shsedu.service.IColumnService;
import com.dianda.shsedu.vo.ColumnVo;
import com.dianda.shsedu.vo.ColumnVoList;
import org.springframework.stereotype.Service;

@DS("shsedu")
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnsMapper, Columns> implements IColumnService {

	@Override
	public ColumnVoList findByPhrase(ColumnSelectParam selectParam) {
		return null;
	}

	@Override
	public ColumnVo findById(long id) {
		return null;
	}

	@Override
	public boolean edit(EditColumnDto editColumnDto) {
		return false;
	}
}
