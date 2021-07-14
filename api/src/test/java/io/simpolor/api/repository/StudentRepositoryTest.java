package io.simpolor.api.repository;

import io.simpolor.api.repository.entity.Student;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeAll
    void init(){

        // Mockito.reset();
        studentRepository.deleteAll();

        Student student = new Student();
        student.setSeq(1L);
        student.setName("하니");
        student.setAge(18);
        student.setHobby("달리기");
        studentRepository.save(student);
    }

    @Test
    void testFindAll(){

        // given, when
        List<Student> expected = studentRepository.findAll();

        // then
        Assertions.assertThat(expected).isNotEmpty();
        Assertions.assertThat(expected).size().isEqualTo(1);
    }

    @Test
    void testFindById(){

        // given
        long seq = 1L;

        // when
        Optional<Student> expected = studentRepository.findById(seq);

        // then
        Assertions.assertThat(expected).isNotEmpty();
        Assertions.assertThat(expected).get().extracting(Student::getSeq).isEqualTo(1L);
        Assertions.assertThat(expected).get().extracting(Student::getName).isEqualTo("하니");
    }

    @Test
    void testSave(){

        // given
        Student student = new Student();
        student.setName("김철수");
        student.setAge(19);
        student.setHobby("고자질하기");

        // when
        Student expected = studentRepository.save(student);

        // then
        Assertions.assertThat(expected).isNotNull();
        Assertions.assertThat(expected).extracting(Student::getSeq).isEqualTo(2L);
        Assertions.assertThat(expected).extracting(Student::getName).isEqualTo("김철수");
    }

    @Test
    void testDelete(){

        // given
        long seq = 1;

        // when
        studentRepository.deleteById(seq);

        // then
        Optional<Student> expected = studentRepository.findById(seq);
        Assertions.assertThat(expected).isEmpty();
    }

}
