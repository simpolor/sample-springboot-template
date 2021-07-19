package io.simpolor.api.model.dto;

import io.simpolor.api.repository.entity.Student;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {

    Long seq;

    String name;

    Integer age;

    String hobby;

    public Student toStudent(){

        Student student = new Student();
        student.setSeq(this.seq);
        student.setName(this.name);
        student.setAge(this.age);
        student.setHobby(this.hobby);

        return student;
    }

}
