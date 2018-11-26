package com.example.puneet.jashn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yuku.ambilwarna.AmbilWarnaDialog;

public class EventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ScrollView mLayout;
    int mDefaultColor;
    int eventColor;
    private EditText eventName,eventDate,eventLocation,eventTime,minAge, entryFee, eventDescription;
    private Button createEvent, colorButton;
    Spinner selectCategory;
    String category;
    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference eventRef;
    String userID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_create);
        mLayout = (ScrollView) findViewById(R.id.layoutEventCreate);
        mDefaultColor = ContextCompat.getColor(EventActivity.this,R.color.colorPrimary);
        colorButton = (Button) findViewById(R.id.editTextColorChooser);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        eventName = findViewById(R.id.editTextEventName);
        eventDate = findViewById(R.id.editTextEventDate);
        eventLocation = findViewById(R.id.editTextEventLocation);
        eventTime = findViewById(R.id.editTextEventTime);
        minAge = findViewById(R.id.editTextMinAge);
        entryFee = findViewById(R.id.editTextEntryFee);
        eventDescription = findViewById(R.id.editTextEventDesc);
        selectCategory = findViewById(R.id.spinnerCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.event_category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCategory.setAdapter(adapter);
        selectCategory.setOnItemSelectedListener(this);
        eventRef = FirebaseDatabase.getInstance().getReference();





        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = eventRef.push().getKey();
                String name = eventName.getText().toString();
                String date = eventDate.getText().toString();
                String time = eventTime.getText().toString();
                String loc = eventLocation.getText().toString();
                String age = minAge.getText().toString();
                String fee = entryFee.getText().toString();
                String desc = eventDescription.getText().toString();

                if(name.length()>0 && date.length()>0 && time.length()>0 && loc.length()>0){

                    Event event = new Event(id,name,date,time,loc,category,age,fee,eventColor,userID);

                }
                else{
                    Toast.makeText(EventActivity.this, "Please Fill in All the Details", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    public void openColorPicker(){
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;
                eventColor = mDefaultColor;
            }
        });
        colorPicker.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            category = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
