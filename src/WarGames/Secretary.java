package WarGames;

import java.util.*;
import java.io.*;

public class Secretary implements Observer, Serializable {
    @Override
    public void update(General general) {
        String name = general.getName();
        ArrayList<String> news = general.getNews();

        System.out.println(name + ": " + news.get(news.size() - 1));
    }
}
