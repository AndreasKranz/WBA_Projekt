package main.wba_projekt.board.repository;

import main.wba_projekt.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Long, Board> {
}
