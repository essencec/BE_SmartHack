package com.example.be_smarthack;

class Message {
    private String message;
    private boolean isChatBot;

    public Message(String message, boolean isChatBot) {
        this.setMessage(message);
        this.setIsChatBot(isChatBot);
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
}
