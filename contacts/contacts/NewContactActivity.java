package com.example.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.room.Room;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.NewContactPresenter;

public class NewContactActivity extends BaseActivity implements NewContactPresenter.MVPView {
    NewContactPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewContactPresenter(this);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        AppCompatEditText editText = new AppCompatEditText(this);

        AppCompatButton saveButton = new AppCompatButton(this);
        saveButton.setOnClickListener(view -> {
            presenter.saveContact(editText.getText().toString());
        });
        saveButton.setText("Save");

        mainLayout.addView(editText);
        mainLayout.addView(saveButton);

        setContentView(mainLayout);
    }

    @Override
    public void goBackToContactsPage(Contact contact) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", contact);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}