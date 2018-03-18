import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.*;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;
import java.util.Scanner;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class kine {
	public static void main(String[] args) {
		int [] position= {0,0};
		int [] destination= {2,6};
		position = moveWidth(position,destination);
		position = moveLength(position,destination);
		
		public static int [] moveWidth(int [] start ; int [] end) {
			//geometry field
			int width = 1;
			int radW = 1;
			int angleW = width/radW;
			//control motor
			RegulatedMotor motorWidth = new EV3LargeRegulatedMotor(MotorPort.A);
			int angleRotW = (end[0]-start[0])*angleW;
			motorWidth.rotate(angleRotW);
			start[0]= end[0];
			return start;
		}
		
		public static int [] moveLength(int [] start ; int [] end) {
			//geometry of field
			int length = 1;
			int radL = 1;
			int angleL = length/radL;
			
			RegulatedMotor motorLength = new EV3LargeRegulatedMotor(MotorPort.B);
			int angleRotL = (end[1]-start[1])*angleL;
			motorLength.rotate(angleRotL);
			start[1]= end[1];
			return start;
		}
		
		
	}
}
