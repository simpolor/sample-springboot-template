package io.simpolor.api.controller;

import io.simpolor.api.advice.WithServiceResponse;
import io.simpolor.api.model.dto.BoardRequest;
import io.simpolor.api.model.dto.BoardResponse;
import io.simpolor.api.model.dto.BoardSearch;
import io.simpolor.api.model.dto.BoardSearchResponse;
import io.simpolor.api.repository.entity.Board;
import io.simpolor.api.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/boards")
@WithServiceResponse
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/search")
    public BoardSearchResponse search(
            BoardSearch search,
            @SortDefault(sort = "seq", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Board> boardPage = boardService.search(search.getTitle(), pageable);
        return BoardSearchResponse.of(boardPage);
    }

    @GetMapping(value="/total-count")
    public long totalCount() {

        return boardService.getTotalCount();
    }

    @GetMapping
    public List<BoardResponse> getAll(){

        return BoardResponse.of(boardService.getAll());
    }

    @GetMapping("/{seq}")
    public BoardResponse get(@PathVariable long seq){

        return BoardResponse.of(boardService.get(seq));
    }

    @PostMapping
    public void register(@RequestBody BoardRequest boardRequest){

        boardService.create(boardRequest.toObject());
    }

    @PutMapping("/{seq}")
    public void modify(@PathVariable Long seq,
                       @RequestBody BoardRequest boardRequest){

        boardRequest.setSeq(seq);
        boardService.update(boardRequest.toObject());
    }

    @DeleteMapping("/{seq}")
    public void delete(@PathVariable Long seq){

        boardService.delete(seq);
    }

}
