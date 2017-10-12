package com.example.downloadmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 潘硕 on 2017/10/12.
 */

class HttpDownloader {
    String line = null;
    StringBuffer strBuffer = new StringBuffer();
    BufferedReader  bufferReader = null;
    public String downloadFiles(String urlStr) {
        try {
            InputStream inputStream = getInputStreamFromUrl(urlStr);
            bufferReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line=bufferReader.readLine())!=null){
                strBuffer.append(line+'\n');
            }
        }catch (Exception e){
            strBuffer.append("something is wrong!!");
            System.out.println("读取数据异常"+e);
        }finally {
            try {
                bufferReader.close();
            }catch (Exception e){
                strBuffer.append("something id wrong!!");
                e.printStackTrace();
            }
        }
        return strBuffer.toString();
    }

    private InputStream getInputStreamFromUrl(String urlStr) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = urlConn.getInputStream();
        return inputStream;
    }

    public int downloadFiles(String urlStr, String path, String fileName) {
        try {
            FileUtils fileUtils = new FileUtils();
            if (fileUtils.isFileExist(fileName,path))
                return 1;
            else {
                InputStream inputStream = getInputStreamFromUrl(urlStr);
                File resultFile = fileUtils.write2SDFromInput(fileName,path,inputStream);
                if (resultFile == null)
                    return -1;
            }
        } catch (Exception e) {
            System.out.println("读写数据异常:"+e);
            return -1;
        }
        return 0;
    }
}
