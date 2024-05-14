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

public class TitleBot extends BaseBot {

    private List<String> extractedTitles;

    public TitleBot(String baseUrl) {
        super(baseUrl);
        this.extractedTitles = new ArrayList<>();
    }

    @Override
    public void crawl(String baseUrl) {
        super.crawl(baseUrl);
        for (String url : visitedUrls) {
            getTitle(url);
        }
    }

    public void getTitle(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String type = ".news-item"; // Selector for the news items
            Elements data = doc.select(type);
            // Extract and store the titles
            for (Element item : data) {
                String title = item.select("h4 > a").html();
                extractedTitles.add(title);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getExtractedTitles() {
        return extractedTitles;
    }
}