//import api.GeoLocation;
//import api.GeoLocationImpl;
//import com.google.gson.*;
//
//import java.lang.reflect.Type;
//import java.util.LinkedList;
//
////"value":5.0,
////        *                 "type":-1,
////        *                 "pos":"35.197656770719604,32.10191878639921,0.0"
//
//
//public class PokemonJsonDeserializer implements JsonDeserializer<LinkedList<Pokemon>> {
//    @Override
//    public LinkedList<Pokemon> deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//        LinkedList<Pokemon> _pokemons = new LinkedList<>();
//        JsonObject jsonObject = json.getAsJsonObject();
//        JsonArray pokemons = jsonObject.get("Pokemons").getAsJsonArray();
//        for (JsonElement poks: pokemons) {
//            Pokemon pok = new Pokemon();
//            JsonObject A = ((JsonObject) poks).get("Pokemon").getAsJsonObject();
//            int ty = A.get("type").getAsInt();
//            double value = A.get("value").getAsDouble();
//            String p = A.get("pos").getAsString();
//            String[] pos = p.split(",");
//            double x = Double.parseDouble(pos[0]);
//            double y = Double.parseDouble(pos[1]);
//            double z = Double.parseDouble(pos[2]);
//            GeoLocation pp = new GeoLocationImpl(x, y, z);
//            pok.set_Type(ty);
//            pok.set_Value(value);
//            pok.set_Pos(pp);
//            _pokemons.add(pok);
//        }
//        return _pokemons;
//    }
//
//
//}
////
