package io.simpolor.api.repository;

import io.simpolor.api.repository.entity.Student;
import io.simpolor.api.repository.querydsl.StudentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {


}
