package com.dianda.common.util.upload.wangsu;

import com.dianda.common.util.upload.IFileUploader;
import com.dianda.common.util.upload.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface IWangsuFileUploader extends IFileUploader {
	UploadResult sliceUpload( String fileKey, InputStream stream);
	UploadResult sliceUpload( MultipartFile file );
	
	
}
