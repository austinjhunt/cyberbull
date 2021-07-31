package edu.vanderbilt.cs.cyberbull.core.watchlist;

public class WatchListFactory {
    public WatchList createWatchList(String title){
        return new WatchList(title);
    }
}
