package com.nc;

import com.nc.services.impl.CricketServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CrickInformerApplication implements CommandLineRunner {

    @Autowired
    CricketServiceImpl cricketService;

    public static void main(String[] args) {
        SpringApplication.run(CrickInformerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        cricketService.getAllMatches();
//        cricketService.getCWC2023PointTable();
//        cricketService.getLettestNews();
//        cricketService.getPlayersRankData();
//        cricketService.getBowlersRankData();
        cricketService.getDynamicImage();

    }
}
