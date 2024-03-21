package com.nc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrickInformerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrickInformerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try{
			String url="https://www.cricbuzz.com/cricket-match/live-scores";
			Document document= Jsoup.connect(url).get();
			Elements liveMatches=document.select("div.cb-col.cb-lv-main");
			for(Element match : liveMatches){
				String tour=match.select("h2.cb-lv-grn-strip.cb-lv-scr-mtch-hdr").select("a").text();
				String teamHeading=match.select("div.cb-col-100.cb-billing-plans-text").select("a").text();
				String matchNumber= match.select("span").text();
				String matchValue=match.select("div.text-gray").select("span.text-gray").text();
				Elements matchBatTeamInfo=match.select("div.cb-hmscg-bat-txt.cb-ovr-flo");
				String batTeamName=matchBatTeamInfo.select("div.cb-ovr-flo.cb-hmscg-tm-nm").text();
					String batTeamScore=matchBatTeamInfo.select("div.cb-ovr-flo+div").text();

				Elements matchBolTeamInfo=match.select("div.cb-hmscg-bwl-txt ");
				String bolTeamName=matchBolTeamInfo.select("div.cb-ovr-flo.cb-hmscg-tm-nm").text();
				String bolTeamScore=matchBolTeamInfo.select("div.cb-ovr-flo+div").text();
				String textLive=match.select("div.cb-text-live").text();
				String textComplete = match.select("div.cb-text-complete").text();
				String matchLink = "https://www.cricbuzz.com/"+match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

				System.out.println(" textComplete "+ matchLink);

			}
			//System.out.println(liveMatches);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
