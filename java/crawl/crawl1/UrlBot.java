package crawl.crawl1;

import crawl.BaseBot;
import crawl.Entity.Truyen;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UrlBot extends BaseBot {
    private List<String> extractedUrls;

    public UrlBot(String baseUrl) {
        super(baseUrl);
        this.extractedUrls = new ArrayList<>();
    }

    @Override
    public void crawl(String baseUrl) {
        super.crawl(baseUrl);
        for (String url : visitedUrls) {
            getStoryUrl(url);
        }
    }

    public void getStoryUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String type = ".news-item";
            Elements data = doc.select(type);
            for (Element item : data) {
                String storyUrl = item.select("h4 > a").attr("href");
                extractedUrls.add(storyUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getExtractedUrls() {
        return extractedUrls;
    }
}