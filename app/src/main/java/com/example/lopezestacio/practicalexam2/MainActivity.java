package com.example.lopezestacio.practicalexam2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView tMsg;
    EditText eName, eAge, eGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tMsg = findViewById(R.id.tvMsg);
    }

    public void saveMe(View v){
        SharedPreferences sp = getSharedPreferences("data1", Context.MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        String name = eName.getText().toString();
        String age = eAge.getText().toString();
        String gender = eGender.getText().toString();
        writer.putString("name", name);
        writer.putString("age", age);
        writer.putString("gender", gender);
        writer.commit();
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_LONG).show();

    }

    public void saveInternal(View v){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data2.txt", Context.MODE_PRIVATE);
            String name2 = eName.getText().toString();
            fos.write(name2.getBytes());
            Toast.makeText(this, "Data Saved!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error Saving", Toast.LENGTH_LONG).show();
        } finally{
            try{
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public void displayMsg(View v){
        SharedPreferences sp = getSharedPreferences("data1", Context.MODE_PRIVATE);
        String name = sp.getString("name", null);
        String age = sp.getString("age", null);
        String gender = sp.getString("gender", null);
        String message = name + "\n" + age + "\n" + gender;
        tMsg.setText(message);
    }

    public void displayInternal(View v){
        try {
            FileInputStream fin = openFileInput("data2.txt");
            int c;
            StringBuffer buffer = new StringBuffer();
            while((c=fin.read()) != -1){
                buffer.append((char)c);
            }
            String message = "Good day" + buffer;
            tMsg.setText(message);
        } catch (Exception e){
            Toast.makeText(this, "Error Reading", Toast.LENGTH_LONG).show();
        }
    }

}
