package edu.vanderbilt.cs.cyberbull.core.news;

import com.kwabenaberko.newsapilib.models.Article;

import java.util.ArrayList;
import java.util.List;

public class NewsManager {
    private final NewsFinder newsFinder;
    private final List<Article> newsCache;
    public NewsManager(){
        newsFinder = new NewsFinder();
        newsCache = new ArrayList<>();
    }
    public List<Article> getBusinessNews(String businessName){
        if (newsCache.size() == 0) {
            newsCache.addAll(newsFinder.queryNewsForBusiness(businessName));
        }
        return newsCache;
    }
}
