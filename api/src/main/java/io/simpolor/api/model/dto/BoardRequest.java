package io.simpolor.api.model.dto;

import io.simpolor.api.model.enums.BoardType;
import io.simpolor.api.repository.entity.Board;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardRequest {

    private Long seq;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private BoardType type;

    public Board toObject(){

        Board board = new Board();
        board.setSeq(this.seq);
        board.setTitle(this.title);
        board.setContent(this.content);
        board.setType(this.type);

        return board;
    }

}
