package io.simpolor.web.repository.querydsl;

import com.querydsl.core.QueryResults;
import io.simpolor.web.repository.entity.Board;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    QueryResults<Board> search(String title, Pageable pageable);
}
