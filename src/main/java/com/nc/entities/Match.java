package com.nc.entities;

import com.nc.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "crick_matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;
    private String teamHeading;
    private String matchValueNumber;
    private String battingTeamName;
    private String battingTeamScore;
    private String bowlTeam;
    private String bowlTeamScore;
    private String liveText;
    private String matchLink;
    private String textCompleted;
    @Enumerated
    private MatchStatus status;
    private Date date = new Date();


    //set the metch status according to textCompleted
    public void setMatchStatus() {
        status = textCompleted.isEmpty() ? MatchStatus.LIVE : MatchStatus.COMPLETED;
    }
}
