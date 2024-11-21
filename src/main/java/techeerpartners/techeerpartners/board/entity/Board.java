package techeerpartners.techeerpartners.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private String title;

    @Column
    private String content;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDone = false;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

}
