package com.androidtitan.spotscore.main.play;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.androidtitan.spotscore.common.MvpView;
import com.androidtitan.spotscore.main.data.Venue;
import com.androidtitan.spotscore.main.play.presenter.ScorePresenter;
import com.androidtitan.spotscore.main.play.ui.ScoreActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by amohnacs on 5/16/16.
 */
public interface PlayMvp {

    interface Model {

        interface ScoreViewListener {
            void onUserProfileSetFinished();
        }

        Observable<Venue> getVenuesOneByOne(double latitude, double longitude);
        Observable<Venue> getAdditionalVenueInfo(String venueIdentifier);

        void setUserProfile(ScoreViewListener listener);

    }

    interface View extends MvpView {

        void updateScore(double average);
        void showFragment(Fragment fragment);

        void onUserProfileSetFinished();
    }

    interface Presenter {

        void attachView(View mvpView);
        void detachView();

        void takeActivity(ScoreActivity activity);

        void connectGoogleApiClient();
        void disconnectGoogleApiClient();
        boolean googleApiIsConnected();

        void setupUserProfile();

        void showFragment(Fragment fragment, Bundle arguments);

        LatLng getLastKnownLocation();
        void calculateAndSetScore();

        ArrayList<Venue> getNearbyVenuesList();

    }
}
