package com.dianda.shsedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shsedu.bean.ColumnSelectParam;
import com.dianda.shsedu.dto.EditColumnDto;
import com.dianda.shsedu.entity.Columns;
import com.dianda.shsedu.vo.ColumnVo;
import com.dianda.shsedu.vo.ColumnVoList;

public interface IColumnService extends IService<Columns> {

  ColumnVoList findByPhrase(ColumnSelectParam selectParam);

  ColumnVo findById(long id);

  boolean edit(EditColumnDto editColumnDto);
}
