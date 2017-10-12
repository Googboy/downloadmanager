package com.example.downloadmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    Button btn1,btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btnDownloadXml);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btnDonloadMp3);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn1){
            new downloadFileThread().start();
        }else{
            new downloadMP3Thread().start();
        }
    }

    private class downloadFileThread extends Thread {
        public void run(){
            HttpDownloader httpDownloader = new HttpDownloader();
            String fileData = httpDownloader.downloadFiles("http://mystudy.bj.bcebos.com/AndroidDemo_009.xml");
            System.out.println(fileData);
        }
    }

    private class downloadMP3Thread extends Thread{
        @Override
        public void run() {
            HttpDownloader httpDownload = new HttpDownloader();
            int downloadResult = httpDownload.downloadFiles("http://fengkui.bj.bcebos.com/%E8%B6%B3%E9%9F%B3.mp3","BoBoMusic","足音.mp3");
            System.out.println("下载结果:"+downloadResult);
        }
    }
}
