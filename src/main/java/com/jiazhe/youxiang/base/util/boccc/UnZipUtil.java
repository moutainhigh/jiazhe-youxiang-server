package com.jiazhe.youxiang.base.util.boccc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class UnZipUtil {
	
	public static void ZipContraFile(String inputFilePath , String outputDirectory) throws ZipException, IOException { 
		File file = new File(inputFilePath);
		File outzipFile = new File(outputDirectory);  
        if(!outzipFile.exists()){  
            outzipFile.mkdirs();  
        }
        byte[] buf = new  byte[1024];          
        File outFile = null;  
        ZipFile zipFile = new ZipFile(file);  
        ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));  
        ZipEntry entry = null;  
        InputStream input = null;  
        OutputStream output = null;  
        while((entry = zipInput.getNextEntry()) != null){  
            outFile = new File(outputDirectory + File.separator + entry.getName());  
              
            if(entry.isDirectory()){  
                outFile.mkdir();  
            }else{  
                  
                if(!outFile.getParentFile().exists()){  
                    outFile.getParentFile().mkdirs();  
                }  
                  
                outFile.createNewFile();  
                 input = zipFile.getInputStream(entry);  
                 output = new FileOutputStream(outFile);  
                 int temp = 0;  
                 while((temp = input.read(buf)) != -1){  
                     output.write(buf,0,temp);  
                 }  
                 input.close();  
                 output.close();  
            }  
        }  
        zipFile.close();  
        zipInput.close();  
	}
}
