package com.dianda.shserc.controller;


import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.util.upload.BasicUploaderProvider;
import com.dianda.shserc.util.upload.FileUploader;
import com.dianda.shserc.util.upload.UploadResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping( "/file" )
public class FileController {
	
	@RequestMapping( value = "/upload", method = RequestMethod.POST )
	public JsonResult UploadFile( @RequestBody MultipartFile file ) throws IOException {
		FileUploader uploader = new BasicUploaderProvider ( ).provide ( );
		UploadResult result = uploader.upload ( file );
		
		return JsonResult.success ( result );
	}
	
	@RequestMapping( value = "/uploadCover", method = RequestMethod.POST )
	public JsonResult UploadCover( @RequestBody MultipartFile file ) {
		return null;
	}
	
}
