package com.app.mobilecomputingproject.ui.fragments.history;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.mobilecomputingproject.R;
import com.app.mobilecomputingproject.databinding.HistoryFragmentBinding;
import com.app.mobilecomputingproject.ui.activities.DetailsActivity;

public class HistoryFragment extends Fragment {


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    HistoryFragmentBinding historyFragmentBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        historyFragmentBinding = HistoryFragmentBinding.inflate(inflater,container,false);


        return historyFragmentBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyFragmentBinding.cvAboutQuds.setOnClickListener(v -> {
            startActivity(DetailsActivity.getDetailsActivityIntent(requireActivity(),3));
        });
        historyFragmentBinding.cvBattle.setOnClickListener(v -> {
            startActivity(DetailsActivity.getDetailsActivityIntent(requireActivity(),2));

        });
        historyFragmentBinding.cvDoors.setOnClickListener(v -> {
            startActivity(DetailsActivity.getDetailsActivityIntent(requireActivity(),1));

        });
        historyFragmentBinding.cvFamous.setOnClickListener(v -> {
            startActivity(DetailsActivity.getDetailsActivityIntent(requireActivity(),4));

        });
    }
}