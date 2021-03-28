package com.bodycodi.test.common;

import com.bodycodi.test.dto.TokenDto;
import com.bodycodi.test.dto.UserDto;
import com.bodycodi.test.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //로그
        logger.debug("{} 를 호출했습니다.", handler.toString());

        //서버와 인증을 하기를 원하는 클라이언트는 Authorization 요청 헤더 필드에 인증 정보를 포함함
        String token = request.getHeader("Authorization");

        try {
            // authorization으로부터 type과 credential을 분리
            String[] split = token.split(" ");  //예 : Basic dGVzdDpwYSQkd29yZA==
            String type = split[0];         //예 : Basic
            String credential = split[1];   //예 : dGVzdDpwYSQkd29yZA==

            if ("Basic".equalsIgnoreCase(type)) {
                // credential을 디코딩하여 username과 password를 분리
                String decoded = new String(Base64Utils.decodeFromString(credential));
                String[] username = decoded.split(":"); //예 : test

                UserDto user = userRepository.findUser(username[0]);    //username으로 조회한다.
                if (user == null){
                    logger.debug("Invalid credentials");
                    throw new RuntimeException("Invalid credentials");
                } else {
                    request.setAttribute("user", user);     //토큰 발행
                   return true;
                }

            } else {
                logger.debug("Unsupported type: " + type);
                throw new RuntimeException("Unsupported type: " + type);
            }

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            logger.debug("Invalid credentials");
            throw new RuntimeException("Invalid credentials");
        } catch (NullPointerException ex) {
            logger.debug("No login infomation");
            throw new RuntimeException("No login infomation");
        }
    }
}
