package com.cbx;

import com.cbx.response.Ticker;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.OutputStream;
import java.net.URI;

/**
 * @author pjacobs
 */
public class CampBX {
    private String username;
    private String password;

    public CampBX(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Ticker ticker() {
        try {
            URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("CampBX.com")
                .setPath("/api/xticker.php")
                .build();

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(uri);
            HttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // Stream content out
                    OutputStream stream = new java.io.ByteArrayOutputStream();
                    entity.writeTo(stream);
                    System.out.println("stream.toString():"+stream.toString());
                }
            } finally {
                //response.close();
            }
        } catch(Exception e) {

        }

        return null;
    }

}
