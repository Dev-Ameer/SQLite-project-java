package com.example.sqlite;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.my.SQLite.Contact;
import com.my.SQLite.ContactAdapter;
import com.my.SQLite.DbContact;
import com.my.SQLite.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView contactList;
    Button btnAdd;
    DbContact db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbContact(this);

        contactList = (ListView) findViewById(R.id.contactList);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_add_contact.class);
                startActivity(intent);            }
        });

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contact selected_contact = (Contact) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, activity_update_contact.class);

                intent.putExtra("id", selected_contact.getId());

                startActivity(intent);
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Contact> contacts = db.getAllContacts();

        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.item_contact, contacts);

        contactList.setAdapter(contactAdapter);

    }


}