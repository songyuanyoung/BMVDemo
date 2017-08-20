package com.example.zach.bmvdemo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zach.bmvdemo.Fragments.DisplayLocationFragment;
import com.example.zach.bmvdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.content, new DisplayLocationFragment()).commit();
    }
}
