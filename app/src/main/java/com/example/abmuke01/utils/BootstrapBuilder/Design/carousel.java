package com.example.abmuke01.utils.BootstrapBuilder.Design;

import com.example.abmuke01.utils.BootstrapBuilder.model.Htmlout;

import java.util.List;

public class carousel implements Design{
    List<String> urls;
    public carousel(List<String> urls){ this.urls = urls;}
    private String code_sec1 = "<div class=\"carousel slide\" data-ride=\"carousel\" id=\"carousel-1\">\n" +
            "        <div class=\"carousel-inner\">";

    private String code_sec2 = "</div>\n" +
            "        <div><a class=\"carousel-control-prev\" href=\"#carousel-1\" role=\"button\" data-slide=\"prev\"><span class=\"carousel-control-prev-icon\"></span><span class=\"sr-only\">Previous</span></a><a class=\"carousel-control-next\" href=\"#carousel-1\" role=\"button\" data-slide=\"next\"><span class=\"carousel-control-next-icon\"></span><span class=\"sr-only\">Next</span></a></div><ol class=\"carousel-indicators\">";
    private  String code_sec3 = "</ol>\n" +
            "    </div>";

    public String imgGen(String url,boolean isActive){
        if(isActive)
        return "<div class=\"carousel-item active\"><img class=\"w-100 d-block\" " +
                "src=\"" +
                url +
                "\"" +
                " alt=\"Slide Image\"></div>";
        return "<div class=\"carousel-item\"><img class=\"w-100 d-block\" " +
                "src=\"" +
                url +
                "\"" +
                " alt=\"Slide Image\"></div>";
    }
    private String liGen(int index, boolean isActive){
        if(isActive)
            return "<li data-target=\"#carousel-1\" " +
                    "data-slide-to=\"" +
                    index +
                    "\" " +
                    "class=\"active\"></li>";
        return "<li data-target=\"#carousel-1\" " +
                "data-slide-to=\"" +
                index +
                "\" " +
                "></li>";
    }

    @Override
    public String getOut() {
        boolean flagFirst = true;
        String result = code_sec1;
        for(String url:urls){
            result+=imgGen(url,flagFirst);
            flagFirst = false;
        }
        flagFirst = true;
        result+=code_sec2;
        for(int i = 1;urls.size()>=i;i++){
            result+=liGen(i,flagFirst);
            flagFirst = false;
        }
        result+= code_sec3;
        return result;
    }

    public static Design getInstance(List<String> urls){
        Design design = new carousel(urls);
        return design;
    }
}
