package org.Playwright.Project;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This class provides the language data for testing the language of the website
 */
public class LanguageDataProvider {

    /**
     * This method provides the different parameters that are needed for language testing the website
     * @return Stream of Arguments, each containing the language name, header ID, and header text
     * @throws IOException IOException if an I/O error occurs while reading the JSON file
     */
    public static Stream<Arguments> provideParametersForHeadersTest() throws IOException {
        JsonNode jsonNode = readJsonFile("src/main/resources/languages.json");

        List<Arguments> argumentsList = new ArrayList<>();

        Iterator<Map.Entry<String, JsonNode>> languages = jsonNode.fields();
        while (languages.hasNext()) {
            Map.Entry<String, JsonNode> language = languages.next();
            String languageName = language.getKey();
            JsonNode headers = language.getValue();

            Iterator<Map.Entry<String, JsonNode>> headerFields = headers.fields();
            while (headerFields.hasNext()) {
                Map.Entry<String, JsonNode> headerField = headerFields.next();
                String headerId = headerField.getKey();
                String headerText = StringEscapeUtils.unescapeHtml4(headerField.getValue().asText());
                argumentsList.add(Arguments.of(languageName, headerId, headerText));
            }
        }

        return argumentsList.stream();
    }

    /**
     * This method reads the specified JSON file and returns its content as a JsonNode.
     * @param filePath the path to the JSON file
     * @return JsonNode representing the contents of the JSON file
     * @throws IOException if an I/O error occurs when reading the file
     */
    private static JsonNode readJsonFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(new File(filePath));
    }
}
