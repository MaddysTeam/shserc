package com.dianda.shserc;

import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.service.IResourceService;
import com.dianda.shserc.vo.ResourceVo;
import com.dianda.shserc.vo.ResourceVoList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest( classes = ServiceApplication.class )
@RunWith( SpringRunner.class )
public class ResourceTest {
	
	@Resource
	IResourceService service;
	
	int current = 1;
	int size = 2;
	//String phrase = "jimmyPoor";
	long resourceId = 1;
	long userId = 1;
	long zero = 0;
	
	@Test
	public void get_page_data_test( ) {
		ResourceSelectParams params = new ResourceSelectParams ( );
		params.setSize ( this.size );
		params.setCurrent ( this.current );
		
		ResourceVoList voList = service.find ( params );
		
		Assert.assertNotNull ( voList.getListData ( ) );
		Assert.assertTrue ( voList.getListData ( ).size ( ) > 0 );
		Assert.assertNotNull ( voList );
	}

	@Test
	public void edit_resource_view_count_test( ){
		editResourceViewCountTest();
	}
	

	private void editResourceViewCountTest( ) {
		try {
			service.addViewCount ( new ResourceOperation ( resourceId , userId ) );  // when success
		}catch ( GlobalException e ){  // throw global exception
			com.dianda.shserc.vo.ResourceVo resource = service.findById ( resourceId );
			Assert.assertTrue ( resource.getViewCount ( ) == 1 );
		}
	}
	
}
