package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bet_games")
public class BetGame implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @OneToOne
    @JoinColumn(name = "bet_id")
    private Bet bet;

    @OneToOne
    @JoinColumn
    private ResultPrediction resultPrediction;

}
