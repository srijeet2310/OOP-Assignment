import java.util.*;

public class ValidationThread implements Runnable
{
    Client cl;
    String info;
    String actualIP; 
    ValidationThread(Client cl, String info, String actualIP)
    {
        this.cl = cl;
        this.info = info;
        this.actualIP = actualIP;
    }

    @Override
    public void run()
    {
        int i;
        for(i = 0; i < 10; i++)
        {
            try
            {
                Thread.sleep(500);
                if(cl == null)
                {
                    System.out.println("No client found with IP Address matching request");
                    break;
                }
                    
                //if(!actualIP.equals(cl.getIPAddress()))
                    //continue;
                
                System.out.println("Server to Client with IP : " + cl.getIPAddress() + " Have you sent a request?");
                String s = cl.validateConfirmation();
                String st = "Yes, I have";
                if(s.equals(st))
                {
                    System.out.println("Client with IP : " + cl.getIPAddress() + " has sent a request to server : \t" + info);
                    System.out.println("Response sent to IP : \t" + cl.getIPAddress());
                    cl.acknoledgeConfirmation();
                    break;
                }

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }    
        if(i == 10)
            System.out.println("Response not sent");
    }
}

