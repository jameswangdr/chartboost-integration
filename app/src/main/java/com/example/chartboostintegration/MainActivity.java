package com.example.chartboostintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Privacy.model.CCPA;
import com.chartboost.sdk.Privacy.model.GDPR;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button cacheInterButton;
    Button showInterButton;
    Button cacheRewardButton;
    Button showRewardButton;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Chartboost.addDataUseConsent(this, new GDPR(GDPR.GDPR_CONSENT.BEHAVIORAL));
        Chartboost.addDataUseConsent(this, new CCPA(CCPA.CCPA_CONSENT.OPT_IN_SALE));

        initSDK();
        cacheInterButton = (Button)findViewById(R.id.btn1);
        showInterButton = (Button)findViewById(R.id.btn2);
        cacheRewardButton = (Button)findViewById(R.id.btn3);
        showRewardButton = (Button)findViewById(R.id.btn4);
        txtView = (TextView)findViewById(R.id.txt);

        cacheInterButton.setOnClickListener(this);
        showInterButton.setOnClickListener(this);
        cacheRewardButton.setOnClickListener(this);
        showRewardButton.setOnClickListener(this);

        showInterButton.setVisibility(View.INVISIBLE);
        showRewardButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn1) {
            txtView.setText("Interstitial Cached");
            cacheInterstitialAd();
            if (Chartboost.hasInterstitial(CBLocation.LOCATION_DEFAULT)) {
                showInterButton.setVisibility(View.VISIBLE);
            }
        }
        if (view.getId() == R.id.btn2) {
            txtView.setText("Show Interstitial");
            showInterstitialAd();
        }
        if (view.getId() == R.id.btn3) {
            txtView.setText("Reward Video Cached");
            cacheRewardedVideo();
            if (Chartboost.hasRewardedVideo(CBLocation.LOCATION_DEFAULT)) {
                showRewardButton.setVisibility(View.VISIBLE);
            }
        }
        if (view.getId() == R.id.btn4) {
            txtView.setText("Show Reward Video");
            showRewardedVideo();
        }
    }

    private void initSDK() {
//        Given appID & appSignature
//        String appId = "33b8446a0af32a0dfa10c8";
//        String appSignature = "8ba672dd317a6c4459eb482724f1180a740e0b08";

//        Personal appID & appSignature
//        String appId = "5f72bc1a732881080d2ea366";
//        String appSignature = "fb30d00c0cdc7d2df4ae749da62d44ba3abbb8d0";

//        Sample App appID & appSignature
        String appId = "4f7b433509b6025804000002";
        String appSignature = "dd2d41b69ac01b80f443f5b6cf06096d457f82bd";

        Chartboost.setDelegate(delegateInit);
        Chartboost.setLoggingLevel(CBLogging.Level.ALL);
        Chartboost.startWithAppId(getApplicationContext(), appId, appSignature);
    }

    public static void cacheInterstitialAd() {
        Chartboost.cacheInterstitial(CBLocation.LOCATION_DEFAULT);
    }

    public static void showInterstitialAd() {
        Chartboost.showInterstitial(CBLocation.LOCATION_DEFAULT);
    }

    public static void cacheRewardedVideo() {
        Chartboost.cacheRewardedVideo(CBLocation.LOCATION_DEFAULT);
    }

    public static void showRewardedVideo() {
        Chartboost.showRewardedVideo(CBLocation.LOCATION_DEFAULT);
    }

    private ChartboostDelegate delegateInit = new ChartboostDelegate() {
        @Override
        public void didInitialize() {
            super.didInitialize();
            Toast.makeText(MainActivity.this.getApplicationContext(), "SDK is initialized", Toast.LENGTH_SHORT).show();
        }
    };
}