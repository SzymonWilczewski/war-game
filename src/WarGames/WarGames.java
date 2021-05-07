package WarGames;

import java.util.*;

public class WarGames {
    public static void main(String[] args) throws Exception {
        System.out.print("Type \"s\" to save game or \"l\" to load game: ");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        System.out.println(choice);

        if (choice.equals("s")) {
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

            System.out.println("Harris: Balance " + (int) generalHarris.getMoney());
            System.out.println("Zhukov: Balance " + (int) generalZhukov.getMoney());

            General.writeToFile(generalHarris);
            General.writeToFile(generalZhukov);
        } else if (choice.equals("l")) {
            General generalHarris = General.readFromFile("Harris");
            General generalZhukov = General.readFromFile("Zhukov");

            System.out.println("Harris: Balance " + (int) generalHarris.getMoney());
            System.out.println("Zhukov: Balance " + (int) generalZhukov.getMoney());
        }
    }
}
