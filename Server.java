import java.util.*;

interface SendClientInfo
{
    void setupClient(Client clientObj);
    boolean isIPPresent(String IPAddress);
}
interface ServerResponse
{
    Client findClient(String packet);
    String packetInfo(String packet);
    void validateRequest(String packet, String clientIp);
} 
public class Server implements SendClientInfo, ServerResponse
{
    private HashMap<String, Client> hmap;
    Server()
    {
        hmap = new HashMap<>();
        hmap.clear();
        System.out.println("Server has been initialised");
    }
    ///////////////////////////////////////
    public void setupClient(Client clientObj)
    {
        hmap.put(clientObj.getIPAddress(), clientObj);
    }
    public boolean isIPPresent(String IPAddress)
    {
        return hmap.containsKey(IPAddress);
    }
    //////////////////////////////////
    public Client findClient(String packet)
    {
        String IPAddress = packet.substring(0, packet.indexOf("\n"));
        if(!hmap.containsKey(IPAddress))
            return null;
        
        return hmap.get(IPAddress);
    }
    public String packetInfo(String packet)
    {
        return packet.substring(packet.indexOf("\n")+1);
    }
    public void validateRequest(String packet, String clientIP)
    {
        Client cl = findClient(packet);
        String info = packetInfo(packet);
        ValidationThread th = new ValidationThread(cl, info, clientIP);
        Thread obj = new Thread(th);
        obj.start();
    }
}
