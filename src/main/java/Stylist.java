import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Stylist(String name) {
    this.name = name;
  }

  public static List<Stylist> all() {
    String sql ="SELECT id, name FROM stylists ORDER BY name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql ="INSERT INTO stylists (name) values (:name)";
      this.id = (int) con.createQuery(sql,true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static Stylist find(int id ) {
    try(Connection con = DB.sql2o.open()) {
      String sql ="SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id",id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where stylist_id=:id";
      return con.createQuery(sql)
       .addParameter("id", this.id)
       .executeAndFetch(Client.class);
    }
  }

  public void delete() {
   try(Connection con = DB.sql2o.open()) {
   String sql = "DELETE FROM stylists WHERE id = :id;";
   con.createQuery(sql)
     .addParameter("id", id)
     .executeUpdate();
   }
 }

 public void updateName(String name) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "UPDATE stylists SET name = :name WHERE id = :id";
     con.createQuery(sql)
       .addParameter("name", name)
       .addParameter("id", this.id)
       .executeUpdate();
   }
 }

  public int count(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(*) FROM clients where stylist_id=:id";
      return (int) con.createQuery(sql).addParameter("id",id).executeScalar(Integer.class);
    }
 }

 @Override
 public boolean equals(Object otherStylist) {
   if(!(otherStylist instanceof Stylist )) {
     return false;
   }
   else {
     Stylist newStylist = (Stylist) otherStylist;
     return this.getName().equals(newStylist.getName());
   }
 }

}
