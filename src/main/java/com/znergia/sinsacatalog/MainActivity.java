package com.znergia.sinsacatalog;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import com.znergia.sinsacatalog.RetrieveWebPage.OnResponseListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrieveWebPage webPage = new RetrieveWebPage(onResponseListener);
        webPage.execute("http://www.sinsa.com.ni");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    protected OnResponseListener onResponseListener = new OnResponseListener(){
        public void onSuccess(Document doc){
            TextView collect = (TextView) findViewById(R.id.collected);
            collect.setText(doc.title());
        }

        public void onFailure(){
            TextView collect = (TextView) findViewById(R.id.collected);
            collect.setText("Failure");
        }
    };
}
