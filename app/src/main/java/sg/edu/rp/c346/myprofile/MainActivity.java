package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import javax.crypto.spec.GCMParameterSpec;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);

    }

    @Override
    protected void onResume() {
super.onResume();

//Step 2a Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

//Step 2b Retrieve the saved data from SharedPreference object
        String name = prefs.getString("name","");
        Float score = prefs.getFloat("score",0);
        Integer gender = prefs.getInt("gender",0);

        //Step 2c Update the UI element with the value
        etName.setText(name);
        etGPA.setText(score+ "");
        rgGender.check(gender);





    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        String strScore = etGPA.getText().toString();
        Float GPA = Float.parseFloat(strScore);
        int gender = rgGender.getCheckedRadioButtonId();

//Step 1a Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 1b Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit =prefs.edit();

        //Step 1c Adding the key-value pair
        prefEdit.putString("name",strName);
        prefEdit.putFloat("score",GPA);
        prefEdit.putInt("gender", gender);

        //Step 1d Call commit() method to save the changes into the SharedPreference
        prefEdit.commit();
    }
}
