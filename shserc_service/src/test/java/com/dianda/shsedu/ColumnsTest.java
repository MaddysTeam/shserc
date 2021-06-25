package com.dianda.shsedu;

import com.dianda.ServiceApplication;
import com.dianda.shsedu.dto.EditColumnDto;
import com.dianda.shsedu.service.IColumnService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = ServiceApplication.class)
@RunWith(SpringRunner.class)
public class ColumnsTest {

	@Resource
	IColumnService columnService;
	String title="一帮痴呆";

	@Test
	public void edit_column_test(){
		EditColumnDto editColumnDto=new EditColumnDto();
		editColumnDto.setMenu(false);
		editColumnDto.setParentId(0);
		editColumnDto.setTitle(title);

		boolean result=columnService.edit(editColumnDto);
		Assert.assertTrue ( result);
	}

}
