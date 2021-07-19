package io.simpolor.web.model.dto;

import io.simpolor.web.model.enums.BoardType;
import io.simpolor.web.repository.entity.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
