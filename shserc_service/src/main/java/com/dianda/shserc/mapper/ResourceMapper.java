package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
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
public interface ResourceMapper extends BaseMapper<Resource> {
	IPage<Resource> selectResources( IPage<Resource> page , @Param( Constants.WRAPPER ) Wrapper<Resource> wrapper );
	
	int addView( ResourceOperation operation );
	
	int addDownload( ResourceOperation operation );
	
	int addFavorite( ResourceOperation operation );

	int deleteFavorite(ResourceOperation operation);
	
	int addCommentCount( ResourceOperation operation );
	
	int addStar2( ResourceOperation operation );
	
	List<ResourceOperation> selectFavorites(@Param( Constants.WRAPPER ) Wrapper<ResourceOperation> wrapper );
	
	List<ResourceOperation> selectDownloads(@Param( Constants.WRAPPER ) Wrapper<ResourceOperation> wrapper );
	
	List<ResourceOperation> selectStars(@Param( Constants.WRAPPER ) Wrapper<ResourceOperation> wrapper );
}
