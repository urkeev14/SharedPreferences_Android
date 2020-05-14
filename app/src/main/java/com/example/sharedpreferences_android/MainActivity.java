package com.example.sharedpreferences_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.Telephony.BaseMmsColumns.MESSAGE_ID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnButton;
    private TextView tvMessage;
    private EditText etMessage;

    private static final String MESSAGE_ID = "messages_prefs";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initComponents();
        attachSavedData();
        
    }

    private void initComponents() {
        btnButton = findViewById(R.id.btnSave);
        tvMessage = findViewById(R.id.tvMessage);
        etMessage = findViewById(R.id.etMessage);
        
        registerButtons();
    }

    private void registerButtons() {
        btnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                onBtnSave();
                break;
            default:
                break;
        }
    }

    private void onBtnSave() {
        String message = etMessage.getText().toString().trim();

        SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("message", message);

        editor.apply();
    }

    public void attachSavedData(){
        SharedPreferences savedData = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        String value = savedData.getString("message", "Nothing yet");

        tvMessage.setText(value);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onBtnSave();
    }
}
