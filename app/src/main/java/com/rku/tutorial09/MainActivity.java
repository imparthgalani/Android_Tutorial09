package com.rku.tutorial09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editTextData;
    TextView txtDisplayData;
    final String FILE_ASSETS="data.json";
    final String FILES_INTERNAL="help.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextData = findViewById(R.id.editTextData);
        txtDisplayData = findViewById(R.id.txtDisplayData);

    }

    public void readAssets(View view) {
        try {
            InputStream is=getAssets().open(FILE_ASSETS);
            InputStreamReader  isreader=new InputStreamReader(is);
            BufferedReader reader=new BufferedReader(isreader);
            String mLine="";
            StringBuilder stringBuilder =new StringBuilder();
            while ((mLine=reader.readLine())!=null)
            {
                stringBuilder.append(mLine);
            }
            txtDisplayData.setText(stringBuilder.toString());
            is.close();
            isreader.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(View view) {

        try {
            FileInputStream fin=openFileInput(FILES_INTERNAL);
            int c;
            String temp="";
            while((c=fin.read())!=-1)
            {
                temp=temp+String.valueOf((char)c);
            }
            txtDisplayData.setText(temp);
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(View view) {
        try {
            FileOutputStream fout=openFileOutput(FILES_INTERNAL, Context.MODE_PRIVATE);
            String data=editTextData.getText().toString();
            fout.write(data.getBytes());
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}