package com.dianda.common.util.upload;

import java.util.HashMap;
import java.util.Map;

import com.dianda.common.util.upload.wangsu.IWangsuFileUploader;
import com.dianda.common.util.upload.wangsu.WangsuUploader;
import org.springframework.stereotype.Service;


@Service
public class BasicUploaderProvider implements IFileUploaderProvider {
	
	static Map<String, IFileUploader> uploaderMap;
	
	public BasicUploaderProvider( ) {
		uploaderMap = new HashMap<> ( );
		this.setUploader ( WangsuUploader.class.getName ( ) , new WangsuUploader ( ) );
	}
	
	@Override
	public IFileUploader getUploader( String loader ) {
		return uploaderMap.get ( loader );
	}
	
	@Override
	public void setUploader( String name , IFileUploader loader ) {
		uploaderMap.put ( name , loader );
	}
	
	@Override
	public IWangsuFileUploader getWangsuUploader( ) {
		return (IWangsuFileUploader)getUploader (WangsuUploader.class.getName ( ) );
	}
	
}
