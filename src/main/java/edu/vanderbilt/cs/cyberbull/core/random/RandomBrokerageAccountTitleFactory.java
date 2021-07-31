package edu.vanderbilt.cs.cyberbull.core.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBrokerageAccountTitleFactory implements RandomPhraseFactory {
    private final List<String> phrases;
    public RandomBrokerageAccountTitleFactory(){
        phrases = new ArrayList<>();
        initializephrases();
    }
    private void initializephrases(){
        phrases.add("retirement");
        phrases.add("money maker 3000");
        phrases.add("investments");
        phrases.add("brokerage account");
        phrases.add("my securities");
    }
    @Override
    public String generateRandomPhrase() {
        Random rand = new Random();
        return phrases.get(rand.nextInt(phrases.size()));
    }
}
