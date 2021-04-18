package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shserc.entity.CroResource;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.entity.ResourceOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * resource mapper interface
 *
 * @author huachao
 * @since 2020-08-17
 */
public interface CroResourceMapper extends BaseMapper<CroResource> {
	IPage<CroResource> selectCroResources( IPage<CroResource> page , @Param( Constants.WRAPPER ) Wrapper<CroResource> wrapper );
	
	int addView( ResourceOperation operation );
	
	int addDownload( ResourceOperation operation );
	
	int addFavorite( ResourceOperation operation );

	int deleteFavorite( ResourceOperation operation );
	
	int addCommentCount( ResourceOperation operation );
	
	int addStar( ResourceOperation operation );
	
	List<ResourceOperation> selectFavorite( @Param( Constants.WRAPPER ) Wrapper<ResourceOperation> wrapper );
	
	List<ResourceOperation> selectStar( @Param( Constants.WRAPPER ) Wrapper<ResourceOperation> wrapper );
}
