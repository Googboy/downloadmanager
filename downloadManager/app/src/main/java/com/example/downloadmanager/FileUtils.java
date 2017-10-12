package com.example.downloadmanager;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 潘硕 on 2017/10/12.
 */

class FileUtils {
    private String SDCardRoot;
    public FileUtils(){
        SDCardRoot = Environment.getExternalStorageDirectory()+File.separator;
    }
    public File createFileInSDCard(String filename,String dir)throws IOException{
        File file = new File(SDCardRoot+dir+File.separator+filename);
        file.createNewFile();
        return file;
    }
    public File createSDDir(String dir)throws IOException{
        File dirFile = new File(SDCardRoot+dir);
        dirFile.mkdir();
        return dirFile;
    }
    public boolean isFileExist(String fileName, String dir) {
        File file = new File(SDCardRoot+dir+File.separator+fileName);
        return file.exists();
    }

    public File write2SDFromInput(String fileName, String dir, InputStream inputStream) {
        File file = null;
        OutputStream output = null;
        try {
            createSDDir(dir);
            file = createFileInSDCard(fileName,dir);
            output = new FileOutputStream(file);
            byte buffer[] = new byte[4*1024];
            int temp;
            while ((temp= inputStream.read(buffer))!=-1){
                output.write(buffer,0,temp);
            }
            output.flush();
        }catch (Exception e){
            System.out.println("写数据异常:"+e);
        }
        finally {
            try {
                output.close();
            }catch (Exception e2){
                System.out.println(e2);
            }
        }
        return file;
    }
}
