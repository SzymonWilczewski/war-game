package WarGames;

import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.*;

public class GeneralTest {

    @Test
    public void testGetName() {
        General general = new General("Harris", 100);
        assertEquals("Harris", general.getName());
    }

    @Test
    public void testGetSoldiers() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        general.buySoldier(2);
        assertEquals(2, general.getSoldiers().size());
    }

    @Test
    public void testMoney() {
        General general = new General("Harris", 100);
        assertEquals(100, general.getMoney());
    }

    @Test
    public void testGetObservers() {
        General general = new General("Harris", 100);
        Secretary secretary = new Secretary();
        general.attach(secretary);
        assertEquals(1, general.getObservers().size());
    }

    @Test
    public void testGetNews() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        assertEquals(2, general.getNews().size());
    }

    @Test
    public void testManeuvers() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        general.buySoldier(1);
        general.maneuvers();
        assertEquals(4, general.getSoldiers().stream().mapToDouble(Soldier::getStrength).sum());
    }

    @Test
    public void testManeuversWithSpecifiedSoldiers() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        general.buySoldier(1);
        Soldier soldier = general.getSoldiers().iterator().next();
        general.maneuvers(new HashSet<>(Collections.singletonList(soldier)));
        assertEquals(3, general.getSoldiers().stream().mapToDouble(Soldier::getStrength).sum());
    }

    @Test
    public void testAttackWin() {
        General general1 = new General("Harris", 100);
        General general2 = new General("Zhukov", 75);
        general1.buySoldier(1);
        general1.buySoldier(2);
        general2.buySoldier(1);
        general1.attack(general2);
        assertEquals(76, general1.getMoney());
    }

    @Test
    public void testAttackDefeat() {
        General general1 = new General("Harris", 100);
        General general2 = new General("Zhukov", 75);
        general1.buySoldier(1);
        general1.buySoldier(2);
        general2.buySoldier(4);
        general1.attack(general2);
        assertEquals(63, general1.getMoney());
    }

    @Test
    public void testAttackDraw() {
        General general1 = new General("Harris", 100);
        General general2 = new General("Zhukov", 75);
        general1.buySoldier(1);
        general1.buySoldier(2);
        general2.buySoldier(3);
        general1.attack(general2);
        assertEquals(70, general1.getMoney());
    }

    @Test
    public void testBuySoldier() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        assertEquals(1, general.getSoldiers().size());
    }

    @Test
    public void testAttach() {
        General general = new General("Harris", 100);
        Secretary secretary = new Secretary();
        general.attach(secretary);
        assertEquals(1, general.getObservers().size());
    }

    @Test
    public void testDetach() {
        General general = new General("Harris", 100);
        Secretary secretary = new Secretary();
        general.attach(secretary);
        general.detach(secretary);
        assertEquals(0, general.getObservers().size());
    }

    @Test
    public void testNotify() {
        General general = new General("Harris", 100);
        general.notify("test");
        assertEquals(2, general.getNews().size());
    }

    @Test
    public void testWriteToFile() throws IOException, ClassNotFoundException {
        General general = new General("TestWrite", 100);
        general.buySoldier(1);
        General.writeToFile(general);
        General testGeneral = General.readFromFile("TestWrite");
        assertEquals(90, testGeneral.getMoney());
    }

    @Test
    public void testReadFromFile() throws IOException, ClassNotFoundException {
        General general = new General("TestRead", 100);
        general.buySoldier(1);
        General.writeToFile(general);
        general.buySoldier(1);
        General testGeneral = General.readFromFile("TestRead");
        assertEquals(90, testGeneral.getMoney());
    }

}
