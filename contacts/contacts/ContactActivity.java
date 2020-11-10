package com.example.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.ContactPresenter;
import com.example.contacts.presenters.NewContactPresenter;

public class ContactActivity extends BaseActivity implements ContactPresenter.MVPView {
    ContactPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ContactPresenter(this);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        AppCompatTextView name = new AppCompatTextView(this);
        name.setText("hi");

        mainLayout.addView(name);

        setContentView(mainLayout);
    }

    @Override
    public void renderContactInfo(Contact contact) {
    }

    @Override
    public void goBackToContactsPage() {
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("result", contact);
//        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
