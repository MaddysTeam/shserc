package com.dianda.shserc.util.upload;

import lombok.Data;

@Data
public class UploadResult {
	
	public UploadResult( ) {
	}
	
	public UploadResult( boolean isSuccess , String message ) {
		this.isSuccess = isSuccess;
		this.message = message;
	}
	
	public UploadResult( boolean isSuccess , String message , String filePath ) {
		this.isSuccess = isSuccess;
		this.message = message;
		this.filePath = filePath;
	}
	
	private boolean isSuccess;
	private String message;
	private String filePath;
	private String fileName;
	private String fileExtName;
	private long fileSize;
	
}
