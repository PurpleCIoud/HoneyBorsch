package net.honey;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
    public void getFromId(int id) {
        Iterator<String> keys = jn.get(id).fieldNames();
        while (keys.hasNext()) {
            String s = keys.next();
            System.out.println(s + " "+ jn.findValue(s));


        }

    }
}
