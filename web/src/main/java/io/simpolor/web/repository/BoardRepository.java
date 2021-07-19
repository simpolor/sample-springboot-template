package io.simpolor.web.repository;

import io.simpolor.web.repository.entity.Board;
import io.simpolor.web.repository.querydsl.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

}
