package com.jnu.student.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jnu.student.R;

public class RecyclerViewActivity extends AppCompatActivity {
    private Button mBtnLinear;
    private Button mBtnBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mBtnLinear=(Button)findViewById(R.id.btn_linear);
        mBtnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecyclerViewActivity.this,LinearRecyclerViewActivity.class);
                startActivity(intent);

            }
        });
        mBtnBookList=(Button)findViewById(R.id.btn_books);
        mBtnBookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecyclerViewActivity.this,BookListMainActivity.class);
                startActivity(intent);
            }
        });
    }
}

