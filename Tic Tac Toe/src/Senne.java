import lejos.hardware.Button;
import lejos.hardware.lcd.*;
import lejos.hardware.motor.Motor;

public class Senne {
	public static void main(String[] args) {
		
	LCD.drawString("test",0,4);
	Motor.A.forward();
	Button.waitForAnyPress();
	
	
	}    
}