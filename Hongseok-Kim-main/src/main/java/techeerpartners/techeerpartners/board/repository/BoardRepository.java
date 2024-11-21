package techeerpartners.techeerpartners.board.repository;

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import techeerpartners.techeerpartners.board.entity.Board;

import java.util.List;
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByIsDone(boolean isDone);
}