import java.util.*;
import org.sql2o.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Client {
  private int id;
  private String client_name;
  private String phone;
  //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
  private int stylist_id;

  public int getId() {
    return id;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public String getPhone() {
    return phone;
  }

  public String getClientName() {
    return client_name;
  }

  public Client(String client_name, int stylist_id, String phone) {
    this.client_name = client_name;
    this.phone = phone;
    this.stylist_id = stylist_id;
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    }
    else {
      Client newClient = (Client) otherClient;
      return this.getClientName().equals(newClient.getClientName()) &&
             this.getId() == newClient.getId() &&
             this.getPhone().equals(newClient.getPhone());
    }
  }

  public static List<Client> all() {
  String sql = "SELECT * FROM clients";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Client.class);
  }
 }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO clients (client_name, stylist_id, phone) VALUES (:client_name, :stylist_id, :phone)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("client_name", client_name)
      .addParameter("stylist_id", stylist_id)
      .addParameter("phone", phone)
      .executeUpdate()
      .getKey();
   }
}
   public static Client find(int id) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM clients where id=:id";
     Client client = con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetchFirst(Client.class);
     return client;
   }
 }

 public void delete() {
  try(Connection con = DB.sql2o.open()) {
  String sql = "DELETE FROM clients WHERE id = :id;";
  con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
  }
}

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET client_name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void updatePhone(String phone) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET phone = :phone WHERE id = :id";
      con.createQuery(sql)
        .addParameter("phone", phone)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

}
