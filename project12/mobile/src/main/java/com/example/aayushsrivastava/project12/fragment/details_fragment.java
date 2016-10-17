package com.example.aayushsrivastava.project12.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aayushsrivastava.project12.R;
import com.example.aayushsrivastava.project12.main.data;

/**
 * Created by Aayush Srivastava on 01-07-2016.
 */
public class details_fragment extends Fragment {
    TextView t1,t2;

    Button b1;

    data dst;
    SQLiteDatabase sq;
    Cursor cr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_details, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

        b1=(Button)getActivity().findViewById(R.id.button3);
        t1=(TextView)getActivity().findViewById(R.id.textView3);
        t2=(TextView)getActivity().findViewById(R.id.textView4);


        try{
            data dst= new data(getActivity());

            sq=dst.getReadableDatabase();
            String nname=t1.getText()+"";
            String mmobile=t2.getText()+"";
            cr=sq.rawQuery("select *from register where name='"+nname+"' and mobile='"+mmobile+"'", null);



            if (cr.getCount() > 0) {
                t1.setText(nname);
                t2.setText(mmobile);

            } else {
                Toast.makeText(getActivity(), "Not Registered. Try again.", Toast.LENGTH_SHORT).show();

            }
            dst.close();
        } catch (Exception e1) {
            Toast.makeText(getActivity(), e1.getMessage() + "", Toast.LENGTH_SHORT).show();
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //close application

            }
        });
        FragmentManager fm = getFragmentManager();



    }

}
