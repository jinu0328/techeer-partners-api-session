package techeerpartners.techeerpartners.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixBoardRequestDto {
    private String content;
    private Boolean isDone;
}
