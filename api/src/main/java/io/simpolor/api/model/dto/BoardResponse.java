package io.simpolor.api.model.dto;

import io.simpolor.api.model.enums.BoardType;
import io.simpolor.api.repository.entity.Board;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardResponse {

    private Long seq;

    private String title;

    private String content;

    private BoardType type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static BoardResponse of(Board board){

        BoardResponse response = new BoardResponse();
        response.setSeq(board.getSeq());
        response.setTitle(board.getTitle());
        response.setContent(board.getContent());
        response.setType(board.getType());
        response.setCreatedAt(board.getCreatedAt());
        response.setUpdatedAt(board.getUpdatedAt());

        return response;
    }

    public static List<BoardResponse> of(List<Board> boards){

        return boards.stream()
                .map(BoardResponse::of)
                .collect(Collectors.toList());
    }
}
