import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";

    //Homepage
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      model.put("stylists", Stylist.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //Invoked by clicking "Add new stylist" on homepage
    post("/newStylist", (request, response) -> {
      HashMap<String,Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Stylist newStylist = new Stylist(name);
      newStylist.save();
      model.put("stylists", Stylist.all());
      response.redirect("/");
      return null;
    });

    //Invoked by clicking "Delete (stylist's name)" on homepage
    get("/del/:id", (request, response) -> {
      HashMap<String,Object> model = new HashMap<String, Object>();
      Stylist currentStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      currentStylist.delete();
      response.redirect("/");
      return null;
    });

    //Page you get to by clicking "Update [stylist]'s Name" on homepage
      get("/updateStylist/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
        model.put("stylist", stylist);
        model.put("template", "templates/update_stylist.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      //Invoked by clicking "submit" on stylist update page
        post("/action/updateStylist/:id", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          Stylist currentStylist = Stylist.find(Integer.parseInt(request.params(":id")));
          String stylistName = request.queryParams("stylist_name");
          String stylistId = request.params(":id");
          currentStylist.updateName(stylistName);
          model.put("stylist", currentStylist);
          response.redirect("/");
          return null;
        });

  //Page you get to by clicking stylist name or "view/edit clients"
    get("/stylists/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist currentStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", currentStylist);
      model.put("clients", currentStylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //Invoked by clicking "submit" under "Add a new client" on a stylist's page
      post("/stylists/:id/new", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        String client_name = request.queryParams("client_name");
        int stylist_id = Integer.parseInt(request.params(":id"));
        String phone = request.queryParams("phone");
        Client newClient = new Client(client_name, stylist_id, phone);
        newClient.save();
        Stylist currentStylist = Stylist.find(Integer.parseInt(request.params(":id")));
        model.put("stylist", currentStylist);
        model.put("clients", currentStylist.getClients());
        String id = request.params(":id");
        response.redirect("/stylists/" +id);
        return null;
      });

  //Invoked by clicking "delete" next to name of client on a stylist's page
    get("/stylists/:stylistId/clients/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist currentStylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      model.put("stylist", currentStylist);
      String stylistId = request.params(":stylistId");
      Client currentClient = Client.find(Integer.parseInt(request.params(":id")));
      currentClient.delete();
      response.redirect("/stylists/" +stylistId);
      return null;
    });

  //Page you get to by clicking "Update client info" on a stylist's page
    get("/stylists/:stylistId/clients/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      model.put("client", client);
      model.put("template", "templates/client_form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  //Invoked by clicking "submit" on client update page
    post("/stylists/:stylistId/clients/:id/updateInfo", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist currentStylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      String stylistId = request.params(":stylistId");
      String clientId = request.params(":id");
      String clientName = request.queryParams("client_name");
      String clientPhone = request.queryParams("phone");
      Client currentClient = Client.find(Integer.parseInt(request.params(":id")));
      currentClient.updateName(clientName);
      currentClient.updatePhone(clientPhone);
      model.put("stylist", currentStylist);
      response.redirect("/stylists/" + stylistId);
      return null;
    });

   }
}
