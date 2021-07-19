package io.simpolor.api.model.dto;

import io.simpolor.api.repository.entity.Student;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {

    long seq;

    String name;

    Integer age;

    String hobby;

    public static StudentResponse of(Student student){

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setSeq(student.getSeq());
        studentResponse.setName(student.getName());
        studentResponse.setAge(student.getAge());
        studentResponse.setHobby(student.getHobby());

        return studentResponse;
    }

    public static List<StudentResponse> of(List<Student> students){

        return students.stream()
                .map(s -> StudentResponse.of(s))
                .collect(Collectors.toList());
    }
}
