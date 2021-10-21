package com.example.widgetex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

public class TextActivity extends AppCompatActivity {
    private EditText edName, edId;
    private TextView textNameSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        edName = findViewById(R.id.editName);
        edId = findViewById(R.id.editID);
        textNameSize = findViewById(R.id.textNameSize);

        edName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                keypadShow();
            } else {
                keypadHide(v);
            }
        });

        edName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                byte[] bytes = null;
                try {
                    bytes = s.toString().getBytes("UTF-8");
                    int strCount = bytes.length;
                    textNameSize.setText("20 / "+strCount);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    void keypadHide(View v) {
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(edName.getWindowToken(),0);
    }
    void keypadShow() {
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.showSoftInput(edName,0);
    }
}