package com.stone.equipsys.core.common.util;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stone.equipsys.core.common.constant.Constant;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 字符串加密解密
 * @author Administrator
 *
 */
@SuppressWarnings("restriction")
public class GZipUtil
{

	private static Logger log = LoggerFactory.getLogger(GZipUtil.class);

	public static String gzipString(String primStr)
	{
		if (primStr == null || primStr.length() == 0)
		{
			return primStr;
		}
		ByteArrayOutputStream out = null;
		GZIPOutputStream gout = null;
		try
		{
			out = new ByteArrayOutputStream();
			gout = new GZIPOutputStream(out);
			gout.write(primStr.getBytes(Constant.TRANSFER_CHARSET));
			gout.flush();
		} catch (IOException e)
		{
			log.error("对字符串进行加压加密操作失败：", e);
			return null;
		} finally
		{
			if (gout != null)
			{
				try
				{
					gout.close();
				} catch (IOException e)
				{
					log.error("对字符串进行加压加密操作，关闭gzip操作流失败：", e);
				}
			}
		}
		return new BASE64Encoder().encode(out.toByteArray());
	}

	public static final String ungzipString(ServletInputStream stream) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null)
		{
			sb.append(line);
		}
		if (isBlank(sb))
		{
			return null;
		}
		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		GZIPInputStream gin = null;
		String decompressed = null;
		try
		{
			byte[] compressed = new BASE64Decoder().decodeBuffer(sb.toString());
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			gin = new GZIPInputStream(in);
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = gin.read(buffer)) != -1)
			{
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString(Constant.TRANSFER_CHARSET);
		} catch (IOException e)
		{
			log.error("对字符串进行解密解压操作失败：", e);
			decompressed = null;
		} finally
		{
			if (gin != null)
			{
				try
				{
					gin.close();
				} catch (IOException e)
				{
					log.error("对字符串进行解密解压操作，关闭压缩流失败：", e);
				}
			}
			if (in != null)
			{
				try
				{
					in.close();
				} catch (IOException e)
				{
					log.error("对字符串进行解密解压操作，关闭输入流失败：", e);
				}
			}
			if (out != null)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					log.error("对字符串进行解密解压操作，关闭输出流失败：", e);
				}
			}
		}
		return decompressed;
	}

}
