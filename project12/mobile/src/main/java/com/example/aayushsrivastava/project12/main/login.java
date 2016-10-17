package com.example.aayushsrivastava.project12.main;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aayushsrivastava.project12.R;

public class login extends AppCompatActivity {

    Button b1;
    EditText t1;
    data dst;
    SQLiteDatabase sq;
    Cursor cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1=(Button)findViewById(R.id.button2);
        t1=(EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                     data dst = new data(login.this);
                    sq = dst.getReadableDatabase();
                    String ppin = t1.getText() + "";

                    cr = sq.rawQuery("select * from register where pin='" + ppin + "'", null);


                    if (cr.getCount() > 0) {
                        Toast.makeText(login.this, "Amazing", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, MainActivity.class));
                    } else {
                        Toast.makeText(login.this, "Try Again", Toast.LENGTH_SHORT).show();

                    }
                    dst.close();
                } catch (Exception e1) {
                    Toast.makeText(login.this, e1.getMessage() + "", Toast.LENGTH_SHORT).show();
                }
            }

        });





    }
}
