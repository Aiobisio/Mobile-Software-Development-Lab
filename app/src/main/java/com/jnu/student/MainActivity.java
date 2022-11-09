package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jnu.student.RecyclerView.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {
    private Button Button_0;
    private Button Button_1;
    private Button Button_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button_0=(Button)findViewById(R.id.btn_recyclerview);
        Button_1=(Button)findViewById(R.id.btn_edittext);
        Button_2=(Button)findViewById(R.id.btn_login);
        setListener();
    }

    private void setListener(){
        OnClick onClick=new OnClick();
        Button_0.setOnClickListener(onClick);
        Button_1.setOnClickListener(onClick);
        Button_2.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent=null;
            switch(view.getId()){
                case R.id.btn_recyclerview:
                    intent =new Intent(MainActivity.this,RecyclerViewActivity.class);
                    break;
                case R.id.btn_edittext:
                    intent =new Intent(MainActivity.this,EditTextActivity.class);
                    break;
                case R.id.btn_login:
                    intent =new Intent(MainActivity.this,EditingLogin.class);
                    break;
            }
            startActivity(intent);
        }
    }
}