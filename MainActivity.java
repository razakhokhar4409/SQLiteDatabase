package com.example.database_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    helper help;
    EditText editname,editbatch;
    Button button,button2,button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        help= new helper(this);

        SQLiteDatabase database= help.getReadableDatabase();
        editname= findViewById(R.id.Editname);
        editbatch=findViewById(R.id.Editbatch2);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        addData();
        showall();
        updatedata();
        DeleteData();

    }
    void DeleteData(){
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer rowsize= help.deletevalues(editname.getText().toString());
                if(rowsize>0){
                    Toast.makeText( MainActivity.this,"Data is Deleted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText( MainActivity.this,"Data is Not Deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void updatedata(){
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean updatecheck= help.updatevalues(editname.getText().toString(),editbatch.getText().toString());
                if(updatecheck==true){
                    Toast.makeText( MainActivity.this,"Data is updated",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText( MainActivity.this,"Data is not updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void showall(){

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=help.showallvalues();

                if(cursor==null){
                    showmessage("data","Nothing available");
                }

                if(cursor!=null){
                    cursor.moveToFirst();

                }
                StringBuilder builder= new StringBuilder();

                do{
                    String name= cursor.getString(0);
                    String batch= cursor.getString(1);
                    builder.append("Name is :"+name+" And Batch is :"+batch+"\n\n");

                }while(cursor.moveToNext());

                showmessage("Data:",builder.toString());
            }
        });


    }

    void showmessage(String Title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(message);
        builder.show();

    }

    void addData(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result= help.insertvalues(editname.getText().toString(),editbatch.getText().toString());

                if(result==true){
                    Toast.makeText( MainActivity.this,"Data inserted is of "+editname.getText().toString(),Toast.LENGTH_LONG).show();
                }
                else if(result==false){
                    Toast.makeText( MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}
