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
    static private MessageListAdapter mMessageAdapter;

    ActivityMessageListBinding mBinding;

    static final ArrayList<Message> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMessageListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());



        messageList.add(new Message("Hello", false, Calendar.getInstance().getTime()));
        messageList.add(new Message("How are you?", false, Calendar.getInstance().getTime()));
        messageList.add(new Message("I'm great!", true, Calendar.getInstance().getTime()));

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

                String result = Shuri.ping(mBinding.edittextChatbox.getText().toString());

                mBinding.edittextChatbox.setText("");
                messageList.add(newMessage);
                mMessageAdapter.notifyDataSetChanged();
                mMessageRecycler.smoothScrollToPosition(messageList.size());

                messageList.add(new Message(result, true, Calendar.getInstance().getTime()));
                mMessageAdapter.notifyDataSetChanged();
            }
        });
    }
    
}