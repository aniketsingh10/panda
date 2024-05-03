import java.io.*;
import java.util.*;
public class Berkley
{

    float diff(int h, int m, int s, int nh, int nm, int ns)// Method to calculate the time difference between two given times
    {
        int dh = h-nh;
        int dm = m-nm;
        int ds = s-ns;
        int diff = (dh*60*60)+(dm*60)+ds;//Calculate the total difference in seconds
        return diff;
    }

    float average(float diff[], int n){
        int sum=0;
        for(int i=0; i<n; i++)
        {
            sum+=diff[i];
        }
        float average = (float)sum/(n+1);
        System.out.println("The average of all time differences is "+average);
        return average;
    }

    void sync(float diff[], int n, int h, int m, int s, int nh[], int nm[], int ns[], float average)//Method to synchronize clocks
    {
        for(int i=0;i<n;i++)
        {
            diff[i]+=average;//Add average time difference to each time difference
            int dh=(int)diff[i]/(60*60); // Calculate the number of hours in the adjusted time difference
            diff[i]%=(60*60);// Store the remaining seconds after subtracting hours
            int dm=(int)diff[i]/60;// Calculate the number of minutes in the adjusted time difference
            diff[i]%=60;// Store the remaining seconds after subtracting minutes
            int ds=(int)diff[i];// Calculate the remaining seconds in the adjusted time difference

            nh[i]+=dh;    // Update the hours of the node's time
            if(nh[i]>23)// Ensure the hour value stays within the range of 0 to 23
            {
                nh[i]%=24;
            }

            nm[i]+=dm;   // Update the minutes of the node's time
            if(nm[i]>59)
            {
                nh[i]++;
                nm[i]%=60;
            }

            ns[i]+=ds; // Update the seconds of the node's time
            if(ns[i]>59)
            {
                nm[i]++;
                ns[i]%=60;
            }

            if(ns[i]<0)  // Adjust the minute and second values if seconds are negative
            {
                nm[i]--;
                ns[i]+=60;
            }
        }

        h+=(int)(average/(60*60));// Update the time server's hour by adding the average time difference (in hours)
        if(h>23)
        { 
            h%=24;
        }
        m+=(int)(average/(60*60*60)); // Update the time server's minute by adding the remaining average time difference (in minutes)

        if(m>59)
        {
            h++;
            m%=60;
        }
        s+=(int)(average%(60*60*60)); // Update the time server's second by adding the remaining average time difference (in seconds)

        if(s>59)
        {
            m++;s%=60;
        }

        if(s<0)
        {
            m--;
            s+=60;
        }

        System.out.println("The synchronized clocks are:\nTime Server ---> "+h+" : "+m+" : "+s);
        for(int i=0;i<n;i++)
        {
        System.out.println("Node "+(i+1)+" ---> "+nh[i]+" : "+nm[i]+" : "+ns[i]);
        }
    }

public static void main(String[] args) throws IOException {
    Berkley b = new Berkley();
    Date date = new Date(); // Getting the current date and time
    BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));// Creating a BufferedReader object to read user input
    System.out.println("Enter number of nodes:");
    int n = Integer.parseInt(obj.readLine()); 
    int h = date.getHours(); // Getting the current hour from the system time
    int m = date.getMinutes();//Getting the current minute from the system time
    int s = date.getSeconds();// Getting the current second from the system time

    int nh[] = new int[n];// Declaring arrays to store hours, minutes, and seconds for each node
    int nm[] = new int[n];
    int ns[] = new int[n];
    for(int i=0; i<n; i++)
    {
        System.out.println("Enter time for node "+(i+1)+"\n Hours:");
        nh[i]=Integer.parseInt(obj.readLine());
        System.out.println("Minutes:");
        nm[i]=Integer.parseInt(obj.readLine());
        System.out.println("Seconds:");
        ns[i]=Integer.parseInt(obj.readLine());
    }
    for(int i=0; i<n; i++)// Sending the current time from the time server to each node
    {
        System.out.println("Time Server sent time "+h+" : "+m+" : "+s+" to node "+(i+1));
    }
    float diff[] = new float[n];

    for(int i=0;i<n;i++)
    {
        diff[i] = b.diff(h,m,s,nh[i],nm[i],ns[i]);// Calculating the time difference for the current node
        System.out.println("Node "+(i+1)+" sent time difference of "+(int)diff[i]+" to Time Server.");
    }
    float average = b.average(diff,n);
    b.sync(diff, n, h, m, s, nh, nm, ns, average);//Synchronizing the clocks of each node and the time server
    }
}