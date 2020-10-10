package com.example.be_smarthack;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;
import com.uber.sdk.android.core.Deeplink;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RequestDeeplink;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.be_smarthack.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();

    Fragment mActiveFragment;

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionConfiguration config = new SessionConfiguration.Builder()
                // mandatory
                .setClientId("hGwDJVeKLgwBPAnAOw81aqADOPVKaQNg")
                // required for enhanced button features
                .setClientSecret("nHxfFeT5rJQ0ndal4RfHkZwSsbu4ttGNDAoA8VRW")
                .setServerToken("JA.VUNmGAAAAAAAEgASAAAABwAIAAwAAAAAAAAAEgAAAAAAAAH4AAAAFAAAAAAADgAQAAQAAAAIAAwAAAAOAAAAzAAAABwAAAAEAAAAEAAAAEDkVB4u33HSSRCiEqulP0WnAAAAZm9gyOXlq4u-cHI8Ld_0v4EUspyEvxWGnLGdcVI4nntc8GOdHPFjC4ffjtGCvkfnCKYLP9JA1LQU5ZG3a7rN5jU2wiO64xuOWz6B7hUySYFoG2S8oMYw6E4oK_LIvURfplZFgs7W-b4HSvoc9LqXA5hvPbXsjq27zk8jjB_GX8zrAnqGszL_Cb5eLRDz56p51I4tra61oRtTgReEDt1VufG0Ta2jZpgADAAAACYWJD7YvQDPEZreFCQAAABiMGQ4NTgwMy0zOGEwLTQyYjMtODA2ZS03YTRjZjhlMTk2ZWU")
                .build();

        RideParameters rideParams = new RideParameters.Builder()
                .setProductId("a1111c8c-c720-46c3-8534-2fcdd730040d")
                .setPickupLocation(37.775304, -122.417522, "Uber HQ", "1455 Market Street, San Francisco, California")
                .setDropoffLocation(37.795079, -122.4397805, "Embarcadero", "One Embarcadero Center, San Francisco")
                .build();

        RequestDeeplink deeplink = new RequestDeeplink.Builder(this.getApplicationContext())
                .setSessionConfiguration(config)
                .setRideParameters(rideParams)
                .build();


        deeplink.execute();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    IamAuthenticator authenticator = new IamAuthenticator("NAL-qk0LqN78hEwL8nuOKe7mfPoN-9j28L6mcUaBHcJI");
                    Assistant assistant = new Assistant("2020-09-24", authenticator);
                    assistant.setServiceUrl("https://api.us-east.assistant.watson.cloud.ibm.com");


                    CreateSessionOptions SESSION_OPTIONS = new CreateSessionOptions.Builder().assistantId("203f51f7-e733-403a-b2ab-ebc746990181").build();
                    Response<SessionResponse> call = assistant.createSession(SESSION_OPTIONS).execute();

                    SessionResponse SESSION_RESPONSE = call.getResult();
                    Log.d("SHURI - Session", SESSION_RESPONSE.toString());

                    MessageInput input = new MessageInput.Builder()
                            .messageType("text")
                            .text("I am afraid")
                            .build();

                    MessageOptions options = new MessageOptions.Builder("203f51f7-e733-403a-b2ab-ebc746990181", SESSION_RESPONSE.getSessionId())
                            .input(input)
                            .build();

                    MessageResponse response = assistant.message(options).execute().getResult();

                    Log.d("SHURI - Test", response.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


        //        Log.d("SHURI", SESSION_RESPONSE.toString());

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        placeFragment(new HomeFragment());
    }

    public void placeFragment(Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(mBinding.containerFrameLayout.getId(), fragment)
                .commit();
    }

    public void replaceFragment(Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(mBinding.containerFrameLayout.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }
}