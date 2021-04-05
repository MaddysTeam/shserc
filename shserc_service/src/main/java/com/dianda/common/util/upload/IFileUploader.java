package com.dianda.common.util.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileUploader {
	UploadResult upload( MultipartFile source ) throws IOException;
}

