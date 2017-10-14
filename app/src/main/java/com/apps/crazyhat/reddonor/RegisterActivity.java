package com.apps.crazyhat.reddonor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener{

    InputStream is=null;
    String result=null;
    String line=null;
    int code;
    private TextView Registerresult;

    private EditText editTextName;
    private EditText editPhone;
    private EditText editTextEmail;
    private EditText editBloodGroup;
    private EditText editPostal;
    private EditText editLastDonation;

    private Button buttonRegister;
    private int pos;

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    private static final String REGISTER_URL = "http://crazyhat.byethost22.com/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.editBloodGroup);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_group_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        editTextName = (EditText) findViewById(R.id.editTextName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        //editBloodGroup = (EditText) findViewById(R.id.editBloodGroup);
        editBloodGroup = (EditText) findViewById(R.id.editTextEmail);
        editPostal = (EditText) findViewById(R.id.editPostal);
        //editLastDonation = (EditText) findViewById(R.id.editLastDonation);
        editLastDonation = (EditText) findViewById(R.id.editTextEmail);
        Registerresult = (TextView)findViewById(R.id.textView6);


        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);
    }



    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String item = parent.getItemAtPosition(pos).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
        }
    };



    private void registerUser() {
        String Cname = editTextName.getText().toString().trim().toUpperCase();
        String mobile = editPhone.getText().toString();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String Bgroup = editBloodGroup.getText().toString().trim().toUpperCase();
        String postal = editPostal.getText().toString().trim();
        String lastDonation = editLastDonation.getText().toString();
        String loc = "optional";

        new insertProfile(this,Registerresult).execute(Cname, mobile,email,Bgroup,postal,lastDonation,loc);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }



}