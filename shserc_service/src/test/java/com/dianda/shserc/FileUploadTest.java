package com.dianda.shserc;

import com.dianda.ServiceApplication;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.basic.StringUtil;
import com.dianda.common.util.upload.UploadResult;
import com.dianda.common.util.upload.wangsu.IWangsuFileUploader;
import com.dianda.common.util.upload.wangsu.WangsuUploader;
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
	public void upload_test(){
		this.sliceUploadTestSuccess();
	}

	private void sliceUploadTestSuccess( ) {
		uploader = new WangsuUploader ( );
		File file = new File ( filePath );
		FileInputStream fs = null;
		try {
			fs = new FileInputStream( file );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		UploadResult result = uploader.sliceUpload ( file.getName ( ) , fs );
		
		Assert.isTrue ( !ObjectUtil.isNull ( result )
				&& result.isSuccess ()
				&& !StringUtil.isNullOrEmpty (result.getFilePath ()  ) );
	}
	
	
}
