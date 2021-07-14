package io.simpolor.web.model;

import io.simpolor.web.repository.entity.Student;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

	private Long seq;

	private String name;

	private Integer grade;

	private Integer age;

	private List<String> hobbies;

	public Student toStudent(){

		Student student = new Student();
		student.setSeq(this.seq);
		student.setName(this.name);
		student.setAge(this.age);
		student.setHobbies(this.hobbies);

		return student;
	}

}
