package com.yammer;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class PhilipsHueApiHelper {

    private static PhilipsHueApiHelper instance;

    private HttpClient client;

    private PhilipsHueApiHelper() {
        client = new DefaultHttpClient();
    }

    public static PhilipsHueApiHelper getInstance() {
        if (instance == null) {
            instance = new PhilipsHueApiHelper();
        }
        return instance;
    }

    public HttpClient getHttpClient() {
        return client;
    }
}
