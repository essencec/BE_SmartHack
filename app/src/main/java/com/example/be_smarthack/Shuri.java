package com.example.be_smarthack;

import android.util.Log;

import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.DialogSuggestion;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;

import java.util.Arrays;

public class Shuri {
    public static void ping() {
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
                            .text("I need an attendant.")
                            .build();

                    MessageOptions options = new MessageOptions.Builder("203f51f7-e733-403a-b2ab-ebc746990181", SESSION_RESPONSE.getSessionId())
                            .input(input)
                            .build();

                    MessageResponse response = assistant.message(options).execute().getResult();

                    Log.d("SHURI - RESP: ", response.getOutput().getGeneric().get(0).text());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


        //        Log.d("SHURI", SESSION_RESPONSE.toString());
    }
}
