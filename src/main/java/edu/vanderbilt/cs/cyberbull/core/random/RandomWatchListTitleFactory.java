package edu.vanderbilt.cs.cyberbull.core.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWatchListTitleFactory implements RandomPhraseFactory {

    private final List<String> phrases;
    public RandomWatchListTitleFactory(){
        phrases = new ArrayList<>();
        initializephrases();
    }
    private void initializephrases(){
        phrases.add("good fundamentals");
        phrases.add("meme stocks");
        phrases.add("motley fool stocks");
        phrases.add("might invest");
        phrases.add("popular stocks");
        phrases.add("controversial stocks");
    }
    @Override
    public String generateRandomPhrase() {
        Random rand = new Random();
        return phrases.get(rand.nextInt(phrases.size()));
    }
}
