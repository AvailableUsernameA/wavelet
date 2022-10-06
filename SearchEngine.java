import java.io.IOException;
import java.net.URI;

class HandlerStr implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String Str = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("String: %d", Str);  
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {   //is this path contains /add
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) { 
                    Str += parameters[1]; 
                    return String.format("String increased by %s! It's now %d", parameters[1], Str);   
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