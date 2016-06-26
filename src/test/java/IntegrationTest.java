import org.fluentlenium.adapter.FluentTest;
import java.util.ArrayList;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Welcome back!");
  }

  @Test
  public void newStylistIsDisplayedTest() {
    Stylist myStylist = new Stylist("Bob");
    myStylist.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Bob");
  }

  @Test
  public void ableToSubmitNewStylist() {
    goTo("http://localhost:4567/");
    fill("#name").with("Sarah");
    submit("#thisSubmit");
    assertThat(pageSource()).contains("Sarah");
  }

  @Test
  public void goToStylistPage() {
    Stylist stylistMac = new Stylist("Mac");
    stylistMac.save();
    String macPath = String.format("http://localhost:4567/stylists/%d", stylistMac.getId());
    goTo(macPath);
    assertThat(pageSource()).contains("Stylist: Mac");
  }

  @Test
  public void addClientToStylistInApp() {
    Stylist stylistMary = new Stylist("Mary");
    stylistMary.save();
    String maryPath = String.format("http://localhost:4567/stylists/%d", stylistMary.getId());
    goTo(maryPath);
    fill("#client_name").with("Andrew");
    fill("#phone").with("555-555-5555");
    submit("#test_btn_id");
    assertThat(pageSource()).contains("Andrew");
}

  @Test
  public void updateClientName() {
    //Adding the stylist and client:
    Stylist stylistMary = new Stylist("Mary");
    stylistMary.save();
    Client clientSabrina = new Client("Sabrina", stylistMary.getId(), "111-111-1111");
    clientSabrina.save();
    //First go to Mary's page to see that Sabrina is initially listed:
    String maryPath = String.format("http://localhost:4567/stylists/%d", stylistMary.getId());
    goTo(maryPath);
    assertThat(pageSource()).contains("Sabrina");
    //Going to page where we update Sabrina:
    String sabrinaUpdatePath = String.format("http://localhost:4567/stylists/%d/clients/%d/update", stylistMary.getId(), clientSabrina.getId());
    goTo(sabrinaUpdatePath);
    //Update Sabrina's name to Jerry:
    fill("#client_name").with("Jerry");
    submit("#client_update_button");
    //Going back to Mary's page, where her clients are in view
    goTo(maryPath);
    assertThat(pageSource()).contains("Jerry");
  }
}
