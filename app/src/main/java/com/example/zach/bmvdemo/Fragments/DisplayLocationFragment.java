package com.example.zach.bmvdemo.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.print.PrintHelper;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zach.bmvdemo.Adapter.LocationFragmentAdapter;
import com.example.zach.bmvdemo.Data.Model.Location;
import com.example.zach.bmvdemo.Data.Remote.SOService;
import com.example.zach.bmvdemo.R;
import com.example.zach.bmvdemo.Utils.APIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhangwenpurdue on 8/20/2017.
 */

public class DisplayLocationFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private SOService mService;
    private LocationFragmentAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_location, container, false);

        mService = APIUtils.getSOService();
        adapter = new LocationFragmentAdapter(getContext(), new ArrayList<Location>());

        unbinder = ButterKnife.bind(this, view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        fetchData();



        return view;
    }

    void fetchData() {
        mService.getLocations().enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "hshshshs", Toast.LENGTH_SHORT).show();
                    adapter.UpdateList(response.body());
                } else {
                    int statusCode  = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
