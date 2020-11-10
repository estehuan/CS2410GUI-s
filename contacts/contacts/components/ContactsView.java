package com.example.contacts.components;

import android.content.Context;
import android.graphics.Color;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contacts.R;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.ContactsPresenter;

public class ContactsView extends LinearLayout {
    Contact contact;
    ContactsPresenter contactsPresenter;
    public interface OnContactStatusChangedListener {
        public void onChange(boolean isComplete);
    }

    public ContactsView(Context context, Contact contact, OnContactStatusChangedListener onChangeListener) {
        super(context);
        this.contact = contact;
        contactsPresenter = new ContactsPresenter(this);
//        AppCompatCheckBox checkBox = new AppCompatCheckBox(context);
//        checkBox.setOnCheckedChangeListener((view, newValue) -> {
//            onChangeListener.onChange(newValue);
//        });
//        checkBox.setChecked(contact.isComplete);

//        AppCompatTextView contactContentsView = new AppCompatTextView(context);
//        contactContentsView.setText(contact.contents);

        AppCompatButton contactContentsView = new AppCompatButton(context);
        contactContentsView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        contactContentsView.setWidth(1200);
        contactContentsView.setText(contact.contents);
        contactContentsView.setOnClickListener(view -> {
            contactsPresenter.handleContactPress(contact);
        });

//        addView(checkBox);
        addView(contactContentsView);
    }
}