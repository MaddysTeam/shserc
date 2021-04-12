package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shserc.entity.Package;
import com.dianda.shserc.entity.PackageOperation;
import org.apache.ibatis.annotations.Param;

/**
 * package Mapper interface
 *
 * @author huachao
 * @since 2020-10-05
 */
public interface PackageMapper extends BaseMapper<Package> {
	
	IPage<Package> selectPackages( IPage<Package> page , @Param( Constants.WRAPPER ) Wrapper<Package> wrapper );
	
	int addPackageResource( PackageOperation packageOperation );
	
	int deletePackageResource( PackageOperation packageOperation );
	
	
	int addView( PackageOperation operation );
	
	int addDownload( PackageOperation operation );
	
	int addFavorite( PackageOperation operation );
	
	int deleteFavorite(PackageOperation operation);
	
	PackageOperation selectFavorite(@Param( Constants.WRAPPER ) Wrapper<PackageOperation> wrapper );
	
	int addCommentCount( PackageOperation operation );
	
	int addStar( PackageOperation operation );
	
}
