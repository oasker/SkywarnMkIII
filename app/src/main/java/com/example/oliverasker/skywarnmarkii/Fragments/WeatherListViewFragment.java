package com.example.oliverasker.skywarnmarkii.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.oliverasker.skywarnmarkii.Activites.ViewReportActivity;
import com.example.oliverasker.skywarnmarkii.Adapters.SkywarnDBAdapter;
import com.example.oliverasker.skywarnmarkii.ICallback;
import com.example.oliverasker.skywarnmarkii.Mappers.SkywarnWSDBMapper;
import com.example.oliverasker.skywarnmarkii.R;
import com.example.oliverasker.skywarnmarkii.Tasks.GetAllRecordsForDayTask;
import com.example.oliverasker.skywarnmarkii.Tasks.MyAsyncTask;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by oliverasker on 2/19/17.
 */

public class WeatherListViewFragment extends Fragment implements ICallback {
    ListView listView;
    MyAsyncTask myAsync = new MyAsyncTask(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        Log.d(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment_home_weather_list_view, container, false);
        GetAllRecordsForDayTask getRecordsForDayTask = null;

        ArrayList<SkywarnWSDBMapper> data = new ArrayList<>();
        SkywarnWSDBMapper testReport = new SkywarnWSDBMapper();
        testReport.setDateSubmittedEpoch((long) 3442311);
        testReport.setDateSubmittedString("2/12/9000");
        data.add(testReport);

        getRecordsForDayTask = new GetAllRecordsForDayTask();
        getRecordsForDayTask.setContext(getContext());
        getRecordsForDayTask.delegate = this;
        getRecordsForDayTask.execute();






        listView = null;
        listView = (ListView)v.findViewById(R.id.weather_list_view);
        //listView = (ListView) findViewById(R.id.listView);
        SkywarnWSDBMapper[]reportArray = data.toArray(new SkywarnWSDBMapper[data.size()]);
        SkywarnDBAdapter skywarnAdapter = new SkywarnDBAdapter(getContext(), reportArray);

        //for(int i=0; i< data.length; i++)
        // System.out.println("Received in QueryLauncherActivity: "+data[i].getEventCity() + " " + data[i].getComments());
//        listView.setAdapter(skywarnAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id ) {
//                int itemPos = position;
//                SkywarnWSDBMapper itemValue = (SkywarnWSDBMapper) listView.getItemAtPosition(position);
//                launchViewReportActivity(itemValue);
//            }
//        });
        return v;
    }
    public void launchViewReportActivity(SkywarnWSDBMapper clickedReport){
        Intent intent = new Intent(getContext(), ViewReportActivity.class);
        intent.putExtra("selectedReport", clickedReport);
        startActivity(intent);
    }
    @Override
    public void processFinish(ArrayList<SkywarnWSDBMapper> result) {
        /*
         *  Once database row is received this interface method runs.
         *  We need to use the data immediately, so send data to listview
         */
        SkywarnWSDBMapper[] data = null;
        //data = null;
//        listView = null;
//        listView = (ListView)findViewById(R.id.weather_list_view);
        //listView = (ListView) findViewById(R.id.listView);
        data = result.toArray(new SkywarnWSDBMapper[result.size()]);
        SkywarnDBAdapter skywarnAdapter = new SkywarnDBAdapter(getContext(), data);

        //for(int i=0; i< data.length; i++)
        // System.out.println("Received in QueryLauncherActivity: "+data[i].getEventCity() + " " + data[i].getComments());
        listView.setAdapter(skywarnAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id ) {
                int itemPos = position;
                SkywarnWSDBMapper itemValue = (SkywarnWSDBMapper) listView.getItemAtPosition(position);
                launchViewReportActivity(itemValue);
            }
        });
        result = null;
    }
}