package main.wba_projekt.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import main.wba_projekt.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@ToString
public class Board extends BaseEntity<Long> {



    private String title;
}
