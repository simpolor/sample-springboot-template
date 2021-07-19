package io.simpolor.api.model.dto;

import io.simpolor.api.repository.entity.Student;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentSearchResponse {

    List<StudentResponse> students;

    long totalPage;

    long totalCount;

    public static StudentSearchResponse of(Page<Student> page){

        return StudentSearchResponse.builder()
                .students(StudentResponse.of(page.getContent()))
                .totalPage(page.getTotalPages())
                .totalCount(page.getTotalElements())
                .build();
    }
}
