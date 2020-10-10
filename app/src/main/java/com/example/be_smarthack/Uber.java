package com.example.be_smarthack;

import android.content.Context;

import com.uber.sdk.android.rides.RequestDeeplink;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.rides.client.SessionConfiguration;

class Uber {
    public static void launchRide (Context context) {

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

        RequestDeeplink deeplink = new RequestDeeplink.Builder(context)
                .setSessionConfiguration(config)
                .setRideParameters(rideParams)
                .build();

        deeplink.execute();
    }
}
