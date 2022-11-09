package Team7.InhaClub.Service;

import Team7.InhaClub.Config.EmailConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/** 이메일 인증 관련 서비스 */
@Service
@RequiredArgsConstructor
public class EmailAuthService {

    /** email sender */
    @Autowired
    JavaMailSender emailSender;

    /** 이메일 양식 */
    private final SpringTemplateEngine templateEngine;
    /** 이메일 인증번호 */
    public static final String ePw = createKey();

    /** 인증용 이메일 생성 */
    private MimeMessage createMessage(String to) throws Exception{
        System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 번호 : "+ePw);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to); //보내는 대상
        message.setFrom("vkwjs336@naver.com"); // 서버 메일 id
        message.setSubject("회원가입 이메일 인증"); // 제목

        String msg="";
        msg+= "<div style='margin:100px;'>";
        msg+= "<h1> 안녕하세요. </h1>";
        msg+= "<br>";
        msg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msg+= "<br>";
        msg+= "<p>감사합니다!<p>";
        msg+= "<br>";
        msg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msg+= "<div style='font-size:130%'>";
        msg+= "CODE : <strong>";
        msg+= ePw+"</strong><div><br/> ";
        msg+= "</div>";
        message.setText(msg, "utf-8", "html");//내용

        return message;
    }

    /** 이메일 인증키 생성 */
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            int n = r.nextInt() % 3;

            switch (n) {
                case 0: // lower alphabet
                    key.append((char) ((int) r.nextInt(26) + 97));
                    break;

                case 1: // high alphabet
                    key.append((char) ((int) r.nextInt(26) + 65));
                    break;

                default: // number
                    key.append(r.nextInt(10));
                    break;
            }
        }
        return key.toString();
    }

    /** 이메일 전송 */
    public String sendSimpleMessage(String to) throws Exception {
        MimeMessage message = createMessage(to);

        try {
            emailSender.send(message); // emailSender 를 통해 이메일 보낸다.
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("IllegalArgumentException - Cannot send the mail.");
        }
        return ePw; // 인증을 위해 인증키를 남긴다.
    }
}
