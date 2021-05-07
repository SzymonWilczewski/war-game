package WarGames;

import java.util.*;

public class Secretary implements Observer {
    @Override
    public void update(General general) {
        String name = general.getName();
        ArrayList<String> news = general.getNews();

        System.out.println(name + ": " + news.get(news.size() - 1));
    }
}
