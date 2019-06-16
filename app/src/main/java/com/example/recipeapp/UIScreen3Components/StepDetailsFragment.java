package com.example.recipeapp.UIScreen3Components;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.recipeapp.Model.Constants;
import com.example.recipeapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Fragment class for StepDetails activity
 * */
public class StepDetailsFragment extends Fragment {

    @BindView(R.id.player_view)
    SimpleExoPlayerView
            mPlayerView;
    @BindView(R.id.description_text_view)
    TextView descriptionTextView;
    @BindView(R.id.video_not_avail_image)
    ImageView videoNotAvailableImage;
    private SimpleExoPlayer mExoPlayer;

    public StepDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_step_details, container, false);
        ButterKnife.bind(this, rootView);

        Bundle bundle = getArguments();

        if (bundle != null) {

            String shortDescription = bundle.getString(Constants.SEND_RECIPE_SHORT_DESCRIPTION);
            String videoURL = bundle.getString(Constants.SEND_RECIPE_VIDEO_URL);

            //if there is no videoUrl in Json, exoplayer is replaced with image
            //explaining that no video is available for this step
            if (videoURL.equals("")) {
                mPlayerView.setVisibility(View.GONE);
                videoNotAvailableImage.setVisibility(View.VISIBLE);
            } else {
                videoNotAvailableImage.setVisibility(View.GONE);
                initializePlayer(Uri.parse(videoURL));
            }

            //changes layout if phone is in landscape mode
            if (rootView.findViewById(R.id.phone_video_landscape_layout) != null) {
                descriptionTextView.setVisibility(View.GONE);
            }

            descriptionTextView.setText(shortDescription);
        }
        return rootView;
    }

    private void initializePlayer(Uri mediaUrl) {

        if (mExoPlayer == null) {
            TrackSelector selector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), selector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(getContext(), "RecipeApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUrl, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);

            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mExoPlayer != null) {
            releasePlayer();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
