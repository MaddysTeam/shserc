package com.dianda.shserc.util.upload.wangsu;

import com.chinanetcenter.api.entity.PutPolicy;
import com.chinanetcenter.api.exception.WsClientException;
import com.chinanetcenter.api.sliceUpload.JSONObjectRet;
import com.chinanetcenter.api.util.DateUtil;
import com.chinanetcenter.api.wsbox.SliceUploadResumable;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.upload.UploadResult;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;


public class WangsuUploader implements IWangsuFileUploader {
	
	public WangsuUploader( ) {
	}
	
	@Override
	public UploadResult upload( MultipartFile source ) throws IOException {
		WangsuConfig.init ( );
		
		return null;
	}
	
	
	@Override
	public UploadResult sliceUpload( String fileKey , InputStream streams ){
		WangsuConfig.init ( );
		String bucket = WangsuConfig.BUCKET;
		PutPolicy policy = new PutPolicy ( );
		policy.setOverwrite ( 1 );
		policy.setReturnBody ( WangsuConfig.RETURN_BODY_PATTERN );
		policy.setScope ( bucket + ":" + fileKey );
		policy.setDeadline ( String.valueOf ( DateUtil.nextDate ( 1 , new Date ( ) ).getTime ( ) ) );
		
		UploadResult result = new UploadResult ( );
		JSONObjectRet jsonObjectRet = getJSONObjectRet ( bucket , result );
		SliceUploadResumable sliceUploadResumable = new SliceUploadResumable ( );
		sliceUploadResumable.execUpload ( bucket , fileKey , streams , policy , jsonObjectRet );
		
		return result;
	}
	
	@Override
	public UploadResult sliceUpload( MultipartFile file ) {
		UploadResult result = new UploadResult ( );
		if ( ! ObjectUtil.isNull ( file ) ) {
			String fileKey = file.getOriginalFilename ( );
			try {
				InputStream stream = file.getInputStream ( );
				result = sliceUpload ( fileKey , stream );
				return result;
			} catch ( IOException e ) {
				result.setSuccess ( false );
				result.setMessage ( e.getMessage () );
			}
		}
		else{
			result.setSuccess ( false );
			result.setMessage ( WangsuConfig.ErrorMessage.FAIL );
		}
		
		return result;
	}
	
	public JSONObjectRet getJSONObjectRet( final String bucket , final UploadResult result ) {
		return new JSONObjectRet ( ) {
			
			@Override
			public void onSuccess( JsonNode jsonNode ) {
				result.setSuccess ( true );
				result.setFilePath (  jsonNode.findValue( "url" ).textValue ());
				result.setFileName ( jsonNode.findValue ( "fname" ).textValue () );
			}
			
			@Override
			public void onSuccess( byte[] bytes ) {
				System.out.println ( );
			}
			
			@Override
			public void onFailure( Exception e ) {
				result.setSuccess ( false);
				result.setMessage ( e.getMessage () );
			}
			
			@Override
			public void onProcess( long l , long l1 ) {
			
			}
			
			@Override
			public void onPersist( JsonNode jsonNode ) {
			
			}
		};
	}
	
}
