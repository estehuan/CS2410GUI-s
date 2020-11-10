package com.example.contacts.presenters;

import androidx.room.Room;

import com.example.contacts.NewContactActivity;
import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;

import java.util.ArrayList;

public class NewContactPresenter {
    MVPView view;
    AppDatabase database;
    public interface MVPView extends BaseMVPView {
        public void goBackToContactsPage(Contact contact);
    }

    public NewContactPresenter(NewContactActivity view) {
        this.view = view;
        this.database = view.getContextDatabase();
    }

    public void saveContact(String contents) {
        // CONTACT: check the contents to make sure they are not empty
        new Thread(() -> {
            Contact contact = new Contact();
            contact.contents = contents;
//            contact.isComplete = false;
            contact.id = (int)database.getContactDao().create(contact);
            view.goBackToContactsPage(contact);
        }).start();
    }
}