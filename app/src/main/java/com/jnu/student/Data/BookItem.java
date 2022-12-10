package com.jnu.student.Data;

import java.io.Serializable;

public class BookItem implements Serializable {
    private String title;
    private int headId;
    public BookItem(String title, int headId) {
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
