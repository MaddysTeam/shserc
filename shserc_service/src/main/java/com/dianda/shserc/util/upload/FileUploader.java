package com.dianda.shserc.util.upload;

import com.dianda.shserc.common.Constant;
import com.dianda.shserc.util.basic.ObjectUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class FileUploader  implements IFileUploader {

	/**
	 *  @title FileUploader
	 *  @Description 描述
	 *  @author huachao
	 *  @Date 2020/7/5 17:22
	 *  @Copyright 2019-2020 
	 */
	
	public UploadResult upload( MultipartFile source) throws IOException {
		// check first
		String directoryPath= Constant.ThisApp.UPLOAD_FILE_PATH;
		String[] allowedTypes = Constant.ThisApp.ALLOW_FILE_TYPES;
		
		if( ObjectUtil.isNull ( source ) ){
			return new UploadResult ( false , Constant.Error.FILE_OBJECT_IS_REQUIRED );
		}
		
		UploadResult result = null;
		if ( source.getName ( ).isEmpty ( ) ) {
			return new UploadResult ( false , Constant.Error.FILE_NAME_IS_REQUIRED );
		}
		if ( source.getSize ( ) <= 0 ) {
			return new UploadResult ( false , Constant.Error.FILE_SIZE_IS_GREATER_THAN_ZERO);
		}
		if ( directoryPath.isEmpty ( ) ) {
			return new UploadResult ( false , Constant.Error.FILE_UPLOAD_DST_PATH );
		}
		
		String fileName = source.getName ( );
		String fileExt = fileName.substring ( fileName.lastIndexOf ( "," ) + 1 );
		Optional<String> isExistExt = Arrays.asList(allowedTypes).parallelStream ( ).filter ( s -> s == fileExt ).findAny ( );
		if ( ObjectUtil.isNull ( isExistExt.get ( ) ))
			return new UploadResult ( false , Constant.Error.FILE_TYPE_NOT_ALLOWED );
		
		// upload action
		
		File target = new File ( directoryPath + fileName );
		if ( ! target.exists ( ) ) {
			target.mkdirs();
		}
		
		source.transferTo ( target );
		
		return new UploadResult ( true , Constant.Success.UPLOADSUCCESS );
	}
	
}


