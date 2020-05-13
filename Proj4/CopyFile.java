import java.io.InputStream;

class CopyFile implements Runnable {
   private Thread t;
   private String threadName;
   private InputStream input;
   
   CopyFile( String name, InputStream in) {
      threadName = name;
      input = in;
      System.out.println("Creating " +  threadName );
   }
   
   public void run() {
      System.out.println("Running " +  threadName );

      try {
          
        while (true) {
            int temp = input.read();
            if(temp == -1){break;}
            char tempChar = (char) temp;
            System.out.print(tempChar);
        }

      } catch (Exception e) {
          System.out.println("Read Error in Thread " + threadName);
      }

      try { 
        Thread.sleep(50);
      } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}
/*
public class TestThread {

   public static void main(String args[]) {
      RunnableDemo R1 = new RunnableDemo( "Thread-1");
      R1.start();
      
      RunnableDemo R2 = new RunnableDemo( "Thread-2");
      R2.start();
   }   
}*/