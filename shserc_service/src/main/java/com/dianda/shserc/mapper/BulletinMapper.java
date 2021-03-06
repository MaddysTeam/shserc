package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shserc.entity.Bulletin;
import org.apache.ibatis.annotations.Param;

public interface BulletinMapper extends BaseMapper<Bulletin> {

  IPage<Bulletin> selectBulletins(IPage<Bulletin> page,@Param(Constants.WRAPPER) Wrapper<Bulletin> wrapper);

}
