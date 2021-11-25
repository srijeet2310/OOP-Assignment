import java.util.*;

public class DNSAuth_TestCase3_Multisocketing
{
    public static void main(String args[])
    {
        Server ser = new Server();
        Client cl1 = new Client("182.0.1.2", ser, "162.0.1.2");
        Client cl2 = new Client("162.0.1.2", ser, "182.0.1.2");
        //Parametrs passed for the client objects are : (Actual IPAddress, Server Object, Spoofed IPAddress)
        /*
         * Note that the Client can have any randomly generated spoofed IPAddress but for 
         * demostration purposes we have choosen to hardcode the spoofed IPAddress such that multi-socket
         * programming can be demostrated in the video. 
         * Multi-socket programming allows multiple clients to request for information from the 
         * same server and their requests are resolved using multi-threading in runtime.
         */
        System.out.println("Client - Server Architecture created");
        cl2.sendRequestPacket("Need Information",0); //Spoofed Request
        cl1.sendRequestPacket("Need Information",0); //spoofed Request
        //SendRequestPacket has parameters : (Message string, 0/1) : 
        //0 stands for correct request and 1 stands for spoofed request
    }
}

/*
output of above code
 
Server has been initialised
Client Registered with Actual IP 	182.0.1.2
Client Registered with Actual IP 	162.0.1.2
Client - Server Architecture created
Request Sent with Source IP-Address 	162.0.1.2
Request Sent with Source IP-Address 	182.0.1.2
Server to Client with IP : 182.0.1.2 Have you sent a request?
Server to Client with IP : 162.0.1.2 Have you sent a request?
Server to Client with IP : 162.0.1.2 Have you sent a request?
Server to Client with IP : 182.0.1.2 Have you sent a request?
Server to Client with IP : 162.0.1.2 Have you sent a request?
Server to Client with IP : 182.0.1.2 Have you sent a request?
Server to Client with IP : 162.0.1.2 Have you sent a request?
Server to Client with IP : 182.0.1.2 Have you sent a request?
Server to Client with IP : 162.0.1.2 Have you sent a request?
Server to Client with IP : 182.0.1.2 Have you sent a request?
Server to Client with IP : 162.0.1.2 Have you sent a request?
Server to Client with IP : 182.0.1.2 Have you sent a request?
Server to Client with IP : 162.0.1.2 Have you sent a request?
Server to Client with IP : 182.0.1.2 Have you sent a request?
Server to Client with IP : 162.0.1.2 Have you sent a request?
Server to Client with IP : 182.0.1.2 Have you sent a request?
Server to Client with IP : 162.0.1.2 Have you sent a request?
Server to Client with IP : 182.0.1.2 Have you sent a request?
Server to Client with IP : 162.0.1.2 Have you sent a request?
Response not sent
Server to Client with IP : 182.0.1.2 Have you sent a request?
Response not sent
 */
