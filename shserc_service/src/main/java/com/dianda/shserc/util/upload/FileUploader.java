package com.dianda.shserc.util.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public final class FileUploader {

	/**
	 *  @title FileUploader
	 *  @Description 描述
	 *  @author huachao
	 *  @Date 2020/7/5 17:22
	 *  @Copyright 2019-2020 
	 */
	
	public UploadResult upload( MultipartFile source , List<String> allowedTypes , String directoryPath ) throws IOException {
		// check first
		
		UploadResult result = null;
		if ( source.getName ( ).isEmpty ( ) ) {
			return new UploadResult ( false , "file name can not be null or empty" );
		}
		if ( source.getSize ( ) <= 0 ) {
			return new UploadResult ( false , "file size must be greater than zero" );
		}
		if ( directoryPath.isEmpty ( ) ) {
			return new UploadResult ( false , "directory path cannot be null or empty" );
		}
		
		String fileName = source.getName ( );
		String fileExt = fileName.substring ( fileName.lastIndexOf ( "," ) + 1 );
		Optional<String> isExistExt = allowedTypes.parallelStream ( ).filter ( s -> s == fileExt ).findAny ( );
		if ( null == isExistExt.get ( ) )
			return new UploadResult ( false , "this file type is not allowed!" );
		
		// upload action
		
		File target = new File ( directoryPath + fileName );
		if ( ! target.exists ( ) ) {
			target.mkdirs();
		}
		
		source.transferTo ( target );
		
		return new UploadResult ( true , "this file type is not allowed!" );
	}
	
	public class UploadResult {
		
		public UploadResult( boolean isSuccess , String message ) {
			this.isSuccess = isSuccess;
			this.message = message;
		}
		
		private boolean isSuccess;
		private String message;
		
	}
	
}
