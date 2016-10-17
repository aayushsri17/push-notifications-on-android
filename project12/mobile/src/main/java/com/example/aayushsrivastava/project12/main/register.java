package com.example.aayushsrivastava.project12.main;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aayushsrivastava.project12.R;

public class register extends AppCompatActivity {

    data dst;
    SQLiteDatabase sq;
    // Cursor cr;
    Button b1;
    EditText e1, e2, e3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       /* SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, register.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();
            )
*/


        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText5);
        e3 = (EditText)findViewById(R.id.editText6);
        b1 = (Button)findViewById(R.id.button);
        dst = new data(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                try {
                    String name = e1.getText() + "";
                    String mobile = e2.getText() + "";
                    String pin = e2.getText() + "";

                    sq = dst.getWritableDatabase();
                    sq.execSQL("insert into register(name,mobile,pin) values ('" + name + "','" + mobile + "','" + pin + "')");
                    dst.close();
                    Toast.makeText(register.this, "Registered", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(register.this, login.class));



                } catch (Exception ex) {

                    Toast.makeText(register.this, ex.getMessage() + "", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
