package com.jnu.student.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jnu.student.R;

import java.util.ArrayList;
import java.util.List;

public class BookListMainActivity extends AppCompatActivity {
    private RecyclerView mRvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_main);
        mRvMain=(RecyclerView) findViewById(R.id.recycle_view_books);
        mRvMain.setLayoutManager(new LinearLayoutManager(BookListMainActivity.this));
//        mRvMain.setAdapter(new BookListAdapter(BookListMainActivity.this));
        List<Book> bookList=new ArrayList<>();
        Book book1=new Book("软件项目管理案例教程（第4版）",R.drawable.book_2);
        Book book2=new Book("创新工程实践",R.drawable.book_no_name);
        Book book3=new Book("信息安全数学基础（第2版）",R.drawable.book_1);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        BookListAdapter bookListAdapter=new BookListAdapter(bookList);
        mRvMain.setAdapter(bookListAdapter);
    }

}