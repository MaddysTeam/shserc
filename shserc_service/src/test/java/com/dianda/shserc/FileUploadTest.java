package com.dianda.shserc;

import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.basic.StringUtil;
import com.dianda.shserc.util.upload.UploadResult;
import com.dianda.shserc.util.upload.wangsu.IWangsuFileUploader;
import com.dianda.shserc.util.upload.wangsu.WangsuUploader;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest( classes = ServiceApplication.class )
@EnableAspectJAutoProxy( proxyTargetClass = true )
@RunWith( SpringRunner.class )
public class FileUploadTest {
	
	IWangsuFileUploader uploader;
	
	String filePath = "D:\\temp.txt";
	
	public FileUploadTest( ) {
	}
	
	@Test
	public void SliceUploadTestSuccess( ) throws FileNotFoundException {
		uploader = new WangsuUploader ( );
		File file = new File ( filePath );
		FileInputStream fs = new FileInputStream ( file );
		UploadResult result = uploader.sliceUpload ( file.getName ( ) , fs );
		
		Assert.isTrue ( !ObjectUtil.isNull ( result )
				&& result.isSuccess ()
				&& !StringUtil.IsNullOrEmpty (result.getFilePath ()  ) );
	}
	
	
}
