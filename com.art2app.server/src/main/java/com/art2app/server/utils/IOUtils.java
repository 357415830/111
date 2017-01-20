package com.art2app.server.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.art2app.server.create.GenerateService;

/**
 * BtyeArray and File conversion Utils
 * @author yt
 *
 */
public class IOUtils {
	private static Logger logger = LogManager.getLogger(IOUtils.class);
	/**
	 * convert The ByteArray into the File
	 * 
	 * @param data
	 * @param filePath
	 * @param fileName
	 * @return File
	 * @throws IOException 
	 */
	public static File writeByteArrayToFile(byte[] data, String filePath, String fileName) throws FileNotFoundException, IOException  {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists()) {// To determine whether a file directory exists
				dir.mkdirs();
			}
			file = new File(filePath + "/" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(data);
		} catch (FileNotFoundException e) {
			logger.error(e);
			throw e;
		} catch (IOException e1) {
			logger.error(e1);
			throw e1;			
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					logger.error(e1);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					logger.error(e1);
				}
			}
		}

		return file;

	}

	/**
	 * convert the File into the ByteArray
	 * @param filePath
	 * @return byte[]
	 * @throws FileNotFoundException, 
	 */
	public static byte[] readFileToByteArray(String filePath) throws FileNotFoundException, IOException{
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			logger.error(e);
			throw e;
		} catch (IOException e1) {
			logger.error(e1);
			throw e1;
		}
		return buffer;
	}

}
