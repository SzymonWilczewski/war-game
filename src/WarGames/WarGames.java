package WarGames;

public class WarGames {
    public static void main(String[] args) throws Exception {
        General generalHarris = new General("Harris", 100);
        General generalZhukov = new General("Zhukov", 75);

        Secretary secretary = new Secretary();
        generalHarris.attach(secretary);
        generalZhukov.attach(secretary);

        generalHarris.buySoldier(1);
        generalHarris.buySoldier(2);
        generalHarris.buySoldier(3);
        generalHarris.buySoldier(4);

        generalZhukov.buySoldier(1);
        generalZhukov.buySoldier(1);
        generalZhukov.buySoldier(1);
        generalZhukov.buySoldier(1);
        generalZhukov.buySoldier(1);
        generalZhukov.maneuvers();
        generalZhukov.maneuvers();
        generalZhukov.maneuvers();

        generalHarris.attack(generalZhukov);

        generalHarris.writeToFile();
        generalZhukov.writeToFile();
        generalHarris.readFromFile();
    }
}
