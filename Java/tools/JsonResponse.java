package com.valemobi.tools;

import com.google.gson.JsonObject;

public class JsonResponse {
    private  final int statusCode;
    private final JsonObject json;

    JsonResponse(JsonObject json, int statusCode){
        this.statusCode = statusCode;
        this.json = json;
    }
    int getStatusCode(){
        return  statusCode;
    }
    JsonObject getJson(){
        return  json;
    }
}
