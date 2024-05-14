package crawl.crawl1;

import crawl.BaseBot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImgUrlBot extends BaseBot {

    private List<String> extractedImageUrls;

    public ImgUrlBot(String baseUrl) {
        super(baseUrl);
        this.extractedImageUrls = new ArrayList<>();
    }

    @Override
    public void crawl(String baseUrl) {
        super.crawl(baseUrl);
        for (String url : visitedUrls) {
            getImageUrl(url);
        }
    }

    public void getImageUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String type = ".news-item";
            Elements data = doc.select(type);
            for (Element item : data) {
                String imgUrl = item.select("img").attr("src");
                extractedImageUrls.add(imgUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getExtractedImageUrls() {
        return extractedImageUrls;
    }
}