package com.llc.springcloud.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	
	public static final String SUFFIX_XML = ".xml";
	
	public static String copyFileToTmpDir(InputStream in) {
		File tmp = null;
		String path = null;
		try {
			tmp = File.createTempFile(StringUtil.longToString(TimeUtil.getTimestamp()), SUFFIX_XML);
			FileOutputStream fos = new FileOutputStream(tmp);
			byte[] buf = new byte[1024];
			while (in.read(buf) > 0) {
				fos.write(buf);
			}
			fos.flush();
			path = tmp.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (tmp != null) {
				tmp.deleteOnExit();
			}
		}
		if (StringUtil.isNotBlank(path)) {
			return path;
		}
		return "";
	}
	
}
