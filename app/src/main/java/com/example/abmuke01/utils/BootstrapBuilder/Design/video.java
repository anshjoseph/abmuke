package com.example.abmuke01.utils.BootstrapBuilder.Design;

import java.util.List;

public class video implements Design{
    String url;
    String height,width;
    public video(String url){this.url = url;}


    @Override
    public String getOut() {
        return
                "<video width=\"320\" height=\"240\" controls autoplay loop>" +
                "<source src=\"" +
                this.url +
                "\">"+
                "</video>"

                ;
    }
    public static Design getInstance(String url){
        Design design = new video(url);
        return design;
    }
}
