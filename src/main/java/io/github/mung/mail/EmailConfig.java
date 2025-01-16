package io.github.mung.mail;

import static io.github.mung.constants.GlobalVars.REPORT_TITLE;

public class EmailConfig {
    //Remember to create an app password (App Password) for Gmail before sending
    //If you use Hosting's email, it's normal
    //Enable Override Report and Send mail in config file => src/test/resources/config/config.properties
    //OVERRIDE_REPORTS=yes
    //send_email_to_users=yes

    public static final String SERVER = "smtp.gmail.com";
    public static final String PORT = "587";

    public static final String FROM = "mung.thai@gmail.com";
    public static final String PASSWORD = "******";

    public static final String[] TO = {"mung.thai@gmail.com"};
    public static final String SUBJECT = REPORT_TITLE;
}
