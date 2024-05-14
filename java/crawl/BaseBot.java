package crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseBot extends Thread{
    //biến url cơ sở
    final String baseUrl;
    protected final List<String> visitedUrls = new ArrayList<>();

    public BaseBot(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void run() {
        crawl(baseUrl);
    }
    public List<String> getVisitedUrls() {
        return visitedUrls;
    }

    public Elements display(String url, String type) {
        Elements datas = null;
        try {
            Document doc = Jsoup.connect(url).get();
            datas = doc.select(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public void crawl(String url) {
        try {
            if (!visitedUrls.contains(url)) {
                Document doc = Jsoup.connect(url).get();
                visitedUrls.add(url);
                Elements links = doc.select(".menuTop a[href]");
                // Nhận tất cả các liên kết bên trong lớp menuTop
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    if (!visitedUrls.contains(linkHref)) {
                        visitedUrls.add(linkHref);
                        // Ở đó, lặp lại trang web bên trong
                        crawlPage(linkHref);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error crawling " + url + ": " + e.getMessage());
        }
    }

    protected void crawlPage(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            // Tìm kiếm phân trang (nếu tồn tại)
            Elements pagings = doc.select(".paging a[href]");
            for (Element page : pagings) {
                String pageHref = page.attr("href");
                // Đi tới từng trang, đảm bảo rằng mỗi trang đều chưa có trong Url đã truy cập
                if (!visitedUrls.contains(pageHref) && !pageHref.endsWith("/1.html")) {
                    visitedUrls.add(pageHref);
                    // Also, each sub has some new paging too
                    crawlPage(pageHref); // Thu thập dữ liệu đệ quy các liên kết phân trang
                }
            }
        } catch (IOException e) {
            System.err.println("Error crawling " + url + ": " + e.getMessage());
        }
    }

    private void printVisitedUrls() {
        for(String s : visitedUrls){
            System.out.println(s);
        }
    }
}
