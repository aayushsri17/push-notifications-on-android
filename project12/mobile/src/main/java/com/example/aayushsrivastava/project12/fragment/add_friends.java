package com.example.aayushsrivastava.project12.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.aayushsrivastava.project12.R;

/**
 * Created by Aayush Srivastava on 01-07-2016.
 */
public class  add_friends extends Fragment {

    private final int PICK = 1;
EditText e1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_friends, container, false);




    }

    @Override
    public void onStart() {
        super.onStart();
        Button b= (Button)getActivity().findViewById(R.id.b7);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK);

            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data, Context context) {
        super.onActivityResult(requestCode, resultCode, data);

        e1=(EditText)getActivity().findViewById(R.id.editText3);

        switch (requestCode) {
            case (PICK) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor cursor = context.getContentResolver()
                            .query(contactData, null, null, null, null);

                    if(cursor!=null && cursor.getCount()>0 ){
                        cursor.moveToFirst();
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // TODO Fetch other Contact details as you want to use
                       // int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        // column index of the contact name
                       // int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                      //  phoneNo = cursor.getString(phoneIndex);
                     //   name = cursor.getString(nameIndex);
                        // Set the value to the textviews
                      //  textView1.setText(name);
                      //  textView2.setText(phoneNo);
                        e1.setText(name);


                    }
                }
                break;
        }
    }
}