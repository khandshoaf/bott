package crawl.crawl1;

import crawl.BaseBot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryBot extends BaseBot {

    private Set<String> visitedGenres;
    public CategoryBot(String baseUrl) {
        super(baseUrl);
        visitedGenres = new HashSet<>(); // Khởi tạo danh sách khi khởi tạo đối tượng
    }

    @Override
    protected void crawlPage(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements genres = doc.select(".menuTop a");

            for (Element genre : genres) {
                String genreName = genre.text();
                String genreUrl = genre.absUrl("href");

                // Kiểm tra xem thể loại đã được trích xuất chưa trước khi in ra kết quả
                if (!visitedGenres.contains(genreName)) {
                    visitedGenres.add(genreName); // Thêm thể loại vào danh sách đã trích xuất
                    System.out.println("Thể loại truyện: " + genreName + " - URL " + genreUrl);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi lục soát trang: " + e.getMessage());
        }
    }
}
