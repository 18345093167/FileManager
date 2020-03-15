package com.example.uploadingfiles.common;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static  boolean createDir(String destDirName) {   
		File dir = new File(destDirName);
		if (dir.exists()) {   
			System.out.println("创建目录" + destDirName + "失败，目标目录已经存在"); 
			File[] files = dir.listFiles();
			if (files.length >= 1){
				for (File fileToDel:files){
					fileToDel.delete();
				}
				
			}
			return false;  
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator; 
		}
		//创建目录   
		if (dir.mkdirs()) {
			System.out.println("创建目录" + destDirName + "成功！");
			return true;
			} else {
				System.out.println("创建目录" + destDirName + "失败！");
				return false; 
			}
	}
	

	
	//创建文件
	//（1）判断文件是否已经存在 （2）判断是不是目录（4）判断目录是否存在，不存在则创建（5）创建目标文件  
	public static boolean createNewFile(String destFileName) {
		File file = new File(destFileName);  
		if(file.exists()) {            
			return false;  
		}        
		if (destFileName.endsWith(File.separator)) {
			return false; 
		}
		//判断目标文件所在的目录是否存在 
		if(!file.getParentFile().exists()) {
			//如果目标文件所在的目录不存在，则创建父目录  
			if(!file.getParentFile().mkdirs()) {
				return false;   
			}
		}        
		//创建目标文件 
		try {
			
			if (file.createNewFile()) {
				return true;  
			}
			else { 
				return false;     
			}
		} catch (IOException e) {
				e.printStackTrace();
				return false;
		} 
	}

}