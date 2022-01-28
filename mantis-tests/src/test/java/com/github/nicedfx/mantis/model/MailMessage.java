package com.github.nicedfx.mantis.model;

public class MailMessage {

    String recipients;
    String content;

    public MailMessage(String recepients, String content) {
        this.recipients = recepients;
        this.content = content;
    }

    public String getRecipients() {
        return recipients;
    }

    public MailMessage withRecipients(String recepients) {
        this.recipients = recepients;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MailMessage withContent(String content) {
        this.content = content;
        return this;
    }
}
