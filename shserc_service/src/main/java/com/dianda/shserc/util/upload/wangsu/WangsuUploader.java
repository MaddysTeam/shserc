package com.dianda.shserc.util.upload.wangsu;

import com.chinanetcenter.api.entity.PutPolicy;
import com.chinanetcenter.api.entity.SliceUploadHttpResult;
import com.chinanetcenter.api.exception.WsClientException;
import com.chinanetcenter.api.sliceUpload.JSONObjectRet;
import com.chinanetcenter.api.util.Config;
import com.chinanetcenter.api.util.DateUtil;
import com.chinanetcenter.api.wsbox.SliceUploadResumable;
import com.dianda.shserc.util.upload.IFileUploader;
import com.dianda.shserc.util.upload.UploadResult;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class WangsuUploader implements IWangsuFileUploader {

	@Override
	public UploadResult upload(MultipartFile source) throws IOException {
		WangsuConfig.init();

		return null;
	}


	@Override
	public void sliceUpload(String bucket, InputStream streams) {
		WangsuConfig.init();

		PutPolicy policy = new PutPolicy();
		policy.setOverwrite(1);
		policy.setReturnBody(WangsuConfig.RETURN_BODY_PATTERN);
		policy.setScope(WangsuConfig.BUCKET);
		policy.setDeadline(WangsuConfig.DEADLINE);
		JSONObjectRet jsonObjectRet= getJSONObjectRet(bucket);
		SliceUploadResumable sliceUploadResumable=new SliceUploadResumable();
		sliceUploadResumable.execUpload(bucket,null,streams, policy, jsonObjectRet);
	}

	public JSONObjectRet getJSONObjectRet(final String bucket) {
		return new JSONObjectRet( ){
			@Override
			public void onSuccess (JsonNode jsonNode){

			}

			@Override
			public void onSuccess ( byte[] bytes){

			}

			@Override
			public void onFailure (Exception e){
				if(e instanceof WsClientException){
					//TODO:
				}

			}

			@Override
			public void onProcess(long l, long l1) {

			}

			@Override
			public void onPersist (JsonNode jsonNode){

			}
		} ;
	}

}
