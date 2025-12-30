package com.example.subjectmanagement.controller;

import com.example.subjectmanagement.model.Subject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @PersistenceContext
    private EntityManager entityManager;

    // POST /subjects
    @PostMapping
    public Subject addSubject(@RequestBody Subject subject) {
        entityManager.persist(subject);
        return subject;
    }

    // GET /subjects
    @GetMapping
    public List<Subject> getSubjects() {
        return entityManager
                .createQuery("from Subject", Subject.class)
                .getResultList();
    }

    // PUT /subjects/{id}
    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Long id,
                                 @RequestBody Subject updatedSubject) {

        Subject subject = entityManager.find(Subject.class, id);
        subject.setSubjectCode(updatedSubject.getSubjectCode());
        subject.setSubjectName(updatedSubject.getSubjectName());
        return entityManager.merge(subject);
    }

    // DELETE /subjects/{id}
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Long id) {
        Subject subject = entityManager.find(Subject.class, id);
        entityManager.remove(subject);
        return "Subject Deleted";
    }
}
