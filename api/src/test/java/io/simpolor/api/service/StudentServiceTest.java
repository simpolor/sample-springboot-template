package io.simpolor.api.service;

import io.simpolor.api.model.enums.BoardType;
import io.simpolor.api.repository.BoardRepository;
import io.simpolor.api.repository.entity.Board;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentServiceTest {

    BoardRepository boardRepository;
    BoardService boardService;

    @BeforeEach
    public void init(){
        boardRepository = Mockito.mock(BoardRepository.class);
        boardService = new BoardService(boardRepository);
    }

    @Test
    public void testGetAll(){

        // given
        Board board = new Board(1L, "제목1", "내용1", BoardType.FREE, LocalDateTime.now(), LocalDateTime.now());
        List<Board> boards = Arrays.asList(board);

        Mockito.when(boardRepository.findAll()).thenReturn(boards);

        // when
        List<Board> expected = boardService.getAll();


        // then
        Assertions.assertThat(expected).isNotEmpty();
        Assertions.assertThat(expected).size().isEqualTo(1);
    }

    @Test
    public void testGet(){

        // given
        long seq = 1L;
        Board board = new Board(seq, "제목1", "내용1", BoardType.FREE, LocalDateTime.now(), LocalDateTime.now());
        Optional<Board> optionalBoard = Optional.of(board);

        Mockito.when(boardRepository.findById(seq)).thenReturn(optionalBoard);

        // when
        Board expected = boardService.get(seq);

        // then
        Assertions.assertThat(expected).isNotNull();
        Assertions.assertThat(expected).extracting(Board::getSeq).isEqualTo(seq);
        Assertions.assertThat(expected).extracting(Board::getTitle).isEqualTo("제목1");
    }

    @Test
    public void testSave(){

        // given
        long seq = 1L;
        Board board = new Board(seq, "제목1", "내용1", BoardType.FREE, LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(boardRepository.save(board)).thenReturn(board);

        // when
        boardService.create(board);

        // then
        Mockito.verify(boardRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void testUpdate(){

        // given
        long seq = 1L;
        Board board = new Board(seq, "제목1", "내용1", BoardType.FREE, LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(boardRepository.findById(seq)).thenReturn(Optional.of(board));
        Mockito.when(boardRepository.save(board)).thenReturn(board);

        // when
        boardService.update(board);

        // then
        Mockito.verify(boardRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(boardRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void testDelete(){

        // given
        long seq = 1L;


        // when
        boardService.delete(seq);


        // then
        Mockito.verify(boardRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }

}
