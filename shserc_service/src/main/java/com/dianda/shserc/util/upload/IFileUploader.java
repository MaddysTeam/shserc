package com.dianda.shserc.util.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileUploader {
	UploadResult upload( MultipartFile source ) throws IOException;
}

