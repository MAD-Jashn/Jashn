package com.example.puneet.jashn;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class ProfileActivity extends AppCompatActivity{
    EditText fName,lName,eMail,add,abtYourself, inter;
    Button sProfile;
    ImageButton img;
    private static final String TAG = "ProfileActivity";
    private static final int PICK_IMAGE = 100;
    Uri selectedImage;
    String dp =" ";


    DatabaseReference databaseProfile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String email = extras.getString("EMAIL");
        final String password = extras.getString("PASSWORD");


        fName = findViewById(R.id.editText1);
        lName = findViewById(R.id.editText2);
       eMail = findViewById(R.id.editText3);
        add = findViewById(R.id.editText4);
        abtYourself = findViewById(R.id.editText5);
        inter = findViewById(R.id.editText6);
        sProfile = findViewById(R.id.button1);
        img = findViewById(R.id.imageButton);

        eMail.setText(email);

        databaseProfile = FirebaseDatabase.getInstance().getReference();
        sProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = fName.getText().toString().trim();
                String last_name = lName.getText().toString().trim();
//                String email = eMail.getText().toString().trim();
                String address = add.getText().toString().trim();
                String about_yourself = abtYourself.getText().toString().trim();
                String interests = inter.getText().toString().trim();


                if (first_name.length()>0){

                    String id = databaseProfile.push().getKey(); // generating the id for each profile

                    Profile profile = new Profile(id,first_name,last_name,email,password,address,about_yourself,interests,dp);
                    Log.d(TAG, "onClick: id" + id + profile.getImage());
                    databaseProfile.child("profiles").child(id).setValue(profile); //to put each record inside the ID
                    Toast.makeText(ProfileActivity.this, "Profile Added Successfully", Toast.LENGTH_SHORT).show();
                    fName.setText("");
                    lName.setText("");
                    eMail.setText("");
                    add.setText("");
                    abtYourself.setText("");
                    inter.setText("");
                    img.setImageURI(null);
                } else {
                    Toast.makeText(ProfileActivity.this, "You should enter a name", Toast.LENGTH_SHORT).show();
                }
                    

            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PICK_IMAGE);
            }
        });


        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data){
            selectedImage = data.getData();
            img.setImageURI(selectedImage);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteFormat = stream.toByteArray();
            String encodedImage = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
            dp = encodedImage;
        }
    }

}
