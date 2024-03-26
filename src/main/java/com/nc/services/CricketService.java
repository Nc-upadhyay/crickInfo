package com.nc.services;


import com.nc.models.Match;
import com.nc.models.NewsStoryModel;
import com.nc.models.PlayersRanking;

import java.util.List;

public interface CricketService {

    void getMatchScores();

    List<List<String>> getCWC2023PointTable();

    List<Match> getAllMatches();

    public void getLettestNews();


    List<NewsStoryModel> getNews();

    List<PlayersRanking> getTestRank();

    List<PlayersRanking> getT20Rank();

    List<PlayersRanking> getODIRank();

    List<PlayersRanking> getBowlerTestRank();

    List<PlayersRanking> getBowlerT20Rank();

    List<PlayersRanking> getBowlerODIRank();
}
