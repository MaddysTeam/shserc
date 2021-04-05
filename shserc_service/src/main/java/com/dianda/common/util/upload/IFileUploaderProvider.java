package com.dianda.common.util.upload;

import com.dianda.common.util.upload.wangsu.IWangsuFileUploader;

import java.util.Map;

public interface IFileUploaderProvider{
	 Map<String, IFileUploader> uploaderMap = null;
	 IFileUploader getUploader (String loader);
	 void setUploader(String name,IFileUploader loader);
	 IWangsuFileUploader getWangsuUploader ();
}


