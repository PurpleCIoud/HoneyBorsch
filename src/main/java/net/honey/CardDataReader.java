package net.honey;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.Iterator;
import java.util.List;

// This class is responsible for retrieving card data given its ID
public class CardDataReader extends ResourceReader {
    private JsonNode jn;
    CardDataReader(String filePath) {
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
        // What we do is convert each item in the Iterator into the required type for card
        /*
         @TODO BUG: Incorrect Data casting!
         * if wrong data is inputted, the program would simply accept it, so a string
         * "something" would be cast as the integer 0, why 0? why not null
         */
        JsonNode dataNode = jn.get(id);
        Iterator<String> it = dataNode.fieldNames();

        int cId = dataNode.get(it.next()).asInt();
        String cName = dataNode.get(it.next()).asText();
        int cBsHealth = dataNode.get(it.next()).asInt();
        int cBsAttack = dataNode.get(it.next()).asInt();
        AttackType cAtkType = AttackType.valueOf(dataNode.get(it.next()).asText());
        String cImage = dataNode.get(it.next()).asText();
        String cDesc = dataNode.get(it.next()).asText();
        return new CardPOJO(cId,cName,cBsHealth,cBsAttack,cAtkType,cImage,cDesc);
    }
    // this method simply returns how many Ids there are in the json file
    public int getLength() {
        List<JsonNode> idList = jn.findValues("id");
        return idList.size();
    }
    // pretty printer, Wow, such cool :doge:
    public void printPretty() {
        System.out.println(jn.toPrettyString());
    }
}
