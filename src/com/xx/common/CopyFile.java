package com.xx.common;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class CopyFile {


    /*
     * ʵ�֣����ļ�������Ŀ���ļ��У�����·��+�ļ���
     * ������sourcestr��Դ�ļ���ַ
     * 		deststr��Ŀ���ļ���ַ
     * ���ߣ�slj
     * ʱ�䣺2017-08-15
     */
	
	public void copyFile(String sourcestr,String deststr) throws IOException{
		File source=new File(sourcestr);
		File dest=new File(deststr);
		
		FileChannel inputchannel=null;
		FileChannel outputchannel=null;
		try{
			inputchannel=new FileInputStream(source).getChannel();
			outputchannel=new FileOutputStream(dest).getChannel();
			outputchannel.transferFrom(inputchannel, 0, inputchannel.size());
 		}
		finally{
			inputchannel.close();
			outputchannel.close();
		}
	}
	


}
