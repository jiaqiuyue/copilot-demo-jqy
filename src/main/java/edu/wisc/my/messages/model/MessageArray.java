package edu.wisc.my.messages.model;

import java.util.List;

public class MessageArray {

  private Message[] messages;

  public MessageArray() {
  }

  public MessageArray(List<Message> someMessages) {
    Message[] arrayExample = {};
    this.messages = someMessages.toArray(arrayExample);
  }

  public Message[] getMessages() {
    return this.messages;
  }

  public void setMessages(Message[] messages) {
    this.messages = messages;
  }
}

