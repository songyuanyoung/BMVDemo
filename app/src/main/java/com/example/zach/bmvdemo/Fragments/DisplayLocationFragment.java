package com.example.zach.bmvdemo.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.zach.bmvdemo.Adapter.LocationFragmentAdapter;
import com.example.zach.bmvdemo.Data.Model.Location;
import com.example.zach.bmvdemo.Data.Model.LocationList;
import com.example.zach.bmvdemo.Data.Remote.SOService;
import com.example.zach.bmvdemo.R;
import com.example.zach.bmvdemo.Utils.APIUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    List<Location> locationsList = LocationList.getInstance();
    @BindView(R.id.bbutton_group_checked1)
    BootstrapButton name;
    @BindView(R.id.bbutton_group_checked2)
    BootstrapButton arriveTime;
    @BindView(R.id.bbutton_group_checked3)
    BootstrapButton distance;


    private SOService mService;
    private LocationFragmentAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_display_location, container, false);

        mService = APIUtils.getSOService();

        adapter = new LocationFragmentAdapter(getContext(), locationsList);

        unbinder = ButterKnife.bind(this, view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        fetchData();
        sort();

        return view;
    }


    public void  fetchData() {

        mService.getLocations().enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.isSuccessful()) {
                    locationsList.addAll(response.body());

                    adapter.UpdateList(response.body());
                } else {
                    int statusCode = response.code();
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


    public void sort() {


        name.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener() {
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    Comparator<Location> cpm = new Comparator<Location>() {
                        @Override
                        public int compare(Location t1, Location t2) {
                            return t1.getName().compareTo(t2.getName());
                        }
                    };
                    Collections.sort(locationsList, cpm);
                    adapter.UpdateList(locationsList);
                }

            }
        });
        arriveTime.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener() {
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    Comparator<Location> cpm = new Comparator<Location>() {
                        @Override
                        public int compare(Location t1, Location t2) {
                            return t1.getArrivalTime().compareTo(t2.getArrivalTime());
                        }
                    };
                    Collections.sort(locationsList, cpm);
                    adapter.UpdateList(locationsList);
                }

            }
        });
        distance.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener() {
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    Comparator<Location> cpm = new Comparator<Location>() {
                        @Override
                        public int compare(Location t1, Location t2) {
                            return t1.getID() - t2.getID();
                        }
                    };
                    Collections.sort(locationsList, cpm);
                    adapter.UpdateList(locationsList);
                }
            }
        });
    }
    public interface onFragmentSelectedlistener{
        void onFragmentSelected(int position);
    }
}
