package com.valemobi.tools;

import com.google.gson.JsonObject;

public class JsonResponse {
    private  final int statusCode;
    private final JsonObject json;

    JsonResponse(JsonObject json, int statusCode){
        this.statusCode = statusCode;
        this.json = json;
    }
    public int getStatusCode(){
        return  statusCode;
    }
    public JsonObject getJson(){
        return  json;
    }
}
