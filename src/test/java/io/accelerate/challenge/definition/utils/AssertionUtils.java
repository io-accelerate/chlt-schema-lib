package io.accelerate.challenge.definition.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;

public class AssertionUtils {
    public static <T> void assertDeserializesToIdenticalObject(Object originalObject, Class<T> objClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String serialised = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(originalObject);
        T deserializedChallengeRound = mapper.readValue(serialised, objClass);
        assertThat(deserializedChallengeRound, equalTo(originalObject));
    }

    public static void assertSerializesTo(String expectedString, Object originalObject) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String serialised = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(originalObject);
        System.out.println(serialised);
        assertThat(serialised, equalToCompressingWhiteSpace(expectedString));
    }
}
