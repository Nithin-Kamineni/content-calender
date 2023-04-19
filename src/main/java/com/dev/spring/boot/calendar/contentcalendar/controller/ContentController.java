package com.dev.spring.boot.calendar.contentcalendar.controller;

import com.dev.spring.boot.calendar.contentcalendar.model.Content;
import com.dev.spring.boot.calendar.contentcalendar.model.Status;
import com.dev.spring.boot.calendar.contentcalendar.model.Type;
import com.dev.spring.boot.calendar.contentcalendar.repository.ContentCollectionRepository;
import com.dev.spring.boot.calendar.contentcalendar.repository.ContentJdbcTemplateRepository;
import com.dev.spring.boot.calendar.contentcalendar.repository.ContentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin()  //CORS handling
public class ContentController {

//    private ContentCollectionRepository repository;
    private ContentJdbcTemplateRepository repository;
//    private ContentRepository repository;
    @Autowired
    public ContentController(ContentJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    //listing all the tasks
    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable int id) {
        Content content = repository.findById(id);
        if(content.id() != null){
            return content;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content content,@PathVariable Integer id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.updateById(content, id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> filterByKeyword(@PathVariable String keyword){
        return repository.findByKeyword(keyword);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.deleteById(id);

    }
}
