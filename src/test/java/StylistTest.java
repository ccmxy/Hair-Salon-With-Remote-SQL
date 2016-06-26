import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void getClientListMatchStylistID() {
    Stylist newStylist = new Stylist("Tom");
    newStylist.save();
    Client clientOne = new Client("Anna", newStylist.getId(), "503-555-5493");
    clientOne.save();
    Client clientTwo = new Client("Bebe", newStylist.getId(), "503-555-5555");
    clientTwo.save();
    Client stylistClient = newStylist.getClients().get(0);

    assertTrue(stylistClient.getClientName().equals("Anna"));
  }

  @Test
  public void count_returnsCorrectCountsIfStylistIdAreTheSame() {
    Stylist newStylist = new Stylist("Harriet");
    newStylist.save();
    Client clientOne = new Client("Anna", 1, "503-555-5555");
    clientOne.save();
    assertEquals(1, newStylist.count(1));
  }

  @Test
  public void equals_returnsTrueIfStylistsAreTheSame() {
      Stylist firstStylist = new Stylist("Tom");
      Stylist secondStylist = new Stylist("Tom");
      assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void true_stylistNameUpdated(){
    Stylist thisStylist = new Stylist("Hannah");
    thisStylist.save();
    thisStylist.updateName("Tammy");
    int stylistId = thisStylist.getId();
    Stylist foundStylist = Stylist.find(stylistId);
    assertEquals(foundStylist.getName(), "Tammy");
  }
}
