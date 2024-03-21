package com.nc.service;

import com.nc.entities.Match;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface MatchService {
    List<Match> getAllMatches();
    List<Match> getLiveMatches();

    List<Map<String,String>> getPointTable();
}
