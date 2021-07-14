package io.simpolor.api.controller;

import io.simpolor.api.advice.WithServiceResponse;
import io.simpolor.api.model.StudentRequest;
import io.simpolor.api.model.StudentResponse;
import io.simpolor.api.model.StudentSearchRequest;
import io.simpolor.api.model.StudentSearchResponse;
import io.simpolor.api.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@WithServiceResponse
@RequestMapping("/students")
public class StudentController {

    StudentService studentService;
    public StudentController(@Autowired StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/search")
    public StudentSearchResponse search(
            StudentSearchRequest studentSearchRequest,
            @SortDefault(sort = "seq", direction = Sort.Direction.DESC) Pageable pageable){

        System.out.println("studentSearchRequest : "+studentSearchRequest.toString());

        return StudentSearchResponse.of(studentService.search(studentSearchRequest.getName(), pageable));
    }

    @GetMapping(value="/total-count")
    public long totalCount() {

        return studentService.getTotalCount();
    }

    @GetMapping
    public List<StudentResponse> getAll(){

        return StudentResponse.of(studentService.getAll());
    }

    @GetMapping("/{seq}")
    public StudentResponse get(@PathVariable long seq){

        return StudentResponse.of(studentService.get(seq));
    }

    @PostMapping
    public void register(@RequestBody StudentRequest studentRequest){

        studentService.create(studentRequest.toStudent());
    }

    @PutMapping("/{seq}")
    public void modify(@PathVariable long seq,
                       @RequestBody StudentRequest studentRequest){

        studentRequest.setSeq(seq);
        studentService.update(studentRequest.toStudent());
    }

    @DeleteMapping("/{seq}")
    public void delete(@PathVariable long seq){

        studentService.delete(seq);
    }

}
