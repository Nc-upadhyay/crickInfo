package com.nc;

import com.nc.services.impl.CricketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableScheduling
public class CrickInformerApplication implements CommandLineRunner {

    @Autowired
    CricketServiceImpl cricketService;

    public static void main(String[] args) {
        SpringApplication.run(CrickInformerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        cricketService.getAllMatches();
//        cricketService.getIPL2024PointTableFromWebSite();
//        cricketService.getLettestNews();
//        cricketService.getPlayersRankData();
//        cricketService.getBowlersRankData();
//        cricketService.getDynamicImage();
//        cricketService.getGalleryImages();
        cricketService.getWorldTestChampionship();

    }

//    @Scheduled(fixedDelay = 300000)
//    public void callApiAfter5Minutes() {
//        cleanVariable();
//        cricketService.getAllMatches();
//        cricketService.getCWC2023PointTableFromWebSite();
//        cricketService.getLettest News();
//        cricketService.getPlayersRankData();
//        cricketService.getBowlersRankData();
//        cricketService.getDynamicImage();
//    }

    public void cleanVariable() {
        cricketService.listOfMatch.clear();
        cricketService.pointTable.clear();
        cricketService.newsList.clear();
        cricketService.playersRank.clear();
        cricketService.playersTestRank.clear();
        cricketService.playersODIRank.clear();
        cricketService.playersT20Rank.clear();
        cricketService.bowlerRank.clear();
        cricketService.bowlerTestRank.clear();
        cricketService.bowlerT20Rank.clear();

    }
}
