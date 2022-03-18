package main.wba_projekt.board.repository;

import main.wba_projekt.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    /**
     * Get the boad entity from DB with the
     *
     * @param name
     * @return board entity
     */
    @Query("select b FROM Board b WHERE b.title = ?1")
    Board findBoard(String name);
}
