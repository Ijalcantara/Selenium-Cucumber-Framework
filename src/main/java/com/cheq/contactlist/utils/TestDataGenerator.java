package com.cheq.contactlist.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;


public class TestDataGenerator {
	
   private static final Faker faker = new Faker();
   private static final ObjectMapper objectMapper = new ObjectMapper();
   private static final Map<String, JsonNode> dataDictionary = new ConcurrentHashMap<>();

   public static String generateTestData(String[] fields) {
       String dataGroup = UUID.randomUUID().toString();
       ObjectNode dataGroupNode = objectMapper.createObjectNode();
       for (String field : fields) {
           switch (field) {
               case "firstName":
                   dataGroupNode.put("firstName", faker.name().firstName());
                   break;
               case "lastName":
                   dataGroupNode.put("lastName", faker.name().lastName());
                   break;
               case "email":
                   dataGroupNode.put("email", faker.internet().emailAddress());
                   break;
               case "country":
                   dataGroupNode.put("country", faker.country().capital());
                   break;
               default:
                   break;
           }
       }
       dataDictionary.put(dataGroup, dataGroupNode);
       return dataGroup;
   }
   
   public static JsonNode getTestData(String dataGroup) {
       return dataDictionary.get(dataGroup);
   }
   
   public static JsonNode generateContactWithEmptyLastName() {
	    ObjectNode contactNode = (ObjectNode) generateValidContact();
	    contactNode.put("lastName", "");  // empty lastName for negative test
	    return contactNode;
	}
   
   public static JsonNode generateValidContact() {
	    ObjectNode contactNode = objectMapper.createObjectNode();
	    contactNode.put("firstName", faker.name().firstName());
	    contactNode.put("lastName", faker.name().lastName());
	    contactNode.put("email", faker.internet().emailAddress());
	    contactNode.put("phone", faker.phoneNumber().cellPhone());
	    contactNode.put("postalCode", faker.address().zipCode());
	    return contactNode;
	}
}
