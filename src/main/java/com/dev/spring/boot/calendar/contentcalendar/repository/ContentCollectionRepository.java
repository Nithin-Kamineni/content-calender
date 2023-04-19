package com.dev.spring.boot.calendar.contentcalendar.repository;

import com.dev.spring.boot.calendar.contentcalendar.model.Content;
import com.dev.spring.boot.calendar.contentcalendar.model.Status;
import com.dev.spring.boot.calendar.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {

    }

    public List<Content>  findAll() {
        return contentList;
    }

    //search list in a stream and filtering elements with their id to the parameter id
    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content){
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }

    @PostConstruct
    private void init(){
        Content content = new Content(1,
                "My first blog post",
                "My first blog post" ,
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");

        contentList.add(content);
    }


}
