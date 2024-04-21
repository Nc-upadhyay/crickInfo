package com.nc.controllers;


import com.nc.models.GalleryModel;
import com.nc.models.Match;
import com.nc.services.CricketService;
import com.nc.services.impl.CricketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cricket")
@CrossOrigin("*")
public class CricketController {

    @Autowired
    private CricketService cricketService;
    @Autowired
    private CricketServiceImpl implService;

    public CricketController(CricketService cricketService) {
        this.cricketService = cricketService;
    }

    // api for getting live matches

    @GetMapping("/news")
    public ResponseEntity<?> getLiveMatchScores() throws InterruptedException {
        return new ResponseEntity<>(this.cricketService.getNews(), HttpStatus.OK);
    }

    @GetMapping("/bater-test-ranking")
    public ResponseEntity<?> getTestRank() throws InterruptedException {
        return new ResponseEntity<>(this.cricketService.getTestRank(), HttpStatus.OK);
    }

    @GetMapping("/bater-odi-ranking")
    public ResponseEntity<?> getODIRank() throws InterruptedException {
        return new ResponseEntity<>(this.cricketService.getODIRank(), HttpStatus.OK);
    }

    @GetMapping("/bow-t20")
    public ResponseEntity<?> getBowlerT20Rank() throws InterruptedException {
        return new ResponseEntity<>(this.cricketService.getBowlerT20Rank(), HttpStatus.OK);
    }

    @GetMapping("/bow-test")
    public ResponseEntity<?> getBowlerTestRank() throws InterruptedException {
        return new ResponseEntity<>(this.cricketService.getBowlerTestRank(), HttpStatus.OK);
    }

    @GetMapping("/bow-odi")
    public ResponseEntity<?> getBowODIRank() throws InterruptedException {
        return new ResponseEntity<>(this.cricketService.getBowlerODIRank(), HttpStatus.OK);
    }

    @GetMapping("/batter-t20-ranking")
    public ResponseEntity<?> getT20Rank() throws InterruptedException {
        return new ResponseEntity<>(this.cricketService.getT20Rank(), HttpStatus.OK);
    }

    @GetMapping("/point-table")
    public ResponseEntity<?> getCWC2023PointTable() {
        return new ResponseEntity<>(this.cricketService.getCWC2023PointTable(), HttpStatus.OK);
    }

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getAllMatches() {
        return new ResponseEntity<>(this.cricketService.getAllMatches(), HttpStatus.OK);
    }

    @GetMapping("/live-matches")
    public ResponseEntity<List<Match>> getLiveMatches() {
        return new ResponseEntity<>(this.cricketService.getLiveMatches(), HttpStatus.OK);
    }

    @GetMapping("/gallery")
    public ResponseEntity<List<GalleryModel>> getGallery() {
        return new ResponseEntity<>(implService.gallery, HttpStatus.OK);
    }
    @GetMapping("/world-test")
    public ResponseEntity<?> getWorldTestChampionship() {
        return new ResponseEntity<>(cricketService.getWorldTestChampionship(), HttpStatus.OK);
    }
}
