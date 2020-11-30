package com.example.sqlitestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class adding_student extends AppCompatActivity {

    OpenHelper openHelper;
    String ID;
    String Name;
    String Address;
    SQLiteDatabase sqLiteDatabase;

    EditText eID;
    EditText eName;
    EditText eAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_student);
        openHelper = new OpenHelper(this);
        sqLiteDatabase = openHelper.getWritableDatabase();

        eID = findViewById(R.id.txtidno);
        eName = findViewById(R.id.txtname);
        eAddress = findViewById(R.id.txtaddress);
    }
    public void clickAdd(View view) {
        ID=eID.getText().toString();
        Name=eName.getText().toString();
        Address=eAddress.getText().toString();

        if(TextUtils.isEmpty(ID) || TextUtils.isEmpty(Name) || TextUtils.isEmpty(Address)) {
            Toast.makeText(this, "Check Empty Fields", Toast.LENGTH_SHORT).show();
        }
        else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseInfo._ID, (byte[]) null);
            contentValues.put(DatabaseInfo.IDNO, ID);
            contentValues.put(DatabaseInfo.StudentName, Name);
            contentValues.put(DatabaseInfo.StudentAddress, Address);
             long rowId = sqLiteDatabase.insert(DatabaseInfo.TABLE_NAME, null, contentValues);

             if (rowId != -1) {
                 Toast.makeText(this, "Added",Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(this, MainActivity.class));
             }
             else {
                 Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
             }
        }
    }
}