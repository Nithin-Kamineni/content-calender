package com.dev.spring.boot.calendar.contentcalendar.repository;

import com.dev.spring.boot.calendar.contentcalendar.model.Content;
import com.dev.spring.boot.calendar.contentcalendar.model.Status;
import com.dev.spring.boot.calendar.contentcalendar.model.Type;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Repository
public class ContentJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    @Value("${db.TableContent: Content1}")
    private String tableName;

    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
//        this.tableName = "Content";
    }

    private static Content mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        return new Content(resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
//                (Status) resultSet.getObject("status"),
//                (Type) resultSet.getObject("content_type"),
//                (LocalDateTime) resultSet.getObject("date_created"),
//                (LocalDateTime) resultSet.getObject("date_updated"),

                Status.valueOf(resultSet.getString("status")),
                Type.valueOf(resultSet.getString("content_type")),
                resultSet.getObject("date_created", LocalDateTime.class),
                resultSet.getObject("date_updated", LocalDateTime.class),
                resultSet.getString("url")
        );

    }

    public List<Content> findAll() {
        String sqlQuery = String.format("SELECT * from %s",this.tableName);
//        List<Content> contentList = jdbcTemplate.queryForList(sqlQuery, );
        List<Content> contentList = jdbcTemplate.query(sqlQuery, ContentJdbcTemplateRepository::mapRow);
        return contentList;
    }

    //search list in a stream and filtering elements with their id to the parameter id
    public Content findById(Integer id){
        String sqlQuery = String.format("SELECT * FROM %s WHERE id=?",this.tableName);
        Content content = jdbcTemplate.queryForObject(sqlQuery, new Object[]{id},ContentJdbcTemplateRepository::mapRow);
        return content;
    }

    public void deleteById(Integer id){
        String sqlQuery = String.format("DELETE FROM %s WHERE id=?",this.tableName);
        jdbcTemplate.update(sqlQuery, id);
    }

    public void save(Content content){
        String sqlQuery = String.format("INSERT INTO %s (title, description, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)",this.tableName);
        jdbcTemplate.update(sqlQuery, content.title(), content.desc(), content.status().toString(), content.contentType().toString(), content.url());
    }

    public void saveAll(List<Content> contentList){
        String sqlQuery = String.format("INSERT INTO %s (title, description, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)",this.tableName);
        for (Content content : contentList) {
            jdbcTemplate.update(sqlQuery, content.title(), content.desc(), content.status().toString(), content.contentType().toString(), content.url());
        }
    }

    public void updateById(Content content, Integer id){
        deleteById(id);
        String sqlQuery = String.format("INSERT INTO %s (title, description, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)",this.tableName);


        jdbcTemplate.update(sqlQuery, content.title(), content.desc(), content.status().toString(), content.contentType().toString(), content.url());
    }

    public boolean existsById(Integer id) {
        String sqlQuery = String.format("SELECT count(*) FROM %s WHERE id = ?",this.tableName);
        int count = jdbcTemplate.queryForObject(sqlQuery, new Object[]{id},Integer.class);
        return count==1;
    }

    public List<Content> findByKeyword(String keyword) {
        String sqlQuery = String.format("SELECT * FROM %s WHERE description ~* ?",this.tableName);
        System.out.println("=============================");
        System.out.println(sqlQuery);
        List<Content> keywordContentList = jdbcTemplate.query(sqlQuery, new Object[]{keyword}, ContentJdbcTemplateRepository:: mapRow);
        return keywordContentList;
    }

}
