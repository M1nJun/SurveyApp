package com.example.surveyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import java.util.TreeMap;

public class SecondActivity extends AppCompatActivity {

    private Gson gson;
    private String[] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(intent != null)
            options = intent.getStringArrayExtra(MainActivity.OPTION);

        setContentView(R.layout.activity_second);
        gson = new Gson();
        new VotesTask().execute();
    }

    private class VotesTask extends AsyncTask<String, Void, String> {
        private String uri;

        VotesTask() {
            uri = "http://" + URIHandler.hostName + "/responses";
        }

        @Override
        protected String doInBackground(String... urls) {
            return URIHandler.doGet(uri, "");
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            loadVotes(result);
        }
    }

    private void loadVotes(String json) {
        TextView vote1 = findViewById(R.id.vote1);
        TextView vote2 = findViewById(R.id.vote2);
        TextView vote3 = findViewById(R.id.vote3);
        TextView vote4 = findViewById(R.id.vote4);

        Vote[] votes = null;
        votes = gson.fromJson(json, Vote[].class);
        TreeMap<String, Integer> map = new TreeMap();
        for(Vote v : votes){
            map.put(v.getAnswer(), v.getCount());
        }



        System.out.println(votes[0].getAnswer());
        int[] counts = new int[4];
        String[] title = new String[4];
        for(int i=0; i<votes.length; i++) {
            counts[i] = votes[i].getCount();
            title[i] = votes[i].getAnswer();
        }

        vote1.setText(options[0] + " got " + map.getOrDefault(options[0], 0) + " votes");
        vote2.setText(options[1] + " got " + map.getOrDefault(options[1], 0) + " votes");
        vote3.setText(options[2] + " got " + map.getOrDefault(options[2], 0) + " votes");
        vote4.setText(options[3] + " got " + map.getOrDefault(options[3], 0) + " votes");
    }
}