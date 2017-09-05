package com.tim.cake.online.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;


public class MD5Util
{
	public final static String MD5(String s)
	{
		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try
		{
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static String getMd5ByFile(File file) throws FileNotFoundException
	{
		String value = null;
		FileInputStream in = new FileInputStream(file);
		try
		{
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public static void main(String[] args)
	{//e4774cdda0793f86414e8b9140bb6db4
		System.out.println(MD5Util.MD5("t2"));//E4774CDDA0793F86414E8B9140BB6DB4
		System.out.println(MD5Util.MD5("1{0f826a89cf68c399c5f4cf320c1a5842}"));
	}
}
