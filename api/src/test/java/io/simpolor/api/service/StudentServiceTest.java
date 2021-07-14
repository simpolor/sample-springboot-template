package io.simpolor.api.service;

import io.simpolor.api.repository.StudentRepository;
import io.simpolor.api.repository.entity.Student;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentServiceTest {

    StudentRepository studentRepository;
    StudentService studentService;

    @BeforeEach
    public void init(){
        studentRepository = Mockito.mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
    }

    @Test
    public void testGetAll(){

        // given
        Student student = new Student(1L, "하니", 18, "달리기");
        List<Student> students = Arrays.asList(student);

        Mockito.when(studentRepository.findAll()).thenReturn(students);

        // when
        List<Student> expected = studentService.getAll();


        // then
        Assertions.assertThat(expected).isNotEmpty();
        Assertions.assertThat(expected).size().isEqualTo(1);
    }

    @Test
    public void testGet(){

        // given
        long seq = 1L;
        Student student = new Student(seq, "하니", 18, "달리기");

        Mockito.when(studentRepository.findById(seq)).thenReturn(Optional.of(student));

        // when
        Student expected = studentService.get(seq);

        // then
        Assertions.assertThat(expected).isNotNull();
        Assertions.assertThat(expected).extracting(Student::getSeq).isEqualTo(seq);
        Assertions.assertThat(expected).extracting(Student::getName).isEqualTo("하니");
    }

    @Test
    public void testSave(){

        // given
        long seq = 1L;
        Student student = new Student(seq, "하니", 18, "달리기");

        Mockito.when(studentRepository.save(student)).thenReturn(student);

        // when
        studentService.create(student);

        // then
        Mockito.verify(studentRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void testUpdate(){

        // given
        long seq = 1L;
        Student student = new Student(seq, "하니", 18, "달리기");

        Mockito.when(studentRepository.findById(seq)).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.save(student)).thenReturn(student);

        // when
        studentService.update(student);

        // then
        Mockito.verify(studentRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(studentRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void testDelete(){

        // given
        long seq = 1L;


        // when
        studentService.delete(seq);


        // then
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }

}
