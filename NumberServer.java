import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Number: %d", num);  //If the path of url is /, then num doesn't change
        } else if (url.getPath().equals("/increment")) {
            num += 1;   //If the path of url is /increment, then 1 is added to num
            return String.format("Number incremented!");
        } else {
            System.out.println("Path: " + url.getPath());   //If the path is neither / nor /increment, then print out the path
            if (url.getPath().contains("/add")) {   //is this path contains /add
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("count")) {  //then is the query of the url has count at the front
                    num += Integer.parseInt(parameters[1]);   //then add the number after count= to num
                    return String.format("Number increased by %s! It's now %d", parameters[1], num);   
                    //return the number added and the total numcer
                }
            }
            return "404 Not Found!";
            //If the path is not / or /increment and deson't contain /add, then return 404 not found.
        }
    }
}

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            //Check that the input can't be empty
            return;
        }

        int port = Integer.parseInt(args[0]);  //Get the port from the first input and turns it into integer

        Server.start(port, new Handler());   //It calls Server.start method.
    }
}
