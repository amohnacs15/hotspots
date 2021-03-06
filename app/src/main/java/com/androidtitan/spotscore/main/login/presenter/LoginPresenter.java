package com.androidtitan.spotscore.main.login.presenter;

import android.content.Context;
import android.util.Log;


import com.androidtitan.spotscore.R;
import com.androidtitan.spotscore.common.BasePresenter;
import com.androidtitan.spotscore.main.login.LoginMvp;
import com.androidtitan.spotscore.main.login.ui.LoginActivity;
import com.androidtitan.spotscore.main.login.web.LoginInteractor;

import javax.inject.Inject;


/**
 * Created by amohnacs on 4/21/16.
 */
public class LoginPresenter extends BasePresenter<LoginMvp.View> implements LoginMvp.Presenter,
        LoginMvp.Model.OnLoginFinishedListener, LoginMvp.Model.OnSignUpFinishedListener, LoginMvp.Model.OnPasswordFinishedListener{
    private final String TAG = getClass().getSimpleName();

    private Context mContext;
    private LoginMvp.Model mInteractor;
    //private Subscription mSubscription

    private LoginActivity mActivity;

    //todo////////////////////////////////

     /*
            Upon login we need to pull information from shared preferences and firbase and populate
            our user objects.
     */

    //todo///////////////////////////////

    @Inject
    public LoginPresenter(Context context) {
        mContext = context;
        this.mInteractor = new LoginInteractor(context);
    }

    @Override
    public void attachView(LoginMvp.View mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        //if (mSubscription != null) mSubscription.unsubscribe();
    }

    @Override
    public void authenticateLogin(String email, String password) {
        Log.e(TAG, "authenticateLogin: " + email + ", " + password);
        mInteractor.authenticateLogin(email, password, this);
    }

    @Override
    public void onAuthenticationSuccess() {
        Log.e(TAG, "authenticate success");
        getMvpView().launchScoreActivity();
    }

    @Override
    public void onAuthenticationFailure() {
        getMvpView().showLoginFailureSnack(mContext.getResources().getString(R.string.auth_failed));

    }

    @Override
    public void createAuthenticatedUser(String email, String password) {
        mInteractor.createAuthenticatedUser(email, password, this);
    }

    @Override
    public void resetPassword(String email) {
        mInteractor.resetPassword(email, this);
    }

    @Override
    public void onSignUpSuccess() {
        getMvpView().showSignUpSuccessDialog();
    }

    @Override
    public void onSignUpFailure() {
        getMvpView().showSignUpFailureSnack(mContext.getResources().getString(R.string.signup_eror));
    }

    @Override
    public void onPasswordSuccess() {
        getMvpView().showPasswordResetSnack(mContext.getResources().getString(R.string.pw_reset_success));
    }


    /* todo: this is where we convert our Observables from the Datamanager to Results
    public void loadRibots() {
        checkViewAttached();
        mSubscription = mDataManager.getRibots()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Ribot>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Ribot> ribots) {
                        if (ribots.isEmpty()) {
                            getMvpView().showRibotsEmpty();
                        } else {
                            getMvpView().showRibots(ribots);
                        }
                    }
                });
     */
}
