package com.RRS.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class UrlRequestManager extends APIRequestManager {
    
    public static String getCallBackURL(){
        return "http://localhost:8084/RailwayReservationSystem/PaymentVerifierServlet";
    }

    public static Boolean validateUserTransactionCardWithBank(String CardNo, String Expiry, String CVV) throws MalformedURLException, IOException {
        Boolean validated = false;
        String URL = getRootAPIRequestURL() + "/validate-card";
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("UserName", USERNAME);
        params.put("Password", PASSWORD);
        params.put("API_KEY", API_KEY);
        params.put("API_RESPONSE_URL", "http://localhost:8084/RailwayReservationSystem/APIResponseReceiverServlet");
        params.put("card-no", CardNo);
        params.put("expiry", Expiry);
        params.put("cvv", CVV);
        String response = requestTo(URL, params);
        if (response.equals("true")) {
            validated = true;
        }
        return validated;
    }

    public static void transactAmountfromBank(String CardNo, String Expiry, String CVV, Double Amount) throws MalformedURLException, IOException {
        String URL = getRootAPIRequestURL() + "/transact-amount-by-card-number";
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("UserName", USERNAME);
        params.put("Password", PASSWORD);
        params.put("API_KEY", API_KEY);
        params.put("card-no", CardNo);
        params.put("expiry", Expiry);
        params.put("cvv", CVV);
        params.put("amount", Amount);
        requestTo(URL, params);
    }

    public static String requestTo(String URL, Map<String, Object> params) throws MalformedURLException, UnsupportedEncodingException, ProtocolException, IOException {
        URL url = new URL(URL);
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;) {
            sb.append((char) c);
        }
        String response = sb.toString();
        return response;
    }

    public static void main(String[] args) throws IOException {
        Boolean validate = validateUserTransactionCardWithBank("5502121234561111", "06/26", "139");
        System.out.println(validate);

        //System.out.println(transactAmountfromBank("5502121234561111", "06/26", "139", 5000.0));
    }
}
