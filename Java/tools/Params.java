package com.valemobi.tools;

public class Params {
    private final String
        key,
        value;

    public Params(String key, String value){
        this.key = key;
        this.value = value;
    }
    public String getValues(){
        return key + "=" + value;
    }
}
