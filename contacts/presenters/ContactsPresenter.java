package com.example.contacts.presenters;

import com.example.contacts.ContactActivity;
import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;

import java.util.ArrayList;

public class ContactsPresenter {
    private MVPView view;
    private ArrayList<Contact> contacts = new ArrayList<>();
    private AppDatabase database;
    public interface MVPView extends BaseMVPView {
        public void renderContact(Contact contact);
        public void goToNewContactPage();
    }

    public ContactsPresenter(MVPView view) {
        this.view = view;
        this.database = view.getContextDatabase();
        loadContacts();

        // load contacts
    }

    public void loadContacts() {
        new Thread(() -> {
            // go to the database and load contacts
            contacts = (ArrayList<Contact>)database.getContactDao().getAll();
            contacts.forEach(contact -> {
                view.renderContact(contact);
            });
        }).start();
    }

    public void handleCreateNewContactPress() {
        new Thread(() -> {
            // stop our long running tasks or wait for them to finish
            view.goToNewContactPage();
        }).start();
    }

    public void updateContact(Contact contact, boolean isComplete) {
        new Thread(() -> {
//            contact.isComplete = isComplete;
            database.getContactDao().update(contact);
        }).start();
    }

    public void handleNewContactCreated(Contact contact) {
        contacts.add(contact);
        view.renderContact(contact);
    }
}