package main;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;
import lejos.robotics.ColorIdentifier;
import java.util.ArrayList;


public class motoring implements ColorDetector, ColorIdentifier{

	RegulatedMotor motorA;
	RegulatedMotor motorB;
	EV3ColorSensor sensor;
	float[] sample;
	
	public motoring(Port motorAPort, Port motorBPort) {
		this.motorA = new EV3LargeRegulatedMotor(motorAPort);
		this.motorB = new EV3LargeRegulatedMotor(motorBPort);
		//sensor = new EV3ColorSensor(port);
		setAmbientMode();
		//setFloodLight(false);
	}
	
	
	public EV3ColorSensor getSensor() {
		return sensor;
	}
	
	public void setAmbientMode()
	{
		sensor.setCurrentMode("Ambient");
		sample = new float[sensor.sampleSize()];
	}
	
	public void setRedMode() {
		sensor.setCurrentMode("Red");
		sample = new float[sensor.sampleSize()];
	}
	
	public void setColorIdMode() {
		sensor.setCurrentMode("ColorID");
		sample = new float[sensor.sampleSize()];
	}
	
	public void setRGBMode() {
		sensor.setCurrentMode("RGB");
		sample = new float[sensor.sampleSize()];
	}
	
	public int getColorID()
	{
		sensor.fetchSample(sample, 0);
		return (int)sample[0];
	}
	
	public Color getColor() {
		sensor.fetchSample(sample, 0);
		return new Color((int)(sample[0]*255), (int)(sample[1]*255), (int)(sample[2]*255));
	}
	
	public float getAmbient() {
		sensor.fetchSample(sample,0);
		return sample[0];
	}
	
	public float getRed() {
		sensor.fetchSample(sample, 0);
		return sample[0];
	}
	
	public void close() {
		sensor.close();
	}
	
	
	public void stop() {
		this.motorA.stop();
		this.motorB.stop();
	}
	
	public void moveForward(int delay) {
		this.motorA.forward();
		this.motorB.forward();
		Delay.msDelay(delay);
	}
	
	public void moveBackward(int delay) {
		this.motorA.backward();
		this.motorB.backward();
		Delay.msDelay(delay);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		motoring mC = new motoring(MotorPort.A, MotorPort.D);
		while(true) {
//			mC.moveForward(30);
//			Delay.msDelay(10);
			if(Button.ESCAPE.isDown()) {
				mC.stop();
				break;
			}
			if(Button.DOWN.isDown()) {
				mC.moveBackward(30);
				Delay.msDelay(10);
			}
			if(Button.UP.isDown()) {
				mC.moveForward(30);
				Delay.msDelay(10);
			}
		}
//	public static String colorName(int color) {
//		switch (color)
//		{
//			case Color.NONE:
//				return "None";
//				
//			case Color.BLACK:
//				return "Black";
//				
//			case Color.BLUE:
//				return "Blue";
//				
//			case Color.BROWN:
//				return "Brown";
//				
//			case Color.CYAN:
//				return "Cyan";
//				
//			case Color.DARK_GRAY:
//				return "Dark Gray";
//				
//			case Color.GRAY:
//				return "Gray";
//				
//			case Color.GREEN:
//				return "Green";
//				
//			case Color.LIGHT_GRAY:
//				return "Light Gray";
//				
//			case Color.MAGENTA:
//				return "Magenta";
//				
//			case Color.ORANGE:
//				return "Orange";
//				
//			case Color.PINK:
//				return "Pink";
//				
//			case Color.RED:
//				return "Red";
//				
//			case Color.WHITE:
//				return "White";
//				
//			case Color.YELLOW:
//				return "Yellow";
//		}
//		
//		return "";
	}
	

}
