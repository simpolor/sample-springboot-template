package io.simpolor.api.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.simpolor.api.repository.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import static io.simpolor.api.repository.entity.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public QueryResults<Board> search(String title, Pageable pageable) {

        return queryFactory
                .selectFrom(board)
                .where(
                    likeOperation(board.title, title)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    public static BooleanExpression likeOperation(StringPath column, String value) {
        if (StringUtils.isEmpty(value)) return null;

        return column.upper().like("%"+value+"%");
    }
}
