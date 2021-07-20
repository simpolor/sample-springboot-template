package io.simpolor.api.repository.querydsl;

import com.querydsl.core.QueryResults;
import io.simpolor.api.repository.entity.Board;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    QueryResults<Board> search(String title, Pageable pageable);
}
