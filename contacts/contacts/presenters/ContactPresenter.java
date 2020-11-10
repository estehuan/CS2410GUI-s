package com.example.contacts.presenters;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;

import java.util.ArrayList;


public class ContactPresenter {
    private MVPView view;
    private AppDatabase database;
    private Contact contact;
    public interface MVPView extends BaseMVPView {
        public void renderContactInfo(Contact contact);
        public void goBackToContactsPage();
    }

    public ContactPresenter(MVPView view) {
        this.view = view;
        this.database = view.getContextDatabase();
        loadContactInfo();
    }

    private void loadContactInfo() {
        contact = database.getContactDao().findById(0);
        view.renderContactInfo(contact);
    }
}
