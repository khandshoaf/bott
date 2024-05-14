package run;

import crawl.crawl1.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String baseUrl = "https://cotich.net/";

        CategoryBot category = new CategoryBot(baseUrl);
        TitleBot titleBot = new TitleBot(baseUrl);
        UrlBot urlBot = new UrlBot(baseUrl);
        ImgUrlBot imgUrlBot = new ImgUrlBot(baseUrl);
        DateViewBot viewsBot = new DateViewBot(baseUrl);
        ContentBot content = new ContentBot(baseUrl);

        System.out.println("Bắt đầu crawling ...");
        category.start();


        titleBot.crawl(baseUrl);
        urlBot.crawl(baseUrl);
        imgUrlBot.crawl(baseUrl);
        viewsBot.crawl(baseUrl);
        content.crawl(baseUrl);

        List<String> titles = titleBot.getExtractedTitles();
        List<String> storyUrls = urlBot.getExtractedUrls();
        List<String> imageUrls = imgUrlBot.getExtractedImageUrls();
        List<String> views = viewsBot.getExtractedViews();
        List<String> conten = content.getExtractedContent();

        for (int i = 0; i < titles.size(); i++) {
            System.out.println("Title: " + titles.get(i));
            System.out.println("Story URL: " + storyUrls.get(i));
            System.out.println("Image URL: " + imageUrls.get(i));
            System.out.println(views.get(i));
            System.out.println("Nội dung:" + conten.get(i));
            System.out.println("--------------------------------------");
        }
    }
}