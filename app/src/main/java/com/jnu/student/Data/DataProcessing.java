package com.jnu.student.Data;

import android.content.Context;
import androidx.annotation.NonNull;
import com.jnu.student.RecyclerView.Book;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataProcessing {
    public void save(Context context, ArrayList<Book> data){
        try{
            FileOutputStream dataStream=context.openFileOutput("data_log.dat",Context.MODE_PRIVATE);
            ObjectOutputStream out=new ObjectOutputStream(dataStream);
            out.writeObject(data);
            out.close();
            dataStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    @NonNull
    public ArrayList<Book> Load(Context context){
        ArrayList<Book>data=new ArrayList<>();
        try{
            FileInputStream file_in=context.openFileInput("data_log.dat");
            ObjectInputStream in=new ObjectInputStream(file_in);
            data=(ArrayList<Book>)in.readObject();
            in.close();
            file_in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
