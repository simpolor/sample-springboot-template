package io.simpolor.api.repository;

import io.simpolor.api.repository.entity.Board;
import io.simpolor.api.repository.querydsl.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {


}
