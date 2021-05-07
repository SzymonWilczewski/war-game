package WarGames;

import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;

public class General implements Observable {
    private final String name;
    private final HashSet<Soldier> soldiers = new HashSet<>();
    private double money;
    private final HashSet<Observer> observers = new HashSet<>();
    private ArrayList<String> news = new ArrayList<>();

    public General(String name, double money) {
        this.name = name;
        this.money = money;
        news.add(name);
    }

    public String getName() { return name; }

    public HashSet<Soldier> getSoldiers() { return soldiers; }

    public double getMoney() { return money; }

    public HashSet<Observer> getObservers() { return observers; }

    public ArrayList<String> getNews() { return news; }

    public void maneuvers() throws IllegalArgumentException {
        double cost = soldiers.stream().mapToDouble(Soldier::getRank).sum();
        
        if (cost <= money) {
            soldiers.forEach(Soldier::gainExperience);
            soldiers.forEach(Soldier::promotion);
            money -= cost;
            notify("Maneuvers started");
        } else {
            throw new IllegalArgumentException("Not enough money!");
        }
    }

    public void maneuvers(HashSet<Soldier> soldiers) throws IllegalArgumentException {
        double cost = soldiers.stream().mapToDouble(Soldier::getRank).sum();

        if (cost <= money) {
            soldiers.forEach(Soldier::gainExperience);
            soldiers.forEach(Soldier::promotion);
            money -= cost;
            notify("Maneuvers started");
        } else {
            throw new IllegalArgumentException("Not enough money!");
        }
    }

    public void attack(General enemy) {
        double strength = soldiers.stream().mapToDouble(Soldier::getStrength).sum();
        double enemyStrength = enemy.soldiers.stream().mapToDouble(Soldier::getStrength).sum();
        notify("War with general " + enemy.getName() + " started");

        if (strength > enemyStrength) {
            money += (int) (0.1 * enemy.money);
            enemy.money -= (int) (0.1 * enemy.money);
            soldiers.forEach(Soldier::gainExperience);
            enemy.soldiers.forEach(Soldier::loseExperience);
            notify("We won the war");
        } else if (enemyStrength > strength) {
            money -= (int) (0.1 * money);
            enemy.money += (int) (0.1 * money);
            soldiers.forEach(Soldier::loseExperience);
            enemy.soldiers.forEach(Soldier::gainExperience);
            notify("We lost the war");
        } else {
            Soldier soldier = new ArrayList<>(soldiers)
                    .get(new Random().nextInt(soldiers.size()));
            soldier.kill();
            soldiers.remove(soldier);
            Soldier enemySoldier = new ArrayList<>(enemy.soldiers)
                    .get(new Random().nextInt(enemy.soldiers.size()));
            enemySoldier.kill();
            enemy.soldiers.remove(enemySoldier);
            notify("Draw");
        }
    }

    public void buySoldier(int rank) throws IllegalArgumentException {
        if (10 * rank <= money) {
            money -= 10 * rank;
            soldiers.add(new Soldier(rank));
            notify("New soldier bought");
        } else {
            throw new IllegalArgumentException("Not enough money!");
        }
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    public void notify(String news) {
        this.news.add(news);
        notifyObservers();
    }

    public void writeToFile() throws IOException {
        Files.write(Paths.get(name + ".txt"), news, Charset.defaultCharset());
    }

    public void readFromFile() throws IOException {
        this.news = (ArrayList<String>) Files.readAllLines(Paths.get(name + ".txt"));
    }
}
