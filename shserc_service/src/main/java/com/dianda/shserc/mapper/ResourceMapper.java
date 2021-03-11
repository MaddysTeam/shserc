package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.entity.ResourceOperation;
import org.apache.ibatis.annotations.Param;

/**
 * resource mapper interface
 *
 * @author huachao
 * @since 2020-08-17
 */
public interface ResourceMapper extends BaseMapper<Resource> {
	IPage<Resource> selectResources( IPage<Resource> page , @Param( Constants.WRAPPER ) Wrapper<Resource> wrapper );
	
	int addView( ResourceOperation operation );
	
	int addDownload( ResourceOperation operation );
	
	int addFavorite( ResourceOperation operation );

	int deleteFavorite(ResourceOperation operation);

	ResourceOperation selectFavorite(@Param( Constants.WRAPPER ) Wrapper<ResourceOperation> wrapper );
	
	int addCommentCount( ResourceOperation operation );
	
	int addStar( ResourceOperation operation );
}
