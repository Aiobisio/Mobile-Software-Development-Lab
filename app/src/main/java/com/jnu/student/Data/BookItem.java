package com.jnu.student.Data;

import com.jnu.student.RecyclerView.Book;

public class BookItem extends Book {
    private String title;
    private int headId;
    public BookItem(String title, int headId) {
        super(title, headId);
        this.title = title;
        this.headId = headId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getHeadId() {
        return headId;
    }
    public void setHeadId(int headId) {
        this.headId = headId;
    }
}
