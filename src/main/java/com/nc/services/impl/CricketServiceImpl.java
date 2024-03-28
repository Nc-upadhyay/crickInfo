package com.nc.services.impl;

import com.nc.models.Match;
import com.nc.models.NewsStoryModel;
import com.nc.models.PlayersRanking;
import com.nc.services.CricketService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CricketServiceImpl implements CricketService {
    List<Match> listOfMatch = new ArrayList<>();
    List<List<String>> pointTable = new ArrayList<>();
    List<NewsStoryModel> newsList = new ArrayList<>();
    List<PlayersRanking> playersRank = new ArrayList<>();
    List<PlayersRanking> playersTestRank = new ArrayList<>();
    List<PlayersRanking> playersODIRank = new ArrayList<>();
    List<PlayersRanking> playersT20Rank = new ArrayList<>();
    List<PlayersRanking> bowlerRank = new ArrayList<>();
    List<PlayersRanking> bowlerTestRank = new ArrayList<>();
    List<PlayersRanking> bowlerODIRank = new ArrayList<>();
    List<PlayersRanking> bowlerT20Rank = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger("Cricket::  ");

    @Override
    public void getMatchScores() {
        try {
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
            for (Element match : liveScoreElements) {
                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
                String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberVenue = match.select("span").text();
                Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                String textLive = match.select("div.cb-text-live").text();
                String textComplete = match.select("div.cb-text-complete").text();
                //getting match link
                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                Match match1 = new Match();
                match1.setTeamHeading(teamsHeading);
                match1.setMatchNumberVenue(matchNumberVenue);
                match1.setBattingTeam(battingTeam);
                match1.setBattingTeamScore(score);
                match1.setBowlTeam(bowlTeam);
                match1.setBowlTeamScore(bowlTeamScore);
                match1.setLiveText(textLive);
                match1.setMatchLink(matchLink);
                match1.setTextComplete(textComplete);
                match1.setMatchStatus();


//                update the match in database
                listOfMatch.add(match1);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("matches are :: " + listOfMatch);

    }

    @Override
    public List<List<String>> getCWC2023PointTable() {
        return pointTable;
    }

    public void getCWC2023PointTableFromWebSite() {
        String tableURL = "https://www.cricbuzz.com/cricket-series/6732/icc-cricket-world-cup-2023/points-table";
        try {
            Document document = Jsoup.connect(tableURL).get();
            Elements table = document.select("table.cb-srs-pnts");
            Elements tableHeads = table.select("thead>tr>*");
            List<String> headers = new ArrayList<>();
            tableHeads.forEach(element -> {
                headers.add(element.text());
            });
            pointTable.add(headers);
            Elements bodyTrs = table.select("tbody>*");
            bodyTrs.forEach(tr -> {
                List<String> points = new ArrayList<>();
                if (tr.hasAttr("class")) {
                    Elements tds = tr.select("td");
                    String team = tds.get(0).select("div.cb-col-84").text();
                    points.add(team);
                    tds.forEach(td -> {
                        if (!td.hasClass("cb-srs-pnts-name")) {
                            points.add(td.text());
                        }
                    });
                    pointTable.add(points);
                }

            });

            System.out.println(pointTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("point table is :: " + pointTable);
    }

    @Override
    public List<Match> getAllMatches() {
        logger.info("return Infor" + listOfMatch);
        return listOfMatch;
    }

    @Override
    public void getLettestNews() {
        String url = "https://www.cricbuzz.com/cricket-news";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.cb-col.cb-lst-itm-lg");
            for (Element element : elements) {
                String title = element.select("div.cb-col.cb-col-100.cb-lst-itm.cb-pos-rel.cb-lst-itm-lg").select("div.cb-col-67.cb-nws-lst-rt.cb-col.cb-col-text-container").select("div.cb-nws-time").text();
                String[] typeArr = title.split("•");
                String type = "";
                if (typeArr.length > 1) {
                    title = typeArr[1];
                    type = typeArr[0];
                } else {
                    title = "Something goes wrong";
                    type = "something goes wrong";
                }
                String imageUrl = elements.select("div.cb-col.cb-col-100.cb-lst-itm.cb-pos-rel.cb-lst-itm-lg").select("div.cb-col.cb-pos-rel meta").text();
//                if (elements.size() >= 3) {
//                    Element thirdMeta = elements.get(2); // Index 2 for the third meta tag (0-based indexing)
//                    String content = thirdMeta.attr("content");
//                    System.out.println("-> " + thirdMeta.text());
//                } else {
//                    System.out.println("-> Third meta content not found");
//                }
                String heading = element.select("div.cb-col.cb-col-100.cb-lst-itm.cb-pos-rel.cb-lst-itm-lg").select("div.cb-col-67.cb-nws-lst-rt.cb-col.cb-col-text-container").select("h2.cb-nws-hdln.line-ht24").select("a").text();
                String subHeading = element.select("div.cb-col.cb-col-100.cb-lst-itm.cb-pos-rel.cb-lst-itm-lg").select("div.cb-col-67.cb-nws-lst-rt.cb-col.cb-col-text-container").select("div.cb-nws-intr").text();
                String timing = element.select("div.cb-col.cb-col-100.cb-lst-itm.cb-pos-rel.cb-lst-itm-lg").select("div.cb-col-67.cb-nws-lst-rt.cb-col.cb-col-text-container").select("div").select("span.cb-nws-time").text();

                NewsStoryModel newsModel = new NewsStoryModel();
                newsModel.setTitle(title.trim());
                newsModel.setHeading(heading);
                newsModel.setImageUrl(imageUrl);
                newsModel.setSubHeading(subHeading);
                newsModel.setTime_ago(timing);
                newsModel.setType(type);


                newsList.add(newsModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPlayersRankData() {
        String url = "https://www.cricbuzz.com/cricket-stats/icc-rankings/men/batting";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.cb-col.text-center");
            for (Element element : elements) {

                String position = element.select("div.cb-col.cb-font-16").text();
                Elements playersData = element.select("div.cb-col.text-left");
                boolean up = false;
                boolean down = false;
                String step = playersData.select("div.cb-col.cb-col-33").select("div.cb-col.cb-col-50").text();
                String profileImage = playersData.select("div.cb-col.cb-col-50").select("img").attr("src");
                String name = playersData.select("div.cb-col.cb-rank-plyr").select("a").text();
                String country = playersData.select("div.cb-col.cb-rank-plyr").select("div.cb-font-12.text-gray").text();
                String rank = element.select("div.cb-col.cb-col-17.cb-rank-tbl.pull-right").text();
                String attr = playersData.select("div.cb-col.cb-col-33").select("div.cb-col.cb-col-50").select("span").attr("class");
//                logger.info(attr);
                if (attr.trim().equals("cb-rank-diff-up cb-ico")) {
                    up = true;
                } else if (attr.trim().equals("cb-rank-down-up cb-ico") || attr.trim().equals("cb-rank-diff-down cb-ico")) {
                    down = true;
                }
                if (step.trim().equals("–")) step = "0";
                PlayersRanking player = new PlayersRanking();
                player.setPosition(position);
                player.setStep(step);
                player.setName(name);
                player.setCountry(country);
                player.setRank(rank);
                player.setProfileImageUrl(profileImage);
                player.setUp(up);
                player.setDown(down);
                if (!position.equals("")) playersRank.add(player);
                if (position.trim().equals("100")) {
                    if (playersTestRank.isEmpty()) {
                        playersTestRank.addAll(playersRank);
                        playersRank = new ArrayList<>();
                    } else if (playersODIRank.isEmpty()) {
                        playersODIRank.addAll(playersRank);
                        playersRank = new ArrayList<>();
                    } else if (playersT20Rank.isEmpty()) {
                        playersT20Rank.addAll(playersRank);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    int count = 1;

    public void getBowlersRankData() {
        String url = "https://www.cricbuzz.com/cricket-stats/icc-rankings/men/bowling";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.cb-col.text-center");
            for (Element element : elements) {

                String position = element.select("div.cb-col.cb-font-16").text();
                Elements playersData = element.select("div.cb-col.text-left");
                boolean up = false;
                boolean down = false;
                String step = playersData.select("div.cb-col.cb-col-33").select("div.cb-col.cb-col-50").text();
                String profileImage = playersData.select("div.cb-col.cb-col-50").select("img").attr("src");
                String name = playersData.select("div.cb-col.cb-rank-plyr").select("a").text();
                String country = playersData.select("div.cb-col.cb-rank-plyr").select("div.cb-font-12.text-gray").text();
                String rank = element.select("div.cb-col.cb-col-17.cb-rank-tbl.pull-right").text();
                String attr = playersData.select("div.cb-col.cb-col-33").select("div.cb-col.cb-col-50").select("span").attr("class");
//                logger.info(attr);
                if (attr.trim().equals("cb-rank-diff-up cb-ico")) {
                    up = true;
                } else if (attr.trim().equals("cb-rank-down-up cb-ico") || attr.trim().equals("cb-rank-diff-down cb-ico")) {
                    down = true;
                }
                if (step.trim().equals("–")) step = "0";
                PlayersRanking player = new PlayersRanking();
                player.setPosition(position);
                player.setStep(step);
                player.setName(name);
                player.setCountry(country);
                player.setRank(rank);
                player.setProfileImageUrl(profileImage);
                player.setUp(up);
                player.setDown(down);
                if (!position.equals("")) bowlerRank.add(player);
                if (count == 1 && bowlerTestRank.isEmpty() && position.trim().equals("100")) {
                    bowlerTestRank.addAll(bowlerRank);
                    bowlerRank.clear();
                    count = 2;
                    logger.info(position);
                } else if (count == 2 && bowlerODIRank.isEmpty() && position.trim().equals("99")) {
                    bowlerODIRank.addAll(bowlerRank);
                    bowlerRank.clear();
                    count = 3;
                    logger.info(position);

                } else if (count == 3 && bowlerT20Rank.isEmpty() && position.trim().equals("100")) {
                    bowlerT20Rank.addAll(bowlerRank);
                    bowlerRank.clear();
                    logger.info(position);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<PlayersRanking> getT20Rank() {
        return playersT20Rank;
    }

    @Override
    public List<PlayersRanking> getODIRank() {
        return playersODIRank;
    }

    @Override
    public List<PlayersRanking> getBowlerTestRank() {
        return bowlerTestRank;
    }

    @Override
    public List<PlayersRanking> getBowlerT20Rank() {
        return bowlerT20Rank;
    }

    @Override
    public List<PlayersRanking> getBowlerODIRank() {
        return bowlerODIRank;
    }

    @Override
    public List<NewsStoryModel> getNews() {
        return newsList;
    }

    @Override
    public List<PlayersRanking> getTestRank() {
        return playersTestRank;
    }

    public void getDynamicImage() {
        logger.info("===== runing ===========");
        final WebDriver driver = new ChromeDriver();

    }
}
