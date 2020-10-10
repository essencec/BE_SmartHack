package com.example.be_smarthack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.be_smarthack.databinding.ActivityMessageListBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class MessageListActivity extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;

    ActivityMessageListBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMessageListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        final ArrayList<Message> messageList = new ArrayList<>();

        messageList.add(new Message("Hi Shuri", false, Calendar.getInstance().getTime()));
        messageList.add(new Message("Hi T'Challa! How can I help you?", true,
                Calendar.getInstance().getTime()));
        messageList.add(new Message("I'm hungry", false, Calendar.getInstance().getTime()));
        messageList.add(new Message("Okay, would you like a flight attendant to bring you a snack?",
                true,
                Calendar.getInstance().getTime()));
        messageList.add(new Message("yes", false, Calendar.getInstance().getTime()));
        messageList.add(new Message("A flight attendant is on the way", true,
                Calendar.getInstance().getTime()));

        mMessageAdapter = new MessageListAdapter(this, messageList);
        mMessageRecycler = mBinding.reyclerviewMessageList;
        mMessageRecycler.setAdapter(mMessageAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        mMessageRecycler.setLayoutManager(layoutManager);

        mBinding.buttonChatboxSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message newMessage = new Message();
                newMessage.setMessage(mBinding.edittextChatbox.getText().toString());
                newMessage.setIsChatBot(false);
                newMessage.setDate(Calendar.getInstance().getTime());

                mBinding.edittextChatbox.setText("");
                messageList.add(newMessage);
                mMessageAdapter.notifyDataSetChanged();
                mMessageRecycler.smoothScrollToPosition(messageList.size());

                messageList.add(new Message("Okay. Here are your in-flight options.", true, Calendar.getInstance().getTime()));
                mMessageAdapter.notifyDataSetChanged();
            }
        });
    }
}