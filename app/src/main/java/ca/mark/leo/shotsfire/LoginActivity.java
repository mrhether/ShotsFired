package ca.mark.leo.shotsfire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_button)
    LoginButton loginButton;
    @Bind(R.id.progressBar)
    View loadingView;

    CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();

        AccessToken currentToken = AccessToken.getCurrentAccessToken();
        if (currentToken != null && !currentToken.isExpired()) {
            onFacebookAccessTokenChange(currentToken);
        } else {
            loginButton.setReadPermissions("user_friends");
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                @Override
                public void onSuccess(LoginResult loginResult) {
                    onFacebookAccessTokenChange(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {
                    // App code
                }

                @Override
                public void onError(FacebookException exception) {
                    Log.d("LEOTEST", "wow fam" + exception.toString());
                    // App code
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void onFacebookAccessTokenChange(AccessToken token) {
        Firebase ref = ShotsFiredApplication.getFirebaseRef();
        if (token != null) {
            showLoadingScreen();
            ref.authWithOAuthToken("facebook", token.getToken(), new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    hideLoadingScreen();

                }
                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    Log.d("LEOTEST", "wow firebasefam" + firebaseError.toString());
                    hideLoadingScreen();
                }
            });
        } else {
        /* Logged out of Facebook so do a logout from the Firebase app */
            ref.unauth();
        }
    }

    private void showLoadingScreen() {
        loadingView.setVisibility(View.VISIBLE);
    }

    private void hideLoadingScreen() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }


}
