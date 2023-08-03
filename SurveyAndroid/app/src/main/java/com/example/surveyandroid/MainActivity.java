package com.example.surveyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private String selectedText;
    private Gson gson;

    public final static String OPTION = "com.example.SurveyAndroid.OPTION";

    Survey s;

    String[] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new Gson();
        new MainActivity.SurveyTask().execute();
    }

    private class SurveyTask extends AsyncTask<String, Void, String> {
        private String uri;

        SurveyTask() {
            uri = "http://" + URIHandler.hostName + "/questions";
        }

        @Override
        protected String doInBackground(String... urls) {
            return URIHandler.doGet(uri, "");
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            loadSurvey(result);
        }
    }

    private void loadSurvey(String json) {
        TextView question = findViewById(R.id.question);

        s = gson.fromJson(json, Survey.class);

        options = new String[4];
        options[0] = s.getOption1();
        options[1] = s.getOption2();
        options[2] = s.getOption3();
        options[3] = s.getOption4();

        question.setText(s.getQuestion());

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setText(options[i]);
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        selectedText = (String) radioButton.getText();
        }
    public void submit(View view) {

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        new SubmitTask(selectedText).execute();

    }

    private class SubmitTask extends AsyncTask<String, Void, String> {
        private String uri;
        private String toSend;



        SubmitTask(String selectedText) {

            Response r = new Response();
            r.setSurvey(s.getId());
            r.setResponse(selectedText);
            uri="http://"+URIHandler.hostName+"/responses";
            this.toSend = gson.toJson(r);
        }

        @Override
        protected String doInBackground(String... urls) {
            return URIHandler.doPost(uri,toSend);
        }

        protected void onPostExecute(String result) {
            gotoNext();
        }

    }

    public void gotoNext() {
        //the first statement sets up the intent, you have to tell which activity you want to navigate to
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(OPTION, options);
        //need corresponding method in the second activity to receive info
        startActivity(intent);
    }
}