package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HelpDetails extends AppCompatActivity {
    private TextView dis, dis2,dis3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_details);
            dis= findViewById(R.id.upFile);
            dis2=findViewById(R.id.upFile2);
            dis3=findViewById(R.id.upFile3);
        String upfile,upfile2,upfile3;
        upfile ="1. Open Application \n" +
                "2. Open Camera section \n" +
                "3. Hit that Tick mark \n" +
                "4. Website will Open \n" +
                "5. Choose file from gallary OR Recorded one which you want to upload \n" +
                "6. Upload file after choosen \n" +
                "7. Click on the upload button \n" +
                "8. Wait untill your will uploaded and processed \n" +
                "9. If file Dosen't upload or processed \n" +
                "10. Visit the report section And complain your report";

        upfile2 ="1. Open Application \n" +
                 "2. Open Camera/Mic Option whatever you want to record \n" +
                 "3. Tap on record button at the Center of the Application \n" +
                 "4. your Record will start";
        upfile3="1. Open Application\n" +
                "2. click on Profile Section\n" +
                "3. Multiple Option For use\n" +
                "4. You can Contact us\n" +
                "5. Check our policies and more\n" +
                "6. Can report us By clicking on report button\n" +
                "7. NOTE :- for report you must Login first\n" +
                "8. Help section for further information";


        dis.setText(upfile);
        dis2.setText(upfile2);
        dis3.setText(upfile3);


    }
}

