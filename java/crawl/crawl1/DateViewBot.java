package crawl.crawl1;

import crawl.BaseBot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DateViewBot extends BaseBot {

    private List<String> extractedViews;

    public DateViewBot(String baseUrl) {
        super(baseUrl);
        this.extractedViews = new ArrayList<>();
    }

    @Override
    public void crawl(String baseUrl) {
        super.crawl(baseUrl);
        for (String url : visitedUrls) {
            getViews(url);
        }
    }

    public void getViews(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String type = ".news-item";
            Elements data = doc.select(type);
            for (Element item : data) {
                String views = item.select(".info-post").text();
                extractedViews.add(views);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getExtractedViews() {
        return extractedViews;
    }
}
