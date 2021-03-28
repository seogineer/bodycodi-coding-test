package com.bodycodi.test.repository;

import com.bodycodi.test.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

@Repository
public class UserRepository {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(UserDto user) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int re = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users(user_name, password) VALUES(?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, bytesToHex(sha256(user.getPassword())));
            return ps;
        }, keyHolder);

        if (re == 1) {
            int userId = keyHolder.getKey().intValue();// 저장한 사용자의 아이디 가지고 오기
            return userId;
        } else {
            throw new RuntimeException("insert error");
        }
    }

    public UserDto findUser(String username) {
        try {

            return this.jdbcTemplate.queryForObject("SELECT id, user_name, password FROM users WHERE user_name LIKE ?", new String[]{username}, new RowMapper<UserDto>() {
                @Override
                public UserDto mapRow(ResultSet resultSet, int i) throws SQLException {
                    UserDto user = new UserDto();
                    //가져온 사용자 정보 매핑
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("user_name"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            });

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //비밀번호 암호화
    public byte[] sha256(String password) {
        byte[] digestedPassword = {};
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digestedPassword = digest.digest(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return digestedPassword;        //digest 생성
    }

    //byte[] to String
    public String bytesToHex(byte[] hash) {
        StringBuilder builder = new StringBuilder();
        for(byte b : hash) {
            builder.append(String.format("%02x", b));
        }

        String decoded = new String(Base64Utils.decodeFromString(builder.toString()));
        return builder.toString();
    }
}
