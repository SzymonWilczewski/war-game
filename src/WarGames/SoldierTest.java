package WarGames;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoldierTest {

    @Test
    public void testGetRank() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        Soldier soldier = general.getSoldiers().iterator().next();
        assertEquals(1, soldier.getRank());
    }

    @Test
    public void testGetExperience() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        Soldier soldier = general.getSoldiers().iterator().next();
        assertEquals(1, soldier.getExperience());
    }

    @Test
    public void testGetStrength() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        Soldier soldier = general.getSoldiers().iterator().next();
        assertEquals(1, soldier.getStrength());
    }

    @Test
    public void testKill() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        Soldier soldier = general.getSoldiers().iterator().next();
        soldier.kill();
        assertEquals(0, soldier.getExperience());
    }

    @Test
    public void testPromotion() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        Soldier soldier = general.getSoldiers().iterator().next();
        soldier.gainExperience();
        soldier.gainExperience();
        soldier.gainExperience();
        soldier.gainExperience();
        soldier.promotion();
        assertEquals(2, soldier.getRank());
    }

    @Test
    public void testGainExperience() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        Soldier soldier = general.getSoldiers().iterator().next();
        soldier.gainExperience();
        assertEquals(2, soldier.getExperience());
    }

    @Test
    public void testLoseExperience() {
        General general = new General("Harris", 100);
        general.buySoldier(1);
        Soldier soldier = general.getSoldiers().iterator().next();
        soldier.gainExperience();
        soldier.loseExperience();
        assertEquals(1, soldier.getExperience());
    }

}
