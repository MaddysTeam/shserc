package com.dianda.resource;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dianda.resource.entity.ResourceInfo;
import com.dianda.resource.entity.UserInfo;
import com.dianda.resource.mapper.ResourceInfoMapper;
import com.dianda.resource.mapper.UserInfoMapper;
import com.dianda.resource.vo.ResourceInfoVo;
import com.dianda.core.mybatis.CustomIdGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ResourceApplication.class)
public class ResourceTest {
	
	@Resource
	ResourceInfoMapper resourceMapper;

	//@Resource
	//CustomIdGenerator customIdGenerator;

	private String userId="f6382011-356e-4745-9893-3ee4e862f95d";
	
	@Test
	public void InsertResourceTest(){
		ResourceInfo resource =new ResourceInfo();
		resource.setResourceName("神秘宝藏");
		resource.setAddUserId(userId);
		resource.setId(UUID.randomUUID ().toString ());

		resourceMapper.insert(resource);
	}

	@Test
	@DS("source1")
	public void SelectResourceListTest(){
		List<ResourceInfoVo> results= resourceMapper.resourceList(userId);
		Assert.assertEquals(1, results.size());
	}


	@Test
	public void SelectPageResourceListTest(){
       int page=1;
       int pageSize=5;
	}

}
