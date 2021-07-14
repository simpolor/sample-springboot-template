package io.simpolor.web.repository;

import io.simpolor.web.repository.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // todo : custom query
    @Query("SELECT s FROM student s WHERE s.seq = ?1")
    Student selectStudent(long seq);
}
