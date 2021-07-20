package io.simpolor.api.repository;

import io.simpolor.api.repository.entity.Board;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @BeforeAll
    void init(){

        // Mockito.reset();
        boardRepository.deleteAll();

        Board board = new Board();
        board.setSeq(1L);
        board.setTitle("제목1");
        board.setContent("내용1");
        boardRepository.save(board);
    }

    @Test
    void testFindAll(){

        // given, when
        List<Board> expected = boardRepository.findAll();

        // then
        Assertions.assertThat(expected).isNotEmpty();
        Assertions.assertThat(expected).size().isEqualTo(1);
    }

    @Test
    void testFindById(){

        // given
        long seq = 1L;

        // when
        Optional<Board> expected = boardRepository.findById(seq);

        // then
        Assertions.assertThat(expected).isNotEmpty();
        Assertions.assertThat(expected).get().extracting(Board::getSeq).isEqualTo(1L);
        Assertions.assertThat(expected).get().extracting(Board::getTitle).isEqualTo("제목1");
    }

    @Test
    void testSave(){

        // given
        Board board = new Board();
        board.setSeq(2L);
        board.setTitle("제목2");
        board.setContent("내용2");

        // when
        Board expected = boardRepository.save(board);

        // then
        Assertions.assertThat(expected).isNotNull();
        Assertions.assertThat(expected).extracting(Board::getSeq).isEqualTo(2L);
        Assertions.assertThat(expected).extracting(Board::getTitle).isEqualTo("내용2");
    }

    @Test
    void testDelete(){

        // given
        long seq = 1;

        // when
        boardRepository.deleteById(seq);

        // then
        Optional<Board> expected = boardRepository.findById(seq);
        Assertions.assertThat(expected).isEmpty();
    }

}
