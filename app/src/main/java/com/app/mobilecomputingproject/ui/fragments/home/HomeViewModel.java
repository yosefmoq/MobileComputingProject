package com.app.mobilecomputingproject.ui.fragments.home;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.mobilecomputingproject.Models.NewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeViewModel extends ViewModel {
    private Context context;

    public HomeViewModel(Context context) {
        this.context = context;

    }

    public MutableLiveData<ArrayList<NewsData>> newsDataMutalbleLiveData = new MutableLiveData<>();


}