package com.example.apphelper2019.ex.login_reg_php_mysql;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types
 */
public class API_Client {
    private final String USER_AGENT = "Mozilla/5.0";

    public static JSONObject performGetCall(String mainUrl, HashMap<String, String> parameterList) {
        JSONObject jsonObject = null;
        HttpURLConnection conn = null;
        try {
            String getData = "?" + getParamsString(parameterList);
            URL url = new URL(mainUrl + getData);
            conn = (HttpURLConnection) url.openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                jsonObject = convertInputStreamToJsonObj(conn.getInputStream());
            }
        } catch (Exception excep) {
            excep.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return jsonObject;
    }

    public static JSONObject performPostCall(String mainUrl, HashMap<String, String> parameterList) {
        JSONObject jsonObject = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(mainUrl);
            String postData = getParamsString(parameterList);
            byte[] postDataBytes = postData.getBytes("UTF-8");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setUseCaches(false);
            DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
            dataOutputStream.write(postDataBytes);
            dataOutputStream.flush();
            dataOutputStream.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                jsonObject = convertInputStreamToJsonObj(conn.getInputStream());
            }
        } catch (Exception excep) {
            /*
            since android 8 and above we get
                        D / NetworkSecurityConfig:No Network Security Config specified, using platform default
                        W / System.err:java.io.IOException:Cleartext HTTP traffic to 192.168 .0 .111 not permitted
            exception
            * to avoid this exception read this article
             * https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted*/
            excep.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return jsonObject;
    }

    private static String getParamsString(Map<String, String> params) {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            try {
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(param.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return postData.toString();
    }

    private static JSONObject convertInputStreamToJsonObj(InputStream is) {
        BufferedReader streamReader = null;
        JSONObject jsonObject = null;
        StringBuilder responseStrBuilder = new StringBuilder();
        try {
            streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            jsonObject = responseStrBuilder.toString().length() > 0 ? new JSONObject(responseStrBuilder.toString()) : null;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

