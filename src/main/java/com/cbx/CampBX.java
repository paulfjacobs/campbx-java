package com.cbx;

import com.cbx.response.Depth;
import com.cbx.response.Funds;
import com.cbx.response.Ticker;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;

import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

    public Funds myFunds() throws Exception {
        return new Funds(getPagePostAuthorized("myfunds.php"));
    }

    private String getPagePostAuthorized(String appendPath) throws Exception {
        // build URI; use https and send the user and password
        URIBuilder uri = new URIBuilder(baseURI).setScheme("https").setPath(baseURI.getPath() + appendPath);
        HttpPost httpPost = new HttpPost(uri.build());

        // add user and password
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("user", this.username));
        params.add(new BasicNameValuePair("pass", this.password));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        // get the response
        return getFromResponse(httpClient.execute(httpPost));
    }

    private String getPage(String appendPath) throws Exception {
        URIBuilder uri = new URIBuilder(baseURI).setPath(baseURI.getPath() + appendPath);
        HttpGet httpGet = new HttpGet(uri.build());
        return getFromResponse(httpClient.execute(httpGet));
    }

    private String getFromResponse(HttpResponse response) throws  Exception {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            OutputStream stream = new java.io.ByteArrayOutputStream();
            entity.writeTo(stream);
            return stream.toString();
        }
        throw new Exception("Entity was null");
    }

}
