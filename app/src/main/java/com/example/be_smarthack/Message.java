package com.example.be_smarthack;

import java.util.Date;

class Message {
    private String message;
    private boolean isChatBot;
    private Date timeStamp;

    public Message() {}

    public Message(String message, boolean isChatBot, Date date) {
        this.setMessage(message);
        this.setIsChatBot(isChatBot);
        this.setDate(date);
    }

    public String getText() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isChatBot() {
        return isChatBot;
    }

    public void setIsChatBot(boolean chatBot) {
        isChatBot = chatBot;
    }

    public Date getDate() {
        return timeStamp;
    }

    public void setDate(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
