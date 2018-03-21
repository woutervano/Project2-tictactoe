
public class MyRunnable implements Runnable {
	
    private int var;

    public MyRunnable() {

    }

    public void run() {
    	
        while(!Thread.interrupted()) {
    		try {
    			Thread.sleep(5000);
    			System.out.println("thread 1" +  Thread.currentThread().getName());
    			
    			
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}   
    		   
         }
        // code in the other thread, can reference "var" variable
    }

}
