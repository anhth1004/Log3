package com.example.log3;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText editTextName, editTextEmail;
    TextView textViewDOB;
    RadioGroup radioGroup;
    Button buttonSave;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        textViewDOB = findViewById(R.id.textViewDOB);
        radioGroup = findViewById(R.id.radioGroup);
        buttonSave = findViewById(R.id.buttonSave);

        databaseHelper = new DatabaseHelper(this);

        textViewDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                textViewDOB.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String dob = textViewDOB.getText().toString();
                String email = editTextEmail.getText().toString();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                byte[] image = getDrawableAsByteArray(radioButton);

                User user = new User(0, name, dob, email, image);
                databaseHelper.addUser(user);
            }
        });
    }

    private byte[] getDrawableAsByteArray(RadioButton radioButton) {
        int radioButtonId = radioButton.getId();
        Drawable drawable;
        if (radioButtonId == R.id.radioButton1) {
            drawable = getResources().getDrawable(R.drawable.vector_2);
        } else if (radioButtonId == R.id.radioButton2) {
            drawable = getResources().getDrawable(R.drawable.vector_2);
        } else if (radioButtonId == R.id.radioButton3) {
            drawable = getResources().getDrawable(R.drawable.vector_3);
        } else {
            return null;
        }

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
