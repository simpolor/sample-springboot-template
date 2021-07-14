package io.simpolor.api.repository.querydsl;

import com.querydsl.core.QueryResults;
import io.simpolor.api.repository.entity.Student;
import org.springframework.data.domain.Pageable;

public interface StudentRepositoryCustom {

    QueryResults<Student> search(String name, Pageable pageable);
}
