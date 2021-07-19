package io.simpolor.web.service;

import com.querydsl.core.QueryResults;
import io.simpolor.web.exception.ApplicationException;
import io.simpolor.web.exception.ExceptionType;
import io.simpolor.web.repository.BoardRepository;
import io.simpolor.web.repository.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> search(String title, Pageable pageable){

        QueryResults<Board> queryResults = boardRepository.search(title, pageable);

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    public long getTotalCount() {
        return boardRepository.count();
    }

    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    public Board get(Long seq) {

        Optional<Board> optionalBoard = boardRepository.findById(seq);
        if(!optionalBoard.isPresent()){
            throw new ApplicationException(ExceptionType.NOT_FOUND, "NotFound Entity : {}", seq);
        }

        return optionalBoard.get();
    }

    public Board create(Board board) {

        return boardRepository.save(board);
    }

    public Board update(Board board) {

        Optional<Board> optionalBoard = boardRepository.findById(board.getSeq());
        if(!optionalBoard.isPresent()){
            throw new ApplicationException(ExceptionType.NOT_FOUND, "NotFound Entity : {}", board.getSeq());
        }

        Board object = optionalBoard.get();
        object.setTitle(board.getTitle());
        object.setContent(board.getContent());

        boardRepository.save(object);

        return board;
    }

    public Long delete(Long seq) {

        boardRepository.deleteById(seq);
        return seq;
    }

}
