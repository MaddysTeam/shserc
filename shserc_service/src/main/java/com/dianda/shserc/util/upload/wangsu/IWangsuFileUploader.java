package com.dianda.shserc.util.upload.wangsu;

import com.dianda.shserc.util.upload.IFileUploader;
import com.dianda.shserc.util.upload.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface IWangsuFileUploader extends IFileUploader {
	UploadResult sliceUpload( String fileKey, InputStream stream);
	UploadResult sliceUpload( MultipartFile file );
	
	
}
