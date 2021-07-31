package edu.vanderbilt.cs.cyberbull.core.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBankAccountTitleFactory implements RandomPhraseFactory {

    private final List<String> phrases;
    public RandomBankAccountTitleFactory(){
        phrases = new ArrayList<>();
        initializephrases();
    }
    private void initializephrases(){
        phrases.add("checking");
        phrases.add("savings");
        phrases.add("bank account");
        phrases.add("joint bank account");
        phrases.add("credit union account");
        phrases.add("bank");
        phrases.add("direct deposit account");
    }
    @Override
    public String generateRandomPhrase() {
        Random rand = new Random();
        return phrases.get(rand.nextInt(phrases.size()));
    }
}
