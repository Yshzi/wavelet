import java.io.IOException;
import java.net.URI;
import java.Utility.ArrayList;

class Handler1 implements URLHandler {
    ArrayList<String> strArray = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.out.print("Andrew's searches:", strArray.toString());
        } else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for (int i = 0; i < strArray.size(); ++i) {
                    if (strArray[i].contains(parameters[1])) {
                        ArrayList<Integer> index = new ArrayList<>();
                        index.add(i);
                    }
                }
                String[] returnList = new String[index.size()];
                for (int i = 0; i < index.size(); ++i) {
                    returnList[i] = strArray.get(index[i]);
                }
                return String.out.print(returnList.toString());
            }
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
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
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port1 = Integer.parseInt(args[0]);

        Server.start(port1, new Handler1());
    }
}
