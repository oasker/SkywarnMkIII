package com.example.oliverasker.skywarnmarkii.Tasks;

import android.os.AsyncTask;

import com.amazonaws.AmazonServiceException;
import com.example.oliverasker.skywarnmarkii.Activities.MainActivity;
import com.example.oliverasker.skywarnmarkii.Callbacks.ICallback;
import com.example.oliverasker.skywarnmarkii.Managers.DynamoDBManager;
import com.example.oliverasker.skywarnmarkii.Mappers.SkywarnWSDBMapper;

import java.util.ArrayList;

public class MyAsyncTask extends AsyncTask<Void, Void, ArrayList<SkywarnWSDBMapper>>  {
    public ICallback delegate = null;
    ArrayList<SkywarnWSDBMapper> weatherList = null;

    public MyAsyncTask(ICallback delegate){
        this.delegate=delegate;
    }

    @Override
    protected ArrayList<SkywarnWSDBMapper> doInBackground(Void... voids) {
        weatherList = null;
        weatherList = DynamoDBManager.getTableRow();
        if (weatherList != null) {
            try {
                for (SkywarnWSDBMapper w : weatherList) {
                }
            } catch (AmazonServiceException ex) {
                MainActivity.clientManager.wipeCredentialsonAuthError(ex);
            }
        }
        return weatherList;
    }

    //Handles data after thread finishes
    @Override
    protected void onPostExecute(ArrayList<SkywarnWSDBMapper> result){
        delegate.processFinish(result);
        delegate = null;
    }
}
