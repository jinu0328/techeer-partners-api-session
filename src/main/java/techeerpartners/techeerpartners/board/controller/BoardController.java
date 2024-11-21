package techeerpartners.techeerpartners.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techeerpartners.techeerpartners.board.dto.response.BoardResponse;
import techeerpartners.techeerpartners.board.dto.request.CreateBoardRequestDto;
import techeerpartners.techeerpartners.board.dto.request.FixBoardRequestDto;
import techeerpartners.techeerpartners.board.service.BoardService;
import techeerpartners.techeerpartners.common.CustomApiResponse;

import java.util.List;

@Tag(name = "Board", description = "게시판 API")
@RequestMapping("/board")
@RestController
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @Operation(summary = "할 일 추가", description = "새로운 할 일을 추가합니다.")
    @PostMapping("/tasks")
    public ResponseEntity<CustomApiResponse> createTask(@RequestBody CreateBoardRequestDto createBoardRequestDto) {
        boardService.createBoard(createBoardRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CustomApiResponse.onSuccess("일정 추가됌"));
    }
    @Operation(summary = "할 일 수정", description = "할 일과,완료여부가 수정되었습니다.")
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<CustomApiResponse> updateTask(@PathVariable Long id, @RequestBody FixBoardRequestDto fixBoardRequestDto) {
        boardService.updateBoard(id,fixBoardRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CustomApiResponse.onSuccess("할 일이 수정됌."));
    }
    @Operation(summary = "할 일 삭제", description = "할 일이 삭제되어있습니다.")
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<CustomApiResponse> deleteTask(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.status(HttpStatus.OK).body(CustomApiResponse.onSuccess("할 일이 삭제됌."));
    }
    @Operation(summary = "완료된 일과 미완료된 일들을 파라미터로 조회", description = "true인 일과 false인 일들을 파라미터로 조회합니다.")
    @GetMapping("/tasks")
    public ResponseEntity<CustomApiResponse<List<BoardResponse>>> getTasks(@RequestParam boolean isDone) {
        List<BoardResponse> tasks = boardService.getTasksByStatus(isDone);
        return ResponseEntity.status(HttpStatus.OK).body(CustomApiResponse.onSuccess(tasks));
    }
    @Operation(summary = "모든 일 조회", description = "모든 일들을 조회합니다.")
    @GetMapping("/all/tasks")
    public ResponseEntity<CustomApiResponse<List<BoardResponse>>> getAllTasks() {
        List<BoardResponse> checkBoardResult = boardService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(CustomApiResponse.onSuccess(checkBoardResult));
    }
}
