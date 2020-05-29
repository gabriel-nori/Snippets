package com.valemobi.tools;

public class Params {
    private final String
        key,
        value;

    Params(String key, String value){
        this.key = key;
        this.value = value;
    }
    String getValues(){
        return key + "=" + value;
    }
}
