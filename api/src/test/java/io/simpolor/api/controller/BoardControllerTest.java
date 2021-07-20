package io.simpolor.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.simpolor.api.model.dto.BoardRequest;
import io.simpolor.api.model.enums.BoardType;
import io.simpolor.api.repository.entity.Board;
import io.simpolor.api.service.BoardService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = {BoardController.class})
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSearch() throws Exception{

        // given
        Board board = new Board(1L, "제목1", "내용1", BoardType.FREE, LocalDateTime.now(), LocalDateTime.now());
        Pageable pageable = PageRequest.of(0, 10);
        Page<Board> page = new PageImpl<>(Arrays.asList(board), pageable, 1L);
        Mockito.when(boardService.search(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);
        // PageableExecutionUtils.getPage(Arrays.asList(student), PageRequest.of(1, 10), () -> 1L);


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.get("/boards/search")
                .queryParam("page", "1")
                .queryParam("size", "10")
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value.boards[0].seq").value(is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value.boards[0].title").value(is("제목1")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value.total_page").value(is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value.total_count").value(is(1)));
    }

    @Test
    public void testList() throws Exception{

        // given
        Board board = new Board(1L, "제목1", "내용1", BoardType.FREE, LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(boardService.getAll()).thenReturn(Arrays.asList(board));


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.get("/students")
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value[0].seq").value(is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value[0].title").value(is("제목1")));
    }

    @Test
    public void testDetail() throws Exception{

        // given
        long seq = 1L;
        Board board = new Board(seq, "제목1", "내용1", BoardType.FREE, LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(boardService.get(seq)).thenReturn(board);


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.get("/boards/1")
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value.seq").value(is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value.title").value(is("제목1")));
    }

    @Test
    public void testRegister() throws Exception{

        // given
        BoardRequest boardRequest = new BoardRequest(1L, "제목1", "내용1", BoardType.FREE);
        String request = objectMapper.writeValueAsString(boardRequest);


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.post("/students")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(request)
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")));
        Mockito.verify(boardService, Mockito.times(1)).create(ArgumentMatchers.any());
    }

    @Test
    public void testModify() throws Exception{

        // given
        long seq = 1L;
        BoardRequest boardRequest = new BoardRequest(1L, "제목1", "내용1", BoardType.FREE);
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
        Mockito.verify(boardService, Mockito.times(1)).update(ArgumentMatchers.any());
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
        Mockito.verify(boardService, Mockito.times(1)).delete(ArgumentMatchers.anyLong());
    }
}
