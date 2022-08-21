package com.appstraction.contactmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.appstraction.contactmanager.data.DatabaseHandler;
import com.appstraction.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        contactArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        //db.deleteAll();

        // MAKE SURE TO UNCOMMENT BELOW LINES TO ADD DATA INTO SQLITE DATABASE TO SEE INSIDE LIST!!
        /*
        Contact a = new Contact("Ahmet", "55555555");
        db.addContact(a);
        db.addContact(new Contact("Ayse","34567890"));
        */

        List<Contact> contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            Log.d("MainActivity", "onCreate: " + contact.getName());
            contactArrayList.add(contact.getName());
        }

        //create array adapter
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactArrayList
        );

        //add to our listview
        listView.setAdapter(arrayAdapter);

        //Attach eventlistener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("List", "onItemClick: " + contactArrayList.get(position));
            }
        });

    }

}
