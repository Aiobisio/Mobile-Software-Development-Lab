package com.jnu.student.RecyclerView;
import java.io.Serializable;
public class Book implements Serializable{
    private String title;
    private int headId;

    public Book(String title, int headId) {
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