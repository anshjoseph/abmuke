package com.example.abmuke01.API.Modelclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AbmukeAPI_Model {
    @SerializedName("string")
    @Expose
    private String string;
    @SerializedName("res")
    @Expose
    private List<List<String>> res = null;
    @SerializedName("code")
    @Expose
    private String code;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public List<List<String>> getRes() {
        return res;
    }

    public void setRes(List<List<String>> res) {
        this.res = res;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AbmukeAPI_Model{" +
                "string='" + string + '\'' +
                ", res=" + res +
                ", code='" + code + '\'' +
                '}';
    }
}
