package com.nc.service.impl;

import com.nc.entities.Match;
import com.nc.repository.MatchRepository;
import com.nc.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;

    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public List<Match> getLiveMatches() {
        return null;
    }

    @Override
    public List<Map<String, String>> getPointTable() {
        return null;
    }
}
