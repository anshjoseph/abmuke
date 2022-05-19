package com.example.abmuke01.utils.BootstrapBuilder.model;

public class Htmlout {
    private String encodingHTML;
    private final String mineType= "text/html";
    private final String encodingType = "base64";

    public Htmlout(String encodingHTML) {
        this.encodingHTML = encodingHTML;
    }

    public String getEncodingHTML() {
        return encodingHTML;
    }

    public String getMineType() {
        return mineType;
    }

    public String getEncodingType() {
        return encodingType;
    }
}
