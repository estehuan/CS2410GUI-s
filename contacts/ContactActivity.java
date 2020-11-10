package com.example.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.contacts.components.ContactsView;
import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.ContactsPresenter;

import java.util.ArrayList;

public class ContactActivity extends BaseActivity implements ContactsPresenter.MVPView {
    LinearLayout mainLayout;
    LinearLayout contactsLayout;
    ContactsPresenter contactsPresenter;
    ArrayList<ContactsView> contactsView;
    private final int CREATE_NEW_CONTACT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactsPresenter = new ContactsPresenter(this);

        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        contactsLayout = new LinearLayout(this);
        contactsLayout.setOrientation(LinearLayout.VERTICAL);
        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(contactsLayout);

        AppCompatButton button = new AppCompatButton(this);
        button.setText("Create new contact");
        button.setOnClickListener(view -> {
            contactsPresenter.handleCreateNewContactPress();
        });

        mainLayout.addView(button);
        mainLayout.addView(scrollView);

        setContentView(mainLayout);
    }


    @Override
    public void renderContact(Contact contact) {
        runOnUiThread(() -> {
            ContactsView listItem = new ContactsView(
                    this,
                    contact,
                    (newValue) -> {
                        contactsPresenter.updateContact(contact, newValue);
                    });
//            contactsView.add(listItem);
            if (contactsView != null) { contactsView.add(listItem); };
            if (contactsLayout != null) { contactsLayout.addView(listItem); };
//            contactsLayout.addView(listItem);
        });
    }

    @Override
    public void goToNewContactPage() {
        Intent intent = new Intent(this, NewContactActivity.class);
        startActivityForResult(intent, CREATE_NEW_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_NEW_CONTACT && resultCode == Activity.RESULT_OK) {
            Contact newContact = (Contact)data.getSerializableExtra("result");
            contactsPresenter.handleNewContactCreated(newContact);
        }

    }
}