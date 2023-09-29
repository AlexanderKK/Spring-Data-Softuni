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
@Table(name = "player_statistics")
public class PlayerStatistics implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "scored_goals")
    private short scoredGoals;

    @Column(name = "assists")
    private short assists;

    @Column(name = "minutes_played")
    private short minutesPlayed;

}
