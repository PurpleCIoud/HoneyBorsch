package net.honey;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.ArrayList;
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
    public List<Object> getFromId(int id) {
        JsonNode dataNode = jn.get(id);
        ArrayList<Object> output = new ArrayList<>();
        CardPOJO card;
        for (Iterator<String> it = dataNode.fieldNames(); it.hasNext(); ) {
            output.add(dataNode.get(it.next()));
        }
        System.out.println(output);
        return output;
    }


}
