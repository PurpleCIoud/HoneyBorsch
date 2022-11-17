package net.honey;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

// This class is responsible for retrieving card data given its ID
public class CardDataReader extends ResourceReader {
    private JsonNode node;
    CardDataReader(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            node = mapper.readTree(getFileAsIOStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void getFromID(int id) {
        while (node.findValue("ids").findValue("id").asInt() == id) {
            System.out.println(node.findValue("ids").findValue("name"));
        }
    }
}
