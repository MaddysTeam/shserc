package com.dianda.resource.mapper;

import com.dianda.resource.entity.ResourceInfo;
import com.dianda.resource.vo.ResourceInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Resource Mapper 接口
 *
 *
 * @author huachao
 * @since 2020-08-06
 */
public interface ResourceInfoMapper extends BaseMapper<ResourceInfo> {

	@Select("select r.id,r.resourceName,u.userName as userName from resource_info r left join user_info u on u.id=r.addUserId where u.id=#{userId}")
	public List<ResourceInfoVo>  resourceList( String userId);

}
