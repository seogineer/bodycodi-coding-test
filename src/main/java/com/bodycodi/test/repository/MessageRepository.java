package com.bodycodi.test.repository;

import com.bodycodi.test.dto.ContentDto;
import com.bodycodi.test.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class MessageRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public int insert(MessageDto message) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int re = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO messages(sender, recipient, type, url, text, timestamp) VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, String.valueOf(message.getSender()));
            ps.setString(2, String.valueOf(message.getRecipient()));
            ps.setString(3, message.getContent().getType());
            ps.setString(4, message.getContent().getUrl());
            ps.setString(5, message.getContent().getText());
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            ps.setString(6, String.valueOf(formatDate.format(new Date())));
            return ps;
        }, keyHolder);

        if (re == 1) {
            int messageId = keyHolder.getKey().intValue();  //저장한 messageId 가져오기
            return messageId;
        } else {
            throw new RuntimeException("insert error");
        }
    }


    public List<MessageDto> select(int recipient, int start, int limit) {
        List<MessageDto> list = this.jdbcTemplate.query("SELECT * FROM messages WHERE recipient = ? LIMIT ?, ?", new Integer[]{recipient, start, limit},
                (rs, rowNum) -> {
                    MessageDto dto = new MessageDto();
                    // 가지고온 데이터 매핑

                    //id
                    dto.setId(rs.getInt("id"));
                    //sender
                    dto.setSender(rs.getInt("sender"));
                    //recipient
                    dto.setRecipient(rs.getInt("recipient"));

                    //content
                    ContentDto contentDto = new ContentDto();
                    contentDto.setType(rs.getString("type"));
                    contentDto.setUrl(rs.getString("url"));
                    contentDto.setText(rs.getString("text"));
                    dto.setContent(contentDto);

                    //timestamp
                    dto.setTimestamp(rs.getString("timestamp"));

                    return dto;
                });

        return list;
    }
}
