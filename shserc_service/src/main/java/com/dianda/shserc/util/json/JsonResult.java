package com.dianda.shserc.util.json;


import com.alibaba.fastjson.JSONObject;

/**
 * @author hauchao
 * @title JsonResult
 * @Description json response
 * @Date 2020/2/23 10:51
 * @Copyright 2019-2020
 */

public class JsonResult<Data> {
	
	Data data;
	String message;
	String status;
	int resultCode;
	
	public JsonResult( int code, Data data, String status, String message) {
		this.data = data;
		this.message = message;
		this.status=status;
		this.resultCode=code;
	}
	
	public JsonResult(int code, String status, String message) {
		this.message = message;
		this.status = status;
		this.resultCode=code;
	}
	
	public static final JsonResult success() {
		return new JsonResult(200,StatusType.OK.getStatus(), "message");
	}
	
	public static final JsonResult success( String message) {
		return new JsonResult(200,StatusType.OK.getStatus(), message);
	}
	
	public static <T> JsonResult<T> success(T data) {
		String json= JSONObject.toJSONString ( data );
		return new JsonResult(200, json, StatusType.OK.getStatus(), "success");
	}
	
	public static <T> JsonResult<T> success(T data, String message) {
		String json= JSONObject.toJSONString ( data );
		return new JsonResult(200,data, StatusType.OK.getStatus(), message);
	}
	
	public static final JsonResult error() {
		return new JsonResult(500,StatusType.ERROR.getStatus(), "error");
	}
	
	public static final JsonResult error( String message) {
		return new JsonResult(500,StatusType.ERROR.getStatus(), message);
	}
	
	public static final JsonResult unAuthorized( String message){
		return new JsonResult(401,StatusType.UNAUTHORIZED.getStatus(), message);
	}
	
	public Data getData() {
		return data;
	}
	
	public void setData(Data data) {
		this.data = data;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus( String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage( String message) {
		this.message = message;
	}
	
	public int getResultCode( ) {
		return resultCode;
	}
	
	public void setResultCode( int resultCode ) {
		this.resultCode = resultCode;
	}
	
}

