package io.simpolor.web.repository.entity;

import io.simpolor.web.repository.converter.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	@Column(name = "name", nullable = false)
	private String name;

	private Integer grade;

	private Integer age;

	@Convert(converter = StringListConverter.class)
	private List<String> hobbies;

}
