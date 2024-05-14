package crawl.crawl1;

import crawl.BaseBot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentBot extends BaseBot {
    private List<String> extractedContent;

    public ContentBot(String baseUrl) {
        super(baseUrl);
        this.extractedContent = new ArrayList<>();
    }
    @Override
    public void crawl(String baseUrl) {
        super.crawl(baseUrl);
        for (String url : visitedUrls) {
            getContent(url);
        }
    }
    public void getContent(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String type = ".news-item"; // Thể loại truyện
            Elements data = doc.select(type);
            // Extract and store the content
            for (Element item : data) {
                String sapo = item.select(".sapo").text();
                extractedContent.add(sapo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getExtractedContent() {
        return extractedContent;
    }
}

