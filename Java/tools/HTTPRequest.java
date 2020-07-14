package com.tools;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HTTPRequest{
    private final String urlString;
    private List<Params> params = null;
    private final int timeOut;
    private int statusCode = 0;
    private JsonObject json = null;

    public HTTPRequest(String urlString, List<Params> params, int timeOut){
        this.urlString = urlString;
        this.params = params;
        this.timeOut = timeOut;
    }
    public HTTPRequest(String urlString, int timeOut){
        this.urlString = urlString;
        this.timeOut = timeOut;
    }

    public JsonResponse getResponse() {
        try {
            StringBuilder requestUrl = new StringBuilder(urlString);
            if(params != null && params.size() > 0) {
                requestUrl.append("?");
                for (int i = 0; i < params.size(); i++) {
                    if (i > 0) {
                        requestUrl.append(",");
                    }
                    requestUrl.append(params.get(i).getValues());
                }
            }
            URL url = new URL(requestUrl.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(timeOut);
            con.setReadTimeout(timeOut);

            int status = con.getResponseCode();
            statusCode = status;
            if (status == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                String read = content.toString();
                json = new JsonParser().parse(read).getAsJsonObject();
            }
            con.disconnect();
        } catch (Exception e) {
            System.out.println("deu ruim"+e);
        }
        return new JsonResponse(json, statusCode);
    }
}
