package com.znergia.sinsacatalog;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;

/**
 * Created by reneval on 7/12/13.
 */
public class RetrieveWebPage extends AsyncTask<String, Integer, Boolean> {
    private Document doc;
    private int responseCode;
    private OnResponseListener responder;
    private Connection.Response response = null;

    public RetrieveWebPage(OnResponseListener responder){
        this.responder = responder;
    }

    @Override
    protected Boolean doInBackground(String... urls){
        int desiredCode = 200;
        responseCode = 0;
        try {
            String url = urls[0];
            response = Jsoup.connect(url).execute();

            responseCode = response.statusCode();

            if(responseCode == desiredCode){
                doc = response.parse();
            }

        }catch (Exception e){
            responseCode = 400;
            System.out.println(e.getMessage());
        }

        return responseCode == desiredCode;
    }

    @Override
    protected void onPostExecute(Boolean result){
        //onPostExecute(data);
        if(result){
            responder.onSuccess(doc);
        }
        else {
            responder.onFailure();
        }

    }

    public interface OnResponseListener {
        public void onSuccess(Document doc);
        public void onFailure();
    }
}
