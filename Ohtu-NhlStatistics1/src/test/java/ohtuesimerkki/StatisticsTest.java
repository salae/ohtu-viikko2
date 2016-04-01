package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class StatisticsTest {
  
    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };  
  
  @Before
  public void setUp() {
    stats = new Statistics(readerStub);
  }

  /**
   * Test of search method, of class Statistics. 
   * The searched player is found.
   */
  @Test
  public void testSearchFound() {
    System.out.println("search");
    String name = "Kurri";
    int expResult = readerStub.getPlayers().get(2).getGoals();
    Player result = stats.search(name);
    assertEquals(expResult, result.getGoals());
  }
  
  /**
   * Test of search method, of class Statistics.
   * the searched player is not found.
   */
  @Test
  public void testSearchNotFound() {
    System.out.println("search");
    String name = "Smith";
    assertEquals(null, stats.search(name));
  }  

  /**
   * Test of team method, of class Statistics.
   */
  @Test
  public void testTeam() {
    System.out.println("team");
    String teamName = "EDM";
    List<Player> result = stats.team(teamName);
    assertEquals(3, result.size());
  }

  /**
   * Test of topScorers method, of class Statistics.
   */
  @Test
  public void testTopScorers() {
    System.out.println("topScorers");
    int howMany = 3;
    String expResult = "Gretzky";
    List<Player> result = stats.topScorers(howMany);
    assertEquals(expResult, result.get(0).getName());
  }
  
  
  
}
