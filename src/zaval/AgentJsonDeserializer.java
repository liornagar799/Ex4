//import com.google.gson.JsonDeserializer;
//
//import api.GeoLocation;
//import api.GeoLocationImpl;
//import com.google.gson.*;
//
//import java.lang.reflect.Type;
//
//public class AgentJsonDeserializer implements JsonDeserializer<Agent> {
//
//   @Override
//    public Agent deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//         Agent Age = new Agent();
//         JsonObject jsonObject = json.getAsJsonObject();
//         JsonArray ag = jsonObject.get("Agents").getAsJsonArray();
//         for (JsonElement agents: ag) {
//         JsonObject A = ((JsonObject) agents).get("Agent").getAsJsonObject();
//         int id = A.get("id").getAsInt();
//         double speed = A.get("speed").getAsDouble();
//         String p = A.get("pos").getAsString();
//         String[] pos = p.split(",");
//         double x = Double.parseDouble(pos[0]);
//         double y = Double.parseDouble(pos[1]);
//         double z = Double.parseDouble(pos[2]);
//         GeoLocation pp = new GeoLocationImpl(x, y, z);
//         int src = A.get("src").getAsInt();
//         int dest = A.get("dest").getAsInt();
//         double value = A.get("value").getAsDouble();
//         System.out.println("" + id);
//         Age.setId(id);
//         Age.setPos(pp);
//         System.out.println("" + speed);
//         Age.setSpeed(speed);
////            Age.setCurrNode(src);
////            Age.setNextNode(dest);
//         Age.setValue(value);
//        }
//        return Age;
//    }
//
//
//
//
//
//}