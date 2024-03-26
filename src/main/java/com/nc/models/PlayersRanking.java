package com.nc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayersRanking {
    String position;
    String name;
    String country;
    String rank;
    boolean up;
    boolean down;
    String step;
    String profileImageUrl;
}
