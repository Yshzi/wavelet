import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    ArrayList<String> strArray = new ArrayList<>();
    ArrayList<Integer> index = new ArrayList<>();

    public String handleRequest(URI url) {
        // Cannot get search to work.
        if (url.getPath().equals("/")) {
            return String.format("Andrew's searches:", strArray.toString());
        } else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for (int i = 0; i < strArray.size(); ++i) {
                    if (strArray.get(i).contains(parameters[1])) {
                        index.add(i);
                    }
                }
                String[] returnList = new String[index.size()];
                for (int i = 0; i < index.size(); ++i) {
                    returnList[i] = strArray.get(index.get(i));
                }
                return String.format("Search results:", strArray.toString());
            }
        } else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                strArray.add(parameters[1]);
                return String.format("Search history updated! " + parameters[1]
                        + " was added. Seach history is now: " + strArray.toString());
            }

        }
        return "404 Not Found!";

    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
