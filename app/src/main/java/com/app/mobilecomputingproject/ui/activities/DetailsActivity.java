package com.app.mobilecomputingproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.app.mobilecomputingproject.Models.InformationData;
import com.app.mobilecomputingproject.R;
import com.app.mobilecomputingproject.databinding.ActivityDetailsBinding;
import com.app.mobilecomputingproject.helper.MyDatabase;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    static int typeA;
    ActivityDetailsBinding activityDetailsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailsBinding = ActivityDetailsBinding.inflate(LayoutInflater.from(DetailsActivity.this),null,false);
        setContentView(activityDetailsBinding.getRoot());
        getData(typeA);
        switch (typeA){
            case 1:{
                activityDetailsBinding.tvTitle.setText("أبواب القدس");
            }
            break;
            case 2:{
                activityDetailsBinding.tvTitle.setText("معارك القدس");

            }
            break;
            case 3:{
                activityDetailsBinding.tvTitle.setText("عن القدس");

            }
            break;
            case 4:{
                activityDetailsBinding.tvTitle.setText("الأماكن المشهورة");

            }

        }
    }

    public static Intent getDetailsActivityIntent(Activity activity,int type){
        typeA = type;

        return new Intent(activity,DetailsActivity.class);
    }
    public void getData(int category){
        ArrayList<InformationData> informationData = new MyDatabase(DetailsActivity.this).getData(category);
        StringBuilder stringBuilder = new StringBuilder();
        for(InformationData informationData1 : informationData){
            stringBuilder.append(informationData1.getDetails());
            stringBuilder.append("\n\n\n");
        }
        activityDetailsBinding.tvDetails.setText(stringBuilder.toString());
    }
}