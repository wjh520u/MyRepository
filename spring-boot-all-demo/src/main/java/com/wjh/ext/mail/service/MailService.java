package com.wjh.ext.mail.service;

/**
 * Created by summer on 2017/5/4.
 */
public interface MailService {

    public void sendSimpleMail(String from, String to, String subject, String content);

    public void sendHtmlMail(String from, String to, String subject, String content);

    public void sendAttachmentsMail(String from, String to, String subject, String content, String filePath);

    public void sendInlineResourceMail(String from, String to, String subject, String content, String rscPath, String rscId);

}
