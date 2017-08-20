package com.example.zach.bmvdemo.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zach.bmvdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangwenpurdue on 8/20/2017.
 */

public class DetailsFragment extends Fragment {
    @BindView(R.id.pos)
    TextView pos;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        unbinder = ButterKnife.bind(this, view);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        Toast.makeText(getContext(), bundle.getInt("position") + "", Toast.LENGTH_SHORT).show();
        pos.setText(bundle.getInt("position") + "");


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
