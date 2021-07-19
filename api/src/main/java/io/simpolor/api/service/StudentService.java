package io.simpolor.api.service;

import com.querydsl.core.QueryResults;
import io.simpolor.api.exception.NotFoundException;
import io.simpolor.api.repository.StudentRepository;
import io.simpolor.api.repository.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public StudentService(@Autowired StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Long getTotalCount() {
        return studentRepository.count();
    }

    public Page<Student> search(String name, Pageable pageable){

        QueryResults<Student> queryResults = studentRepository.search(name, pageable);

        return new PageImpl<>(
                queryResults.getResults(),
                pageable,
                queryResults.getTotal());
    }

    public Page<Student> search2(Pageable pageable){

        return studentRepository.findAll(pageable);
    }

    public List<Student> getAll(){

        return studentRepository.findAll();
    }

    public Student get(long seq){

        Optional<Student> student = studentRepository.findById(seq);

        if(!student.isPresent()){
            throw new NotFoundException("Notfound seq : {}", seq);
        }

        return student.get();
    }

    public void create(Student student){
        studentRepository.save(student);
    }

    public void update(Student student){

        Optional<Student> result = studentRepository.findById(student.getSeq());
        if(!result.isPresent()){
            throw new NotFoundException("Notfound seq : {}", student.getSeq());
        }
        studentRepository.save(student);
    }

    public void delete(Long seq){

        studentRepository.deleteById(seq);
    }
}
