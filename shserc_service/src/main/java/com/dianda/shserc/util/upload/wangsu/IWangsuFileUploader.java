package com.dianda.shserc.util.upload.wangsu;

import com.dianda.shserc.util.upload.IFileUploader;

import java.io.InputStream;

interface IWangsuFileUploader extends IFileUploader {
	void sliceUpload(String bucket, InputStream stream);
}
