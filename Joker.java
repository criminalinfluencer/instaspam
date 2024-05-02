import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JokeFetcher {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Random random = new Random();

    public static String geeks() throws IOException, InterruptedException {
        String url = "https://geek-jokes.sameerkumar.website/api";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode json = mapper.readTree(response.body());
        return json.textValue();
    }

    public static String chuckNorris() throws IOException, InterruptedException {
        String url = "https://api.icndb.com/jokes/random";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode json = mapper.readTree(response.body());
        return json.path("value").path("joke").textValue();
    }

    public static String stormConsultancyQuotes() throws IOException, InterruptedException {
        String url = "http://quotes.stormconsultancy.co.uk/random.json";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode json = mapper.readTree(response.body());
        return json.path("quote").textValue();
    }

    public static String catFactQuotes() throws IOException, InterruptedException {
        String url = "https://cat-fact.herokuapp.com/facts/random";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode json = mapper.readTree(response.body());
        return json.path("text").textValue();
    }

    public static String getMsg() throws IOException, InterruptedException {
        int x = random.nextInt(4);
        switch (x) {
            case 0:
                return geeks();
            case 1:
                return chuckNorris();
            case 2:
                return stormConsultancyQuotes();
            case 3:
                return catFactQuotes();
            default:
                return "No message found";
        }
    }

    public static void Joker(String[] args) {
        try {
            System.out.println(getMsg());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
