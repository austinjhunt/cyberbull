package edu.vanderbilt.cs.cyberbull.core.news;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsFinder {
    NewsApiClient newsApiClient;
    public List<Article> queryNewsForBusiness(String businessName) {
        newsApiClient = new NewsApiClient("86d119be2db54c7ca381284c3551e0d3");
        System.out.println("Querying news for business name " + businessName);
        List<Article> news = new ArrayList<>();
        newsApiClient.getEverything(
            new EverythingRequest.Builder().q(businessName).build(),
            new NewsApiClient.ArticlesResponseCallback() {
                @Override
                public void onSuccess(ArticleResponse response) {
                    int count = 0;
                    List<Article> articles = response.getArticles();
                    while (count < 5){
                        news.add(articles.get(count));
                        count += 1;
                    }
                }
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println(throwable.getMessage());
                }
            }
        );
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            //no op
        }
        System.out.println("News size: " + news.size());
        return news;
    }
    public static void main(String[] args){
        NewsFinder newsFinder = new NewsFinder();
        newsFinder.queryNewsForBusiness("Apple");
    }
}
