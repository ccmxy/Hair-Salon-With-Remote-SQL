import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class ClientTest {

     @Rule
     public DatabaseRule database = new DatabaseRule();

     @Test
     public void all_emptyAtFirst() {
       assertEquals(Client.all().size(), 0);
     }

     @Test
     public void equals_returnsTrueIfClientAreTheSame() {
       Client firstClient = new Client("John", 1, "5034343");
       Client secondClient = new Client("John", 1, "5034343");
       assertTrue(firstClient.equals(secondClient));
     }

     @Test
      public void save_returnsTrueIfClientIDAretheSame() {
       Client myClient = new Client("John", 1, "324224");
       myClient.save();
       assertEquals(Client.all().get(0).getId(), myClient.getId());
      }

      @Test
      public void clientSaved(){
        Client myClient = new Client("John", 1, "503-555-5555");
        myClient.save();
        Client savedClient = Client.all().get(0);
        assertEquals(myClient.getId(), savedClient.getId());
      }

      @Test
      public void phoneUpdated(){
        Client myClient = new Client("John", 1, "444-444-4444");
        myClient.save();
        myClient.updatePhone("555-555-5555");
        int clientId = myClient.getId();
        Client foundClient = Client.find(clientId);
        assertEquals(foundClient.getPhone(),"555-555-5555");
      }

      @Test
      public void nameUpdated(){
        Client myClient = new Client("John", 1, "444-444-4444");
        myClient.save();
        myClient.updateName("Jen");
        int clientId = myClient.getId();
        Client foundClient = Client.find(clientId);
        assertEquals(foundClient.getClientName(),"Jen");
      }

      @Test
      public void findClientInDatabase(){
        Client myClient = new Client("Sarah", 1, "555-555-5555");
        myClient.save();
        Client savedClient = Client.find(myClient.getId());
        assertTrue(myClient.equals(savedClient));
      }
 }
