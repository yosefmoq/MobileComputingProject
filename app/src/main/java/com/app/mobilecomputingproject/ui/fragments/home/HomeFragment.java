package com.app.mobilecomputingproject.ui.fragments.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.mobilecomputingproject.Aadpters.NewsAdapter;
import com.app.mobilecomputingproject.Models.NewsData;
import com.app.mobilecomputingproject.databinding.HomeFragmentBinding;
import com.app.mobilecomputingproject.listeners.OnNewsClickListener;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements OnNewsClickListener {

    ArrayList<NewsData> newsData = new ArrayList<>();
    NewsAdapter newsAdapter;
    HomeFragmentBinding homeFragmentBinding;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    SweetAlertDialog sweetAlertDialog;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        newsAdapter =new  NewsAdapter(newsData,this);
        homeFragmentBinding =HomeFragmentBinding.inflate(inflater,container,false);
        sweetAlertDialog = new SweetAlertDialog(requireActivity(),SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setCancelable(false);
        return homeFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeFragmentBinding.rvNews.setLayoutManager(new LinearLayoutManager(requireContext()));
        homeFragmentBinding.rvNews.setAdapter(newsAdapter);

        observeData();
        getNewsData();
    }

    private void observeData() {
    }
    public void getNewsData() {
        sweetAlertDialog.show();
        Calendar c = Calendar.getInstance();
        String date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.DAY_OF_MONTH)) + "-" + (c.get(Calendar.MONTH)+1);
        Volley.newRequestQueue(requireContext()).add(new JsonObjectRequest(Request.Method.GET, "https://free-news.p.rapidapi.com/v1/search?q=القدس&lang=ar", null, response -> {
            sweetAlertDialog.hide();
            ArrayList<NewsData> arrayNewsData = new ArrayList<>();
            try {
                String status = response.getString("status");

                JSONArray jsonArray = (JSONArray) response.get("articles");
                if(status.equals("ok")){
                    for(int i = 0 ; i<jsonArray.length()-1;i++){
                        NewsData newsData = new NewsData();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        newsData.setImage(jsonObject.getString("media"));
                        newsData.setDescription(jsonObject.getString("summary"));
                        newsData.setNews_text(jsonObject.getString("title"));
                        arrayNewsData.add(newsData);
                    }
                    newsData.clear();
                    newsData.addAll(arrayNewsData);
                    newsAdapter.notifyDataSetChanged();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            sweetAlertDialog.hide();
            Log.v("ttt", error.getLocalizedMessage()+"");
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("x-rapidapi-key","08c1f75f43mshb92cea66dd4216cp1bdb05jsn99e3cd94c194");
                hashMap.put("x-rapidapi-host","free-news.p.rapidapi.com");
                hashMap.put("useQueryString","true");
                return hashMap;
            }
        });
    }

    @Override
    public void onNewsClick(int id, String title) {
        Toast.makeText(requireContext(), title, Toast.LENGTH_SHORT).show();
    }
}