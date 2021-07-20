package io.simpolor.api.model.dto;

import io.simpolor.api.repository.entity.Board;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardSearchResponse {

    List<BoardResponse> students;

    long totalPage;

    long totalCount;

    public static BoardSearchResponse of(Page<Board> page){

        return BoardSearchResponse.builder()
                .students(BoardResponse.of(page.getContent()))
                .totalPage(page.getTotalPages())
                .totalCount(page.getTotalElements())
                .build();
    }
}
