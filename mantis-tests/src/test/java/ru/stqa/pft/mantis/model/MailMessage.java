package ru.stqa.pft.mantis.model;

public class MailMessage {

    /**
     * поля, кому пришло письмо и сам текс письма
     */
    public String to;

    public String text;

    public MailMessage (String to, String text)
    {
        this.to=to;
        this.text=text;
    }
}

