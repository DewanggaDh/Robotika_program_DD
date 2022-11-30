package main;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;
import lejos.robotics.ColorIdentifier;
import java.util.ArrayList;

public class testtremaux2 {
	RegulatedMotor motorA;
	RegulatedMotor motorD;
	EV3ColorSensor eyesensor;
	EV3UltrasonicSensor earsensor;
	float[] sample;
	
	public void moveForward() {
		RegulatedMotor[] motors = {this.motorD};
		for (int i = 0; i <= 614; i++) {
			this.motorA.synchronizeWith(motors);
			this.motorA.startSynchronization();
			this.motorA.rotate(614);
			this.motorD.rotate(614);
			this.motorA.endSynchronization();
		}
		
	}
	
	public void moveBackward() {
		//Delay.msDelay(delay);
		RegulatedMotor[] motors = {this.motorD};
		for (int i = 0; i <= 614; i++) {
			this.motorA.synchronizeWith(motors);
			this.motorA.startSynchronization();
			this.motorA.rotate(-614);
			this.motorD.rotate(-614);
			this.motorA.endSynchronization();
		}
	}
	
	public void moveLeft() {
//		this.motorA.rotate(-180);
//		this.motorD.rotate(180);
		//Delay.msDelay(delay);
		RegulatedMotor[] motors = {this.motorD};
		for (int i = 0; i <= 180; i++) {
			this.motorA.synchronizeWith(motors);
			this.motorA.startSynchronization();
			this.motorA.rotate(-180);
			this.motorD.rotate(180);
			this.motorA.endSynchronization();
		}
	}
	
	public void moveRight() {
		
		RegulatedMotor[] motors = {this.motorD};
		for (int i = 0; i <= 180; i++) {
			this.motorA.synchronizeWith(motors);
			this.motorA.startSynchronization();
			this.motorA.rotate(180);
			this.motorD.rotate(180);
			this.motorA.endSynchronization();
		}
		//Delay.msDelay(delay);
	}
	
	public testtremaux2(Port motorAPort, Port motorDPort) {
		this.motorA = new EV3LargeRegulatedMotor(motorAPort);
		this.motorD = new EV3LargeRegulatedMotor(motorDPort);
		//sensor = new EV3ColorSensor(port);
		//setAmbientMode();
		//setFloodLight(false);
	}
	public static int inside_maze(int y_1, int x_1) {
		if(y_1 < 0 || y_1 > 0) {
			return 0;
		}
		if(x_1 < 0 || x_1 > 0) {
			return 0;
		}
		return 1;
	}
//	public int junction() {
//		this.moveLeft();
//		if
//		return 0;
//	}
	public static void main(String args[]) {
		int x = 0, y = 0;
		testtremaux2 mC = new testtremaux2(MotorPort.A, MotorPort.D);
		EV3UltrasonicSensor soundSensor = new EV3UltrasonicSensor(SensorPort.S1);
		SampleProvider mode = soundSensor.getDistanceMode();
		while(true) {
			LCD.drawString(String.format("Lebar Maze : %d", x), 0, 0);
			Button.UP.waitForPress();
			x += 1;
			if (x > 10) {
				x = 0;
			}
			if(Button.ESCAPE.isDown()) {
				break;
			}
			
			LCD.clear();
		}
		while(true) {
			LCD.drawString(String.format("Panjang Maze : %d", y), 0, 0);
			Button.UP.waitForPress();
			y += 1;
			if (y > 10) {
				y = 0;
			}
			if(Button.ESCAPE.isDown()) {
				break;
			}
			LCD.clear();
		}
		int[][] array_maze = new int[y][x];
//		int[][] array_maze = {{0, 0, 0, 0, 0},
//		             {0, 0, 0, 0, 0},
//		             {0, 0, 0, 0, 0},
//		             {0, 0, 0, 0, 0},
//		             {0, 0, 0, 0, 0}};
		            		 
		int prev_pos_x = -1;
		int prev_pos_y = -1;
		int input_x = 0;
		int input_y = 0;
		while(true) {
			
			if (input_x >= x - 1) {
				input_x = 0;
			}
			LCD.drawString(String.format("Posisi x awal : %d", input_x), 0, 0);
			Button.UP.waitForPressAndRelease();
			input_x += 1;
			if(Button.ESCAPE.isDown()) {
				break;
			}
			LCD.clear();
		}
		while(true) {
			
			if (input_y >= y - 1) {
				input_y = 0;
			}
			LCD.drawString(String.format("Posisi y awal : %d", input_y), 0, 0);
			Button.UP.waitForPressAndRelease();
			input_y += 1;
			if(Button.ESCAPE.isDown()) {
				break;
			}
			LCD.clear();
		}
		int[] starting_point = {input_y, input_x};
		String[] posisi = {"Utara", "Timur", "Selatan", "Barat"};
		int posisi_awal = 0;
		while(true) {
			
			
			if (posisi_awal >= 4) {
				posisi_awal = 0;
			}
			LCD.drawString("Arah awal : ", 0, 0);
			LCD.drawString(String.format("%s", posisi[posisi_awal]), 0, 2);
			Button.UP.waitForPressAndRelease();
			posisi_awal += 1;
			if(Button.ESCAPE.isDown()) {
				break;
			}
			LCD.clear();
		}
		
		//test motor
//		mC.moveForward();
//		mC.moveBackward();
//		mC.moveLeft();
//		mC.moveRight();
		int tempe = inside_maze(starting_point[0], starting_point[1]);
		EV3LargeRegulatedMotor largeMotorkiri = new EV3LargeRegulatedMotor(MotorPort.A);
		EV3LargeRegulatedMotor largeMotorkanan = new EV3LargeRegulatedMotor(MotorPort.D);
		
		while(inside_maze(starting_point[0], starting_point[1]) != 0){
			if
		}
//			if(junction() == True) {
//				if(forward != wall || forward != mark) {
//					if position(N, E, S, W) {
//						starting_point[0] +- 1
//						starting_point[1] +- 1
//					}
//					mark(next_point (starting_point +- 1))
//					if prev_pos != null{
//						mark(prev_pos)
//					}
//					move forward
//				}
//				elif(left != wall || left != mark){
//					rotate to left
//					if position (change position)
//					mark
//				}
//				elif(right) (you know the drill)
//				else //blocked
//				{
//					rotate 180 degrees
//					mark bigger
//				}
//			}
//			else {
//				move forward
//			}
//		
//		}
	}
}
