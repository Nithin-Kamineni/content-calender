package com.dev.spring.boot.calendar.contentcalendar.repository;

import com.dev.spring.boot.calendar.contentcalendar.model.Content;
import com.dev.spring.boot.calendar.contentcalendar.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//extending repository we can add out own methods in here to manage the database "Repository"
public interface ContentRepository extends ListCrudRepository<Content, Integer> {

    List<Content> findAllByContentType(String type);

    @Query("""
    SELECT * FROM Content WHERE status = :status
    """)
    List<Content> listByStatus(@Param("status") Status status);
}
