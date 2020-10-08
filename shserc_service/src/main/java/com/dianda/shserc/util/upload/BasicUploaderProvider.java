package com.dianda.shserc.util.upload;

public class BasicUploaderProvider implements IFileUploaderProvider<FileUploader>{
	@Override
	public  FileUploader provide( ) {
		return new FileUploader();
	}
	
}
