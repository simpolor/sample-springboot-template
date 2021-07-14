package io.simpolor.api.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.simpolor.api.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import static io.simpolor.api.repository.entity.QStudent.student;

@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public QueryResults<Student> search(String name, Pageable pageable) {

        return queryFactory
                .selectFrom(student)
                .where(student.name.like("%"+name+"%"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    public static BooleanExpression likeOperation(StringPath column, String value) {
        if (StringUtils.isEmpty(value)) return null;

        return column.upper().like(value);
    }
}
