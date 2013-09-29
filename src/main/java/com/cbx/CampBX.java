package com.cbx;

import com.cbx.response.Depth;
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
    private HttpClient httpClient;
    private URI baseURI;

    public CampBX(String username, String password) throws Exception{
        this.username = username;
        this.password = password;
        baseURI = new URIBuilder()
                .setScheme("http")
                .setHost("CampBX.com")
                .setPath("/api/").build();
        this.httpClient = new DefaultHttpClient();
    }

    public Depth depth() throws Exception {
        return new Depth(getPage("xdepth.php"));
    }


    public Ticker ticker() throws  Exception {
        return new Ticker(getPage("xticker.php"));
    }

    private String getPage(String appendPath) throws Exception {
        URIBuilder uri = new URIBuilder(baseURI).setPath(baseURI.getPath() + appendPath);
        HttpGet httpget = new HttpGet(uri.build());
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            OutputStream stream = new java.io.ByteArrayOutputStream();
            entity.writeTo(stream);
            return stream.toString();
        }
        throw new Exception("Failed to get the page "+appendPath);
    }

}
