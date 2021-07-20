package io.simpolor.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.simpolor.api.model.dto.BoardRequest;
import io.simpolor.api.model.enums.BoardType;
import io.simpolor.api.repository.BoardRepository;
import io.simpolor.api.repository.entity.Board;
import io.simpolor.api.service.BoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = {BoardController.class, BoardService.class, BoardRepository.class})
public class BoardControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardRepository boardRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testList() throws Exception{

        // given
        Board board = new Board(1L, "제목1", "내용1", BoardType.FREE, LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(boardRepository.findAll()).thenReturn(Arrays.asList(board));


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.get("/boards")
        )
        .andDo(MockMvcResultHandlers.print())


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value[0].title").value(is("제목1")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value[0].content").value(is("내용1")));
    }

    @Test
    public void testDetail() throws Exception{

        // given
        long seq = 1L;
        Board board = new Board(1L, "제목1", "내용1", BoardType.FREE, LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(boardRepository.findById(seq)).thenReturn(Optional.of(board));


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.get("/boards/1")
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value.title").value(is("제목1")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value.content").value(is("내용1")));
    }

    @Test
    public void testRegister() throws Exception{

        // given
        BoardRequest boardRequest = new BoardRequest(1L, "제목1", "내용1", BoardType.FREE);
        String request = objectMapper.writeValueAsString(boardRequest);


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.post("/boards")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")));
        Mockito.verify(boardRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void testModify() throws Exception{

        // given
        long seq = 1L;
        Board board = new Board(seq, "제목1", "내용1", BoardType.FREE, LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(boardRepository.findById(seq)).thenReturn(Optional.of(board));

        BoardRequest boardRequest = new BoardRequest(seq, "제목1", "내용1", BoardType.FREE);
        String request = objectMapper.writeValueAsString(boardRequest);


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.put("/boards/{seq}", seq)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")));
        Mockito.verify(boardRepository, Mockito.times(1)).findById(ArgumentMatchers.any());
        Mockito.verify(boardRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void testDelete() throws Exception{

        // given
        long seq = 1L;


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/boards/{seq}", seq)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")));
        Mockito.verify(boardRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }
}
