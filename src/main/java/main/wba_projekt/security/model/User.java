package main.wba_projekt.security.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import main.wba_projekt.board.model.Board;
import main.wba_projekt.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Setter
@Getter
@ToString
public class User extends BaseEntity<Long> {

    private String name;

    private Boolean Admin;

    //Relation zu Board, Task und Kommentar machen



}
