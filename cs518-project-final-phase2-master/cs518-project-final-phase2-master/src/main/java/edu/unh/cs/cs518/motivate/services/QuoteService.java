package edu.unh.cs.cs518.motivate.services;

import edu.unh.cs.cs518.motivate.models.Quote;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {
    private final ArrayList<Quote> quotes;

    /**
     * Default QuoteService constructor. Parses data files, and adds quotes to 'quotes' ArrayList.
     */
    public QuoteService() {
        this.quotes = new ArrayList<>();

        ArrayList<File> dataFiles = getDataFiles();
        ArrayList<JSONArray> data = readDataFiles(dataFiles);

        parseDataArrays(data);
    }

    /**
     * Gets all files in the target data directory ending with the JSON extension.
     *
     * @return Files in data directory matching extension pattern.
     */
    private ArrayList<File> getDataFiles() {
        Path dataDirectoryPath = Path.of("data").toAbsolutePath();

        File dataDirectory = new File(dataDirectoryPath.toString());

        File[] files = dataDirectory.listFiles((dir, name) -> name.endsWith(".json"));

        if (files == null) {
            System.err.println("Unable to locate data directory.");

            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(files));
        }
    }

    /**
     * Gets array of quotes from each data file.
     *
     * @param files Files to process.
     * @return JSON arrays of quotes.
     */
    private ArrayList<JSONArray> readDataFiles(ArrayList<File> files) {
        JSONParser jsonParser = new JSONParser();
        ArrayList<JSONArray> dataArrays = new ArrayList<>();

        for (File file : files) {
            try (FileReader reader = new FileReader(file)) {
                Object dataObject = jsonParser.parse(reader);
                JSONObject jsonDataObject = (JSONObject) dataObject;
                dataArrays.add((JSONArray) jsonDataObject.get("data"));
            } catch (IOException | ParseException e) {
                System.err.println("Unable to parse json file '" + file.getName() + "'. Skipping.");
            }
        }

        return dataArrays;
    }

    /**
     * Processes JSON arrays of quotes, adding each quote to the class quotes structure.
     *
     * @param dataArrays JSON arrays to process.
     */
    private void parseDataArrays(ArrayList<JSONArray> dataArrays) {
        for (JSONArray dataArray : dataArrays) {
            for (Object quoteObject : dataArray) {
                JSONObject quoteData = (JSONObject) quoteObject;

                this.quotes.add(new Quote((String) quoteData.get("quote"), (String) quoteData.get("author")));
            }
        }
    }

    /**
     * Gets a random quote.
     *
     * @return Quote if there is a non-zero number of quotes, else Empty.
     */
    public Optional<Quote> getQuote() {
        //500
        if (this.quotes.size() == 0) {
            return Optional.empty();
        }
        //200
        int randomIndex = new Random().nextInt(this.quotes.size());
        return Optional.of(this.quotes.get(randomIndex));
    }

    /**
     * Gets a random quote.
     * @param author Author of a quote to search for
     * @return Quote if there is a non-zero number of quotes, else Empty.
     */
    public Optional<Quote> getQuote(String author) {
        ArrayList<Quote> AQuotes = new ArrayList<>();
        //500
        if (this.quotes.size() == 0) {
            return Optional.empty();
        }
        for (int i = 0; i < this.quotes.size(); i++) {
            if (this.quotes.get(i).author.equals(author)) {
                //200
                AQuotes.add(this.quotes.get(i));
            }
        }
        //400
        if (AQuotes.size() == 0 ){
            return Optional.empty();
        }
        int randomIndexA = new Random().nextInt(AQuotes.size());
        return Optional.of(AQuotes.get(randomIndexA));
    }
}
