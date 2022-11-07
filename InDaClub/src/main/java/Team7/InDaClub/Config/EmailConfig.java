package Team7.InDaClub.Config;

import lombok.Getter;
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
@Getter
public class EmailConfig {

    /** 관리 email 계정의 id */
    @Value("${mail.id}")
    private String id;

    /** 관리 email 계정의 password */
    @Value("${mail.password}")
    private String password;

    /** 관리 email 계정 host */
    @Value("${mail.host}")
    private String host;

    /** smtp port */
    @Value("${mail.port}")
    private int port;

    /** smtp auth 여부 */
    @Value("${mail.smtp.auth}")
    private boolean auth;

    /** smtp 에서 TLS 통신 사용 */
    @Value("${mail.smtp.starttls.enable}")
    private boolean startTLS;

    /** smtp 에서 tls 통신 사용 */
    @Value("${mail.smtp.starttls.required}")
    private boolean startTLSRequired;


    @Value("${mail.smtp.ssl.trust}")
    private String trustServer;

    @Value("${mail.smtp.ssl.enable}")
    private boolean sslEnable;

    @Value("${mail.smtp.auth.connectiontimeout}")
    private int connectionTimeout;

    @Value("${mail.smtp.auth.timeout}")
    private int timeout;

    @Value("${mail.smtp.auth.writetimeout}")
    public int writeTimeout;

    /** Spring email auth service 의 설정 */
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
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
        pt.put("mail.smtp.auth", auth);
        pt.put("mail.smtp.auth.connectiontimeout", connectionTimeout);
        pt.put("mail.smtp.auth.timeout", timeout);
        pt.put("mail.smtp.auth.writetimeout", writeTimeout);
        pt.put("mail.smtp.starttls.enable", startTLS);
        pt.put("mail.smtp.starttls.required", startTLSRequired);
        pt.put("mail.smtp.ssl.trust", trustServer);
        pt.put("mail.smtp.ssl.enable", sslEnable);
        return pt;
    }
}