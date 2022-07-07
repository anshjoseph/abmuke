package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abmuke01.API.AbmukeAPI;
import com.example.abmuke01.API.Modelclass.AbmukeAPI_Model;
import com.example.abmuke01.utils.AsyncRun.AsyncRun_onFail;
import com.example.abmuke01.utils.AsyncRun.AsyncRun_onSuccess;
import com.example.abmuke01.utils.BootstrapBuilder.BootstrapCodeBuilder;
import com.example.abmuke01.utils.BootstrapBuilder.Design.carousel;
import com.example.abmuke01.utils.BootstrapBuilder.Design.video;
import com.example.abmuke01.utils.CustomWebView.CWebView;

import java.util.List;

import retrofit2.Response;

public class showResponse extends AppCompatActivity {
    private String gettxt;
    private AbmukeAPI_Model response;
    private int count;
    private TextView load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_response);
        Bundle extras = getIntent().getExtras();
        gettxt = extras.getString("string");
        Toast.makeText(this, gettxt, Toast.LENGTH_SHORT).show();
        load = findViewById(R.id.output);
        AbmukeAPI.GetData(gettxt, new AsyncRun_onSuccess() {
            @Override
            public void run(Response response) {
                AbmukeAPI_Model abmukemodel = (AbmukeAPI_Model) response.body();
                String output[] = abmukemodel.getString().split(" ");
                Toast.makeText(showResponse.this, abmukemodel.toString(), Toast.LENGTH_SHORT).show();
                List<List<String>> res = abmukemodel.getRes();

                // size of response
                int size = res.size();

                // counter
                count = 0;


                // constants
                ImageView forword = findViewById(R.id.forward);
                ImageView back = findViewById(R.id.back);
                WebView web = findViewById(R.id.webView);


                // init img deploy
                load.setText(output[count]);
                if(res.get(count).get(res.get(count).size()-1).equals("0")) new CWebView(web).loadHtml(new BootstrapCodeBuilder(new video(res.get(count).get(0))));
                if (res.get(count).get(res.get(count).size() - 1).equals("1")) {
                    List<String> temp  = res.get(count).subList(0,res.get(count).size()-1);
                    new CWebView(web).loadHtml(new BootstrapCodeBuilder(new carousel(temp)));
                }

                forword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(count<size-1) {
                            // counter inc
                            count += 1;

                            // logic
                                // video
                            if (res.get(count).get(res.get(count).size() - 1).equals("0"))
                                new CWebView(web).loadHtml(new BootstrapCodeBuilder(new video(res.get(count).get(0))));
                                // images
                            if (res.get(count).get(res.get(count).size() - 1).equals("1")) {
                                List<String> temp  = res.get(count).subList(0,res.get(count).size()-1);
//                                temp.remove(res.get(count).size()-1);
                                Log.d("cas",""+temp);
                                new CWebView(web).loadHtml(new BootstrapCodeBuilder(new carousel(temp)));
                            }
                            load.setText(output[count]);
                        }else Toast.makeText(showResponse.this, "max", Toast.LENGTH_SHORT).show();
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (count != 0) {
                            count -= 1;
                            Log.d("count",""+count);
                            if (res.get(count).get(res.get(count).size() - 1).equals("0"))
                                new CWebView(web).loadHtml(new BootstrapCodeBuilder(new video(res.get(count).get(0))));
                            if (res.get(count).get(res.get(count).size() - 1).equals("1")) {
                                List<String> temp  = res.get(count).subList(0,res.get(count).size()-1);
                                new CWebView(web).loadHtml(new BootstrapCodeBuilder(new carousel(temp)));
                            }
                            load.setText(output[count]);
                        }else Toast.makeText(showResponse.this, "min", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }, new AsyncRun_onFail() {
            @Override
            public void run() {
                Toast.makeText(showResponse.this, "some thing went wrong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}