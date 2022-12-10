package com.jnu.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.jnu.student.Data.HttpDataLoader;
import com.jnu.student.Data.Location;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaiduMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaiduMapFragment extends Fragment {

    private MapView mapView;
    public BaiduMapFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BaiduMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaiduMapFragment newInstance() {
        BaiduMapFragment fragment = new BaiduMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        mapView=rootView.findViewById(R.id.bmapView);

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        mapView.getMap().setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        LatLng cenpt = new LatLng(22.255925,113.541112);
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();

        mapView.getMap().setMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus));

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpDataLoader dataLoader=new HttpDataLoader();
                String bookJsonData= dataLoader.getHttpData("http://file.nidama.net/class/mobile_develop/data/bookstore2022.json");
                List<Location> locations=dataLoader.ParseJsonData(bookJsonData);

                BaiduMapFragment.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AddMarkersOnMap(locations);
                    }
                });
            }
        }).start();


        mapView.getMap().setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(BaiduMapFragment.this.getContext(),"Marker clicked",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return rootView;
    }

    private void AddMarkersOnMap(List<Location> locations) {
        BitmapDescriptor bitmap= BitmapDescriptorFactory.fromResource(R.drawable.home);
        for (Location bookshelf: locations) {
            LatLng bookPoint = new LatLng(bookshelf.getLatitude(),bookshelf.getLongitude());

            OverlayOptions options = new MarkerOptions().position(bookPoint).icon(bitmap);
            mapView.getMap().addOverlay(options);
            mapView.getMap().addOverlay(new TextOptions().bgColor(0xAAFFFF00)
                    .fontSize(32)
                    .fontColor(0xFFFF00FF).text(bookshelf.getName()).position(bookPoint));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}