package com.dianda.common.util.basic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  @title Encoder
 *  @Description 描述
 *  @author huachao
 *  @Date 2021/3/3 22:20
 *  @Copyright 2019-2020
 */

public class EncoderUtil {
	
	public final static String SHA(String s){
		String encodeStr=StringUtil.empty ();
		
		try {
			MessageDigest  md= MessageDigest.getInstance ( "SHA" );
			byte[] bytes=s.getBytes ();
			md.update ( bytes );
			encodeStr=new BigInteger ( md.digest () ).toString (32);
		} catch ( NoSuchAlgorithmException e ) {
			e.printStackTrace ( );
		}
		
		return encodeStr;
	}
	
}
