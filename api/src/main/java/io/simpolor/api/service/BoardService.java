package io.simpolor.api.service;

import com.querydsl.core.QueryResults;
import io.simpolor.api.exception.NotFoundException;
import io.simpolor.api.repository.BoardRepository;
import io.simpolor.api.repository.entity.Board;
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

    public Page<Board> search(String name, Pageable pageable){

        QueryResults<Board> queryResults = boardRepository.search(name, pageable);

        return new PageImpl<>(
                queryResults.getResults(),
                pageable,
                queryResults.getTotal());
    }

    public Long getTotalCount() {
        return boardRepository.count();
    }

    public List<Board> getAll(){

        return boardRepository.findAll();
    }

    public Board get(long seq){

        Optional<Board> optionalBoard = boardRepository.findById(seq);
        if(!optionalBoard.isPresent()){
            throw new NotFoundException("Notfound seq : {}", seq);
        }

        return optionalBoard.get();
    }

    public void create(Board board){
        boardRepository.save(board);
    }

    public void update(Board board){

        Optional<Board> optionalBoard = boardRepository.findById(board.getSeq());
        if(!optionalBoard.isPresent()){
            throw new NotFoundException("Notfound seq : {}", board.getSeq());
        }

        Board object = optionalBoard.get();
        object.setTitle(board.getTitle());
        object.setContent(board.getContent());

        boardRepository.save(object);
    }

    public void delete(long seq){

        boardRepository.deleteById(seq);
    }
}
