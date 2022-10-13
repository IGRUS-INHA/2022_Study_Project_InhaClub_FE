package Team7.InDaClub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class EmailAuthService {
    @Autowired
    JavaMailSender emailSender;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String to)throws Exception{
        System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 번호 : "+ePw);
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);//보내는 대상
        message.setSubject("회원가입 이메일 인증");//제목

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
        message.setFrom(new InternetAddress("properties email 쓰세용!","Babble"));//보내는 사람

        return message;
    }

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

    public String sendSimpleMessage(String to) throws Exception {
        MimeMessage message = createMessage(to);

        try {
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}
