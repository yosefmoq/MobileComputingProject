package com.app.mobilecomputingproject.ui.fragments.movies;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.mobilecomputingproject.Models.VideoModel;
import com.app.mobilecomputingproject.databinding.MoviesFragmentBinding;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    ExoPlayer player;

    MoviesFragmentBinding moviesFragmentBinding;
    FirebaseFirestore firebaseFirestore;
    SweetAlertDialog sweetAlertDialog;
    ArrayList<VideoModel> videoModels = new ArrayList<>();
    int runningVideo = 0;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        moviesFragmentBinding = MoviesFragmentBinding.inflate(inflater,container,false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        sweetAlertDialog = new SweetAlertDialog(requireActivity(),SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setCancelable(false);
        return moviesFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore.collection("Video").get().addOnCompleteListener(command -> {
            for(DocumentSnapshot documentSnapshot :command.getResult().getDocuments()){
                VideoModel videoModel =documentSnapshot.toObject(VideoModel.class);
                videoModels.add(videoModel);
            }
//            initVideo(videoModels.get(0).getLink());
            runningVideo = 1;
        });
        moviesFragmentBinding.btnNext.setOnClickListener(view1->{
            if(runningVideo!=0){
                if(runningVideo!=videoModels.size()-1){
                    runningVideo++;
                    initVideo(videoModels.get(runningVideo).getLink());

                }
            }
        });
        moviesFragmentBinding.btnPrevious.setOnClickListener(v -> {
            if(runningVideo!=0){
                if(runningVideo!=0){
                    runningVideo--;
                    initVideo(videoModels.get(runningVideo).getLink());

                }
            }
        });
    }
    private void initVideo(String url) {
        if(runningVideo!=0){
            releasePlayer();
            currentWindow = 0;
            playbackPosition = 0;
        }
        player = ExoPlayerFactory.newSimpleInstance(requireContext());
        moviesFragmentBinding.sep.setPlayer(player);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(requireContext(), "exo-player");
        Uri uri = Uri.parse(url);

        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        player.prepare(mediaSource,false,false);

    }

    private void releasePlayer(){
        if(player != null){
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(runningVideo!=0){
            initVideo(videoModels.get(runningVideo).getLink());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        releasePlayer();
    }
}