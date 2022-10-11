package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jnu.student.RecyclerView.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {
    private Button Button_0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button_0=(Button)findViewById(R.id.btn_recyclerview);
        setListener();
    }

    private void setListener(){
        OnClick onClick=new OnClick();
        Button_0.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this, RecyclerViewActivity.class);
            startActivity(intent);
        }
    }
}