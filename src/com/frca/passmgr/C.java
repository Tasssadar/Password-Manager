package com.frca.passmgr;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// class with constants and global methods
public final class C
{
	public static final String settingsFilename="settings.xml";
	
	public static final String hashText(String text)
	{
		MessageDigest m;
		try
		{
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		m.reset();
		try
		{
			m.update(text.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);

		while(hashtext.length() < 32 )
			hashtext = "0"+hashtext;
		return hashtext;
	}
}