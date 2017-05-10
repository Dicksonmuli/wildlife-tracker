import org.sql2o.*;

public class DB {
  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "dickson", "dickson");
//   public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-50-17-236-15.compute-1.amazonaws.com:5432/d7jf4e2jrk48mq", "phtrzuiktlilam", "ed5c7178459027617f8294f3380dc9fae0341f5dbc43f3fab70915571edc32e7");
}
