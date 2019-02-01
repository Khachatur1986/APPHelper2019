package com.example.apphelper2019.ex.login_reg_php_mysql;
import android.util.Pair;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.net.ssl.HttpsURLConnection;

/**
 * https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
 * http://www.baeldung.com/java-http-request
 * https://developer.android.com/reference/java/net/HttpURLConnection
 */

public class Util {

    private final String USER_AGENT = "Mozilla/5.0";
//
//    public static URI applyParameters(URI baseUri, String[] urlParameters) {
//        StringBuilder query = new StringBuilder();
//        boolean first = true;
//        for (int i = 0; i < urlParameters.length; i += 2) {
//            if (first) {
//                first = false;
//            } else {
//                query.append("&");
//            }
//            try {
//                query.append(urlParameters[i]).append("=")
//                        .append(URLEncoder.encode(urlParameters[i + 1], "UTF-8"));
//            } catch (UnsupportedEncodingException ex) {
//        /* As URLEncoder are always correct, this exception
//         * should never be thrown. */
//                throw new RuntimeException(ex);
//            }
//        }
//        try {
////            return new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), query.toString(), null);
//            return new URI(baseUri.getScheme(), baseUri.getAuthority(), baseUri.getPath(), query.toString(), null);
//        } catch (URISyntaxException ex) {
//     /* As baseUri and query are correct, this exception
//      * should never be thrown. */
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static void send(String url, String[] params) throws URISyntaxException, IOException {
//        URI baseUri = new URI(url);
//        URI uri = applyParameters(baseUri, params);
//        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
//        connection.setDoInput(true);
//        connection.setDoOutput(false);
//        connection.setRequestMethod("GET");
//        connection.connect();
//        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//            // ...
//        }
////        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
////        try {
////            urlConnection.setDoOutput(true);
////            urlConnection.setChunkedStreamingMode(0);
////
////            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
////            writeStream(out);
////
////            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
////            readStream(in);
////        } finally {
////            urlConnection.disconnect();
////        }
//    }

    /**
     * http://www.baeldung.com/java-http-request
     *
     * @param requestUrl
     * @param params
     */
    public static void sendGetRequest(String requestUrl, ArrayList<Pair<String, String>> params) {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(requestUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(getParamsString(params));
            out.flush();
            out.close();

            con.setRequestProperty("Content-Type", "application/json");
//            con.setConnectTimeout(5000);
//            con.setReadTimeout(5000);

//            String cookiesHeader = con.getHeaderField("Set-Cookie");
//            List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);

//            cookies.forEach(cookie -> cookieManager.getCookieStore().add(null, cookie));

//            Optional<HttpCookie> usernameCookie = cookies.stream()
//                    .findAny().filter(cookie -> cookie.getName().equals("username"));
//            if (usernameCookie == null) {
//                cookieManager.getCookieStore().add(null, new HttpCookie("username", "john"));
//            }

//            con.disconnect();
//            con = (HttpURLConnection) url.openConnection();
//
//            con.setRequestProperty("Cookie",
//                    StringUtils.join(cookieManager.getCookieStore().getCookies(), ";"));

//            con.setInstanceFollowRedirects(false);
//            if (status == HttpURLConnection.HTTP_MOVED_TEMP
//                    || status == HttpURLConnection.HTTP_MOVED_PERM) {
//                String location = con.getHeaderField("Location");
//                URL newUrl = new URL(location);
//                con = (HttpURLConnection) newUrl.openConnection();
//            }

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    private static String getParamsString(ArrayList<Pair<String, String>> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Pair<String, String> entry : params) {
            result.append(URLEncoder.encode(entry.first, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.second, "UTF-8"));
            result.append("&");
        }
        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }

    private static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }

    // HTTP GET request
    private void sendGet() throws Exception {

        String url = "http://www.google.com/search?q=mkyong";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }

    // HTTP POST request
    private void sendPost() throws Exception {

        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
