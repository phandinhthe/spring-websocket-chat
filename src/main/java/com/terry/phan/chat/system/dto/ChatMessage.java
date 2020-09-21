package com.terry.phan.chat.system.dto;

public class ChatMessage {
    private String from;
    private String text;
    private String recipient;
    private String time;

    public ChatMessage() { }

    public static Builder builder() {
        return new Builder();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class Builder {
        private ChatMessage chatMessage = null;

        private Builder() {
            chatMessage = new ChatMessage();
        }

        public Builder recipient(String recipient) {
            chatMessage.setRecipient(recipient);
            return this;
        }

        public Builder text(String txt) {
            chatMessage.setText(txt);
            return this;
        }

        public Builder from(String from) {
            chatMessage.setFrom(from);
            return this;
        }

        public Builder time(String time) {
            chatMessage.setTime(time);
            return this;
        }

        public ChatMessage build() {
            return chatMessage;
        }
    }
}
