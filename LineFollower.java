package main;

import lejos.hardware.Button;
//import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;
import lejos.utility.Delay;

public class LineFollower {
	public static void main(String[] args) {
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);
		SensorMode mode = colorSensor.getRedMode();
		
		LCD.drawString("white", 0, 0);
		Button.ESCAPE.waitForPressAndRelease();
		float[] sampleWhite = new float[1];
		mode.fetchSample(sampleWhite, 0);;
		LCD.drawInt(new Float(sampleWhite[0] * 100).intValue(), 2, 2);
		Delay.msDelay(1000);
		LCD.clear();
		
		Delay.msDelay(1000);
		LCD.drawString("Black", 0, 0);
		Button.ESCAPE.waitForPressAndRelease();
		float[] sampleBlack = new float[1];
		mode.fetchSample(sampleBlack, 0);
		LCD.drawInt(new Float(sampleBlack[0] * 100).intValue(), 2, 2);
		Delay.msDelay(1000);
		LCD.clear();
		Delay.msDelay(2000);
		
		int defaultPower = 20;
		int multiplyingFactor = 100;
		int multipleblack = 25;
		
		//this.motorA = new EV3LargeRegulatedMotor(motorAPort);
		//this.motorB = new EV3LargeRegulatedMotor(motorBPort);

		EV3LargeRegulatedMotor largeMotorkiri = new EV3LargeRegulatedMotor(MotorPort.A);
		EV3LargeRegulatedMotor largeMotorkanan = new EV3LargeRegulatedMotor(MotorPort.D);
		float[] color = new float[1];
		
		while (!Button.ESCAPE.isDown()) {

			float avgLight = (sampleBlack[0] + sampleWhite[0]) / 2;
			mode.fetchSample(color, 0);
			
			LCD.drawString(String.format("White = %.2f", sampleWhite[0] * 100), 0, 0);
			LCD.drawString(String.format("Black = %.2f", sampleBlack[0] * 100), 0, 1);
			LCD.drawString(String.format("AverageLight = %.2f", avgLight), 0, 2);
			
			
			//Backup plan
			if(avgLight < color[0]) {
				largeMotorkiri.stop(true);
				largeMotorkanan.forward();
			}
			else if(avgLight > color[0]) {
				largeMotorkanan.stop(true);
				largeMotorkiri.forward();
			}
			else {
				largeMotorkanan.forward();
				largeMotorkanan.forward();
			}
			
			
//			float cSpeed = defaultPower + multiplyingFactor * (avgLight - color[0])
//					/ ((sampleWhite[0]) - (sampleBlack[0]));
//			largeMotorkiri.setSpeed(new Float(cSpeed).intValue());
//			largeMotorkiri.forward();
//
//			float bSpeed = defaultPower - multiplyingFactor * (avgLight - color[0])
//					/ ((sampleWhite[0]) - (sampleBlack[0]));
//			largeMotorkanan.setSpeed(new Float(bSpeed).intValue());
//			largeMotorkanan.forward();
//			Delay.msDelay(1000);
//			if(cSpeed > bSpeed) {
//				largeMotorkanan.forward();
//			}
			LCD.drawString(String.format("Color = %.2f", color[0]), 0, 3);
			//LCD.drawString(String.format("Speed %.2f %.2f", cSpeed, bSpeed), 0, 4);
		}

	}
}
