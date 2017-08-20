package com.example.zach.bmvdemo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zach.bmvdemo.Adapter.LocationFragmentAdapter;
import com.example.zach.bmvdemo.Fragments.DetailsFragment;
import com.example.zach.bmvdemo.Fragments.DisplayLocationFragment;
import com.example.zach.bmvdemo.R;

public class MainActivity extends AppCompatActivity implements LocationFragmentAdapter.onItemSelectedListener{
    DetailsFragment detailsFragment = new DetailsFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.content, new DisplayLocationFragment()).commit();
    }


    @Override
    public void onItemSelected(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        detailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, detailsFragment).addToBackStack(null).commit();

    }
}
