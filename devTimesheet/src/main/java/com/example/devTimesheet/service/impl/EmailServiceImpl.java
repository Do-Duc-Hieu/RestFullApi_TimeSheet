package com.example.devTimesheet.service.impl;

import com.example.devTimesheet.entity.User;
import com.example.devTimesheet.repository.UserRepository;
import com.example.devTimesheet.service.EmailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;


@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    @Value("${jwt.signerKey}")
    private String jwtSecret;
    private final long jwtExp = 5 * 1000 * 3600;
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final String adminEmail = "17082002doduchieu@gmail.com";
    private final String baseUrl = "http://localhost:8080/admin/resetPasswordByEmail/";
    @Async
    @Override
    public void sendEmail(String userName, String newPassword) throws MessagingException {

        User user = userRepository.findUserByUsername(userName)
                .orElseThrow(()-> new RuntimeException("Position not found"));

        String token = Jwts.builder()
                .setSubject("hieu2")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExp))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        String resetUrl = baseUrl + user.getId() + "?newPassword=" + newPassword+"&token="+token;
        String messageText = "Người dùng: " + user.getUsername() + " yêu cầu thay đổi mật khẩu thành: " + newPassword +
                "\n\nNhấn vào nút dưới đây để phê duyệt yêu cầu:\n" +
                "<a href=\"" + resetUrl + "\">Reset Password</a>";

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(adminEmail);
            helper.setSubject("Yêu cầu thay đổi mật khẩu");
            helper.setText(messageText, true);
            mailSender.send(message);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
