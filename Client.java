import java.util.*;

interface ClientSendInfo
{
    void sendSetupInfo();
    String getIPAddress();

}
interface ClientRequest
{
    String createRequestPacket(String info, int val);
    void sendRequestPacket(String info, int val);
}
interface ServerConfirmation
{
    String validateConfirmation();
    void acknoledgeConfirmation();
}

public class Client implements ClientRequest, ClientSendInfo, ServerConfirmation
{
    private String ipAddress;
    private boolean haveSent; 
        // false for request not sent and true for request sent
    private Server serverObj;
    private String spoofedIp;

    Client(String ipAddress, Server serverObj, String spoofedIp)
    {
        this.ipAddress = ipAddress;
        this.serverObj = serverObj;
        this.spoofedIp = spoofedIp;
        haveSent = false;
        sendSetupInfo();
    }
    ///////////////////////////////////
    public void sendSetupInfo()
    {
        serverObj.setupClient(this);
        System.out.println("Client Registered with Actual IP \t" + ipAddress); 
    }
    public String getIPAddress()
    {
        return ipAddress;
    }
    //////////////////////////////////////////
    public void sendRequestPacket(String info, int val)
    {
        haveSent = true;
        if(val == 0)
            System.out.println("Request Sent with Source IP-Address \t" + ipAddress);
        else
            System.out.println("Request Sent with Source IP-Address \t" + spoofedIp);
            
        serverObj.validateRequest(createRequestPacket(info, val), ipAddress);
    }
    public String createRequestPacket(String info, int val)
    {
        String packet = "";
        if(val == 0)
            packet += spoofedIp + "\n" + info;
        else 
            packet += ipAddress + "\n" + info;
        return packet;
    }
    //////////////////////////////////////////////
    public String validateConfirmation()
    {
        if(haveSent)
        {
            String s = "Yes, I have";
            return s;
        }
        else
            return "";
    }
    public void acknoledgeConfirmation()
    {
        System.out.println("Server - Client Authentication Complete for IP \t" + ipAddress);
        haveSent = false;
    }
}
