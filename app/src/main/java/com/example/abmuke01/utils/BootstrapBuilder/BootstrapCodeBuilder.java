package com.example.abmuke01.utils.BootstrapBuilder;

import android.util.Base64;
import android.widget.Toast;

import com.example.abmuke01.utils.BootstrapBuilder.Design.Design;
import com.example.abmuke01.utils.BootstrapBuilder.model.Htmlout;

public class BootstrapCodeBuilder {
    public String bootstrapVersion = "4.6.0";
    private String _body;
    public BootstrapCodeBuilder(Design body){
        _body = body.getOut();

    }
    private String code1 = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\n" +
            "    <title>Abmuke</title>\n" +
            "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css\">\n" +
            "    <link rel=\"stylesheet\" href=\"assets/css/styles.css\">\n" +
            "</head>"+
            "<body>" ;


    private String code2 ="<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\n" +
            "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js\"></script>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
    public Htmlout getRenderableHtml(){
        String code = code1 + _body + code2;
        return new Htmlout(
                Base64.encodeToString(
                        code.getBytes(),
                        Base64.NO_PADDING
                )
        );
    }
    public  String getHtml(){
        String code = code1 + _body + code2;
        return code;
    }
}
