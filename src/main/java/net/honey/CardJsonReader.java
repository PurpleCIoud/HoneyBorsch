package net.honey;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;


// This class is responsible for retrieving card data given its ID
public class CardJsonReader extends ResourceReader {
    private JsonNode jn;
    CardJsonReader(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            jn = mapper.readTree(getFileAsIOStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
 redundant code for an old way of getting items from list, I am keeping it in case of emergency
 public List<Object> getFromId(int id) {
     JsonNode dataNode = jn.get(id);
     ArrayList<Object> output = new ArrayList<>();
     for (Iterator<String> it = dataNode.fieldNames(); it.hasNext(); ) {
         output.add(dataNode.get(it.next()));
     }
     System.out.println(output);
     return output;
 }
*/

    public CardPOJO getFromId(int id) {
        // I really think there is a better way of doing this
        JsonNode dataNode = jn.get(id);

        int cId = dataNode.findValue("id").asInt(-1); // if the value is not an int set to -1
        String cName = dataNode.get("name").asText(null); // if the value is not a text set null
        int cBsHealth = dataNode.get("baseHealth").asInt(-1); // if the value is not an int set to -1
        int cBsAttack = dataNode.get("baseAttack").asInt(-1); // if the value is not an int set to -1
        AttackType cAtkType = AttackType.ERROR; // if the value is not a text set ERROR
        for (AttackType type: AttackType.values()) {
            if (Objects.equals(type.toString(), dataNode.get("attackType").asText())) {
                cAtkType = AttackType.valueOf(dataNode.get("attackType").asText());
                break;
            }
        }
        String cImage = dataNode.get("image").asText(null); // if the value is not a text set null
        String cDesc = dataNode.get("description").asText(null); // if the value is not a text set null
        return new CardPOJO(cId,cName,cBsHealth,cBsAttack,cAtkType,cImage,cDesc);
    }
    // this method simply returns how many Ids there are in the json file
    public int getLength() {
        return jn.findValues("id").size();
    }

    // gets the list of all ids as an int[]
    public int[] getIds() {
        int[] ids = new int[getLength()];
        for (int i = 0; i < getLength(); i++) {
            ids[i] = jn.get(i).asInt();
        }
        return ids;
    }
    // pretty printer, Wow, such cool :doge:
    public void printPretty() {
        System.out.println(jn.toPrettyString());
    }
}
