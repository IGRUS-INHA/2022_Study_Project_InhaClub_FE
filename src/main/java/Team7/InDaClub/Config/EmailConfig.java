package Team7.InDaClub.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/** email.properties 의 설정값을 config 와 매칭시킴 */
@Configuration
@PropertySource("classpath:email.properties")
public class EmailConfig {

    /** smtp port */
    @Value("${mail.smtp.port}")
    private int port;

    /** smtp socket port */
    @Value("${mail.smtp.socketFactory.port}")
    private int socketPort;

    /** smtp auth 여부 */
    @Value("${mail.smtp.auth}")
    private boolean auth;

    /** smtp 에서 TLS 통신 사용 */
    @Value("${mail.smtp.starttls.enable}")
    private boolean startTLS;

    /** smtp 에서 tls 통신 사용 */
    @Value("${mail.smtp.starttls.required}")
    private boolean startTLSRequired;

    /** smtp fallback */
    @Value("${mail.smtp.socketFactory.fallback}")
    private boolean fallback;

    /** 관리 email 계정의 id */
    @Value("${AdminMail.id}")
    private String id;

    /** 관리 email 계정의 password */
    @Value("${AdminMail.password}")
    private String password;

    /** Spring email auth service 의 설정 */
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername(id);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(port);
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }

    /** email.properties 에서 설정을 받아와 java 화 */
    private Properties getMailProperties()
    {
        Properties pt = new Properties();
        pt.put("mail.smtp.socketFactory.port", socketPort);
        pt.put("mail.smtp.auth", auth);
        pt.put("mail.smtp.starttls.enable", startTLS);
        pt.put("mail.smtp.starttls.required", startTLSRequired);
        pt.put("mail.smtp.socketFactory.fallback",fallback);
        pt.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return pt;
    }
}
