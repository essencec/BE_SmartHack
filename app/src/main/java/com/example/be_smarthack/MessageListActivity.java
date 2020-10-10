package com.example.be_smarthack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        ArrayList<Message> messageList = new ArrayList<>();

        messageList.add(new Message("Hello", false));
        messageList.add(new Message("How are you?", false));
        messageList.add(new Message("I'm great!", true));

        mMessageAdapter = new MessageListAdapter(this, messageList);
        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageRecycler.setAdapter(mMessageAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        mMessageRecycler.setLayoutManager(layoutManager);
    }
}