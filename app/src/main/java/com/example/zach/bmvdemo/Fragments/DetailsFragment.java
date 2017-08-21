package com.example.zach.bmvdemo.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zach.bmvdemo.Data.Model.Location;
import com.example.zach.bmvdemo.Data.Model.LocationList;
import com.example.zach.bmvdemo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangwenpurdue on 8/20/2017.
 */

public class DetailsFragment extends Fragment implements OnMapReadyCallback {

    Unbinder unbinder;

    GoogleMap mGoogleMap;
    View mView;
    @BindView(R.id.map)
    MapView mMapView;

    int position = 0;
    @BindView(R.id.tx_id)
    TextView txId;
    @BindView(R.id.tx_address)
    TextView txAddress;
    @BindView(R.id.tx_arriveTime)
    TextView txArriveTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_details, container, false);
        unbinder = ButterKnife.bind(this, mView);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        position = bundle.getInt("position");

        txId.setText( LocationList.getInstance().get(position).getID() + "");
        txAddress.setText(LocationList.getInstance().get(position).getAddress());
        txArriveTime.setText(LocationList.getInstance().get(position).getArrivalTime());
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Location location = LocationList.getInstance().get(position);
        LatLng place = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(place).title(location.getName()).snippet("ouodfisa"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(place));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));

    }
}
