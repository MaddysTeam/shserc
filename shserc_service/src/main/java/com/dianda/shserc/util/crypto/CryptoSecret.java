package com.dianda.shserc.util.crypto;


import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CryptoSecret {
	
	public final static String KEY = "1234567890123456";
	public final static String IV = "1234567890123456";
	
	public static String desEncrypt( String word ) {
		return desEncrypt ( word , KEY , IV );
	}
	
	public static String desEncrypt( String word , String key , String iv ) {
		try {
			byte[] sourceBytes = new Base64 ( ).decode ( word );
			Cipher cipher = Cipher.getInstance ( "AES/CBC/NoPadding" );
			SecretKeySpec keySpec = new SecretKeySpec ( key.getBytes ( ) , "AES" );
			IvParameterSpec ivSpec = new IvParameterSpec ( iv.getBytes ( ) );
			cipher.init ( Cipher.DECRYPT_MODE , keySpec , ivSpec );
			sourceBytes = cipher.doFinal ( sourceBytes );
			return new String ( sourceBytes ).trim ();
			
		} catch ( Exception e ) {
			e.printStackTrace ( );
			return null;
		}
	}
	
}
