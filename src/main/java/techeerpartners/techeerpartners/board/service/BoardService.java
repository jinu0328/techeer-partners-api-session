package techeerpartners.techeerpartners.board.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import techeerpartners.techeerpartners.board.dto.response.BoardResponse;
import techeerpartners.techeerpartners.board.dto.request.CreateBoardRequestDto;
import techeerpartners.techeerpartners.board.dto.request.FixBoardRequestDto;
import techeerpartners.techeerpartners.board.entity.Board;
import techeerpartners.techeerpartners.board.repository.BoardRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    @Transactional
    public void createBoard(CreateBoardRequestDto createBoardRequestDto) {
        Board board = Board.builder()
                .title(createBoardRequestDto.getTitle())
                .content(createBoardRequestDto.getContent())
                .build();
        boardRepository.save(board);
    }
    @Transactional
    public void updateBoard(Long id, FixBoardRequestDto fixBoardRequestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID를 찾을 수없음"));
        Board updatedBoard = Board.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(fixBoardRequestDto.getContent())
                .isDone(fixBoardRequestDto.getIsDone())
                .createdAt(board.getCreatedAt())
                .build();
        boardRepository.save(updatedBoard);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board =boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID를 찾을 수없음"));
        boardRepository.delete(board);
    }
    public List<BoardResponse> getTasksByStatus(boolean isDone) {
        return boardRepository.findByIsDone(isDone).stream()
                .map(board -> BoardResponse.builder()
                        .id(board.getBoardId())
                        .title(board.getTitle())
                        .isDone(board.getIsDone())
                        .build())
                .collect(Collectors.toList());
    }
    public List<BoardResponse> getAllTasks() {
        return boardRepository.findAll().stream()
                .map(board -> BoardResponse.builder()
                        .id(board.getBoardId())
                        .title(board.getTitle())
                        .isDone(board.getIsDone())
                        .build())
                .collect(Collectors.toList());
    }

}
