package io.simpolor.web.service;

import io.simpolor.web.repository.StudentRepository;
import io.simpolor.web.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public long getTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student get(Long seq) {
        // return studentRepository.findById(seq).orElse(new Student());
        return studentRepository.selectStudent(seq);
    }

    public Student create(Student student) {

        return studentRepository.save(student);
    }

    public Student update(Student student) {

        if(studentRepository.findById(student.getSeq()).isPresent()){
            return studentRepository.save(student);
        }

        return new Student();
    }

    public Long delete(Long seq) {

        studentRepository.deleteById(seq);
        return seq;
    }

}
