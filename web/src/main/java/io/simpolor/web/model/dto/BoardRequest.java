package io.simpolor.web.model.dto;

import io.simpolor.web.model.enums.BoardType;
import io.simpolor.web.repository.entity.Board;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
