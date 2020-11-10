package com.example.contacts.components;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contacts.models.Contact;

public class ContactsView extends LinearLayout {
    Contact contact;
    public interface OnContactStatusChangedListener {
        public void onChange(boolean isComplete);
    }

    public ContactsView(Context context, Contact contact, OnContactStatusChangedListener onChangeListener) {
        super(context);
        this.contact = contact;
//        AppCompatCheckBox checkBox = new AppCompatCheckBox(context);
//        checkBox.setOnCheckedChangeListener((view, newValue) -> {
//            onChangeListener.onChange(newValue);
//        });
//        checkBox.setChecked(contact.isComplete);

        AppCompatTextView contactContentsView = new AppCompatTextView(context);
        contactContentsView.setText(contact.contents);

//        addView(checkBox);
        addView(contactContentsView);
    }
}