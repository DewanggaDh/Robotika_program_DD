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
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3GyroSensor;
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
	EV3TouchSensor button;
	EV3GyroSensor arah;
	
	SampleProvider range;
	SampleProvider touch;
	SampleProvider empat;
	
	public void moveForward() {
		//RegulatedMotor[] motors = {this.motorD};
//		for (int i = 0; i <= 307; i++) {
//			this.motorA.synchronizeWith(motors);
//			this.motorA.startSynchronization();
//			this.motorA.rotate(307);
//			this.motorD.rotate(307);
//			this.motorA.endSynchronization();
//		}
		this.motorA.forward();
		this.motorD.forward();
		
	}
	
	public void moveBackward() {
		//Delay.msDelay(delay);
		RegulatedMotor[] motors = {this.motorD};
		for (int i = 0; i <= 307; i++) {
			this.motorA.synchronizeWith(motors);
			this.motorA.startSynchronization();
			this.motorA.rotate(-307);
			this.motorD.rotate(-307);
			this.motorA.endSynchronization();
		}
	}
	

	
	public testtremaux2(Port motorAPort, Port motorDPort, Port sensor1Port, Port sensor2Port, Port sensor3Port) {
		this.motorA = new EV3LargeRegulatedMotor(motorAPort);
		this.motorD = new EV3LargeRegulatedMotor(motorDPort);
		this.earsensor = new EV3UltrasonicSensor(sensor1Port);
		this.button = new EV3TouchSensor(sensor2Port);
		this.arah = new EV3GyroSensor(sensor3Port);
		//sensor = new EV3ColorSensor(port);
		//setAmbientMode();
		//setFloodLight(false);
	}
	public static int inside_maze(int y_1, int x_1, int y, int x) {
		if(y_1 < 0 || y_1 > y) {
			return 0;
		}
		if(x_1 < 0 || x_1 > x) {
			return 0;
		}
		return 1;
	}
	public float check_sound() {
		
		range = earsensor.getDistanceMode();
		float[] sample = new float[range.sampleSize()];
		range.fetchSample(sample, 0);
		return (float)sample[0];
	}
	public int junction() {
		this.moveLeft();
		int count = 0;
		range = earsensor.getDistanceMode();
		float[] sample = new float[range.sampleSize()];
		range.fetchSample(sample, 0);
		if((int)sample[0] <= 0.05) {
			count += 1;
		}
		this.moveRight();
		this.moveRight();
		range = earsensor.getDistanceMode();
		//float[] sample = new float[range.sampleSize()];
		range.fetchSample(sample, 0);
		if((int)sample[0] <= 0.05) {
			count += 1;
		}
		this.moveLeft();
//		range = earsensor.getDistanceMode();
//		//float[] sample = new float[range.sampleSize()];
//		range.fetchSample(sample, 0);
//		if((int)sample[0] <= 0.05) {
//			count += 1;
//		}
		return count;
	}
	public int sentuh() {
		touch = button.getTouchMode();
		float[] touching = new float[touch.sampleSize()];
		touch.fetchSample(touching,0);
		return (int)touching[0];
	}
	public int kompas() {
		empat = arah.getAngleMode();
		float[] four = new float[touch.sampleSize()];
		touch.fetchSample(four, 0);
		return (int)four[0];
	}
	public void moveRight() {
//		this.motorA.rotate(-180);
//		this.motorD.rotate(180);
		//Delay.msDelay(delay);
//		RegulatedMotor[] motors = {this.motorD};
//		for (int i = 0; i <= 90; i++) {
//			this.motorA.synchronizeWith(motors);
//			this.motorA.startSynchronization();
//			this.motorA.rotate(-90);
//			this.motorD.rotate(90);
//			this.motorA.endSynchronization();
//		}
		while(kompas() < 270) {
			this.motorA.backward();
			this.motorD.forward();
		}
		arah.reset();
	}
	
	public void moveLeft() {
		
//		RegulatedMotor[] motors = {this.motorD};
//		for (int i = 0; i <= 90; i++) {
//			this.motorA.synchronizeWith(motors);
//			this.motorA.startSynchronization();
//			this.motorA.rotate(90);
//			this.motorD.rotate(-90);
//			this.motorA.endSynchronization();
//		}
		//Delay.msDelay(delay);
		while(kompas() < 270) {
			this.motorA.forward();
			this.motorD.backward();
		}
		arah.reset();
	}
	public static void main(String args[]) {
		int x = 0, y = 0;
		testtremaux2 mC = new testtremaux2(MotorPort.A, MotorPort.D, SensorPort.S1, SensorPort.S2, SensorPort.S3);
		//EV3UltrasonicSensor soundSensor = new EV3UltrasonicSensor(SensorPort.S1);
		//SampleProvider mode = soundSensor.getDistanceMode();
//		while(true) {
//			LCD.drawString(String.format("Lebar Maze : %d", x), 0, 0);
//			Button.UP.waitForPress();
//			x += 1;
//			if (x > 10) {
//				x = 0;
//			}
//			if(Button.ESCAPE.isDown()) {
//				break;
//			}
//			
//			LCD.clear();
//		}
//		while(true) {
//			LCD.drawString(String.format("Panjang Maze : %d", y), 0, 0);
//			Button.UP.waitForPress();
//			y += 1;
//			if (y > 10) {
//				y = 0;
//			}
//			if(Button.ESCAPE.isDown()) {
//				break;
//			}
//			LCD.clear();
//		}
		//int[][] array_maze = new int[y][x];
		int[][] array_maze = 	{{0, 0, 0, 0, 0},
								{1, 1, 1, 1, 0},
								{0, 0, 0, 0, 0},
								{0, 1, 1, 1, 0},
								{0, 0, 0, 1, 0}};
		            		 
		int prev_pos_x = -1;
		int prev_pos_y = -1;
		int input_x = 0;
		int input_y = 0;
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
		float[] color = new float[1];
		//while(!Button.ESCAPE.isDown()) {
			
//			if (input_x >= x - 1) {
//				input_x = 0;
//			}
//			LCD.drawString(String.format("Posisi x awal : %d", input_x), 0, 0);
//			Button.UP.waitForPressAndRelease();
//			input_x += 1;
//			if(Button.ESCAPE.isDown()) {
//				break;
//			}
//			LCD.clear();
//		}
//		while(true) {
//			
//			if (input_y >= y - 1) {
//				input_y = 0;
//			}
//			LCD.drawString(String.format("Posisi y awal : %d", input_y), 0, 0);
//			Button.UP.waitForPressAndRelease();
//			input_y += 1;
//			if(Button.ESCAPE.isDown()) {
//				break;
//			}
//			LCD.clear();
//		}
//		int[] starting_point = {input_y, input_x};
//		String[] posisi = {"Utara", "Timur", "Selatan", "Barat"};
//		int posisi_awal = 0;
//		while(true) {
//			
//			
//			if (posisi_awal >= 4) {
//				posisi_awal = 0;
//			}
//			LCD.drawString("Arah awal : ", 0, 0);
//			LCD.drawString(String.format("%s", posisi[posisi_awal]), 0, 2);
//			Button.UP.waitForPressAndRelease();
//			posisi_awal += 1;
//			if(Button.ESCAPE.isDown()) {
//				break;
//			}
//			LCD.clear();
//		}
		//testtremaux2 mc = new testtremaux2(MotorPort.A, MotorPort.D);
		
//		float suara = mC.check_sound();
//		LCD.drawString(String.format("Jarak (m) = %.2f", suara), 0, 0);
//		if(suara <= 0.05) {
//			mC.moveLeft();
//		}
//		else {
//			mC.moveForward();
//		}
		//mC.moveRight();
		//mC.moveLeft();
//		RegulatedMotor[] motors = {mC.motorD};
//		for (int i = 0; i <= 90; i++) {
//			mC.motorA.synchronizeWith(motors);
//			mC.motorA.startSynchronization();
//			mC.motorA.rotate(-90);
//			mC.motorD.rotate(90);
//			mC.motorA.endSynchronization();
//		}
//		for (int i = 0; i <= 90; i++) {
//			mC.motorA.synchronizeWith(motors);
//			mC.motorA.startSynchronization();
//			mC.motorA.rotate(90);
//			mC.motorD.rotate(-90);
//			mC.motorA.endSynchronization();
//		}
		//test motor
//		while(mC.check_sound() >= 0.1) {
//			mC.moveForward();
//		}
		//mC.moveBackward();
		//mC.moveLeft();
		//mC.moveRight();
		//int tempe = inside_maze(starting_point[0], starting_point[1], y, x);
		
		
		//while(inside_maze(starting_point[0], starting_point[1], y, x) != 0){
		//EV3GyroSensor arah = new EV3GyroSensor(SensorPort.S3);
		//SampleProvider empat = arah.getAngleMode();
		//float[] four = new float[empat.sampleSize()];
		
		
		
		while(mC.sentuh() != 1) {
			//empat.fetchSample(four, 0);
			//LCD.drawString(String.format("White = %.2f",  four[0]), 0, 0);
			float avgLight = (sampleBlack[0] + sampleWhite[0]) / 2;
			mode.fetchSample(color, 0);
			
			LCD.drawString(String.format("White = %.2f", sampleWhite[0] * 100), 0, 0);
			LCD.drawString(String.format("Black = %.2f", sampleBlack[0] * 100), 0, 1);
			LCD.drawString(String.format("AverageLight = %.2f", avgLight), 0, 2);
			
			
			//Backup plan
			if(avgLight < color[0]) {
				mC.motorA.stop(true);
				mC.motorD.forward();
			}
			else if(avgLight > color[0]) {
				mC.motorA.stop(true);
				mC.motorD.forward();
			}
			else {
				mC.motorA.forward();
				mC.motorD.forward();
			}
			//empat.fetchSample(four, 0);
		}
			
		while(!Button.ESCAPE.isDown()) {
			mC.motorA.forward();
			mC.motorD.forward();
			if(mC.check_sound() <= 0.05) {
				mC.moveLeft();
				if(mC.check_sound() <= 0.05) {
					mC.moveRight();
					mC.moveRight();
					if(mC.check_sound() <= 0.05) {
						mC.moveRight();
					}
				}
			}
//			//if far wall jalan
//			//if near wall, stop
//			//move left
//			//check
//			//if wall
//				//move to right 2x
//				//if wall
//					//moev right
//					//move forward once
//				//else
//					//move
//			//else
//				//move
//			
		}
		
//		while(!Button.DOWN.isDown()){
//			if(mC.junction() != 0) {
//				mC.moveLeft();
//				if(mC.check_sound() <= 0.05) {
//					mC.moveRight();
//					if(mC.check_sound() <= 0.05) {
//						mC.moveRight();
//						if(mC.check_sound() <= 0.05) {
//							mC.moveLeft();
//							mC.moveBackward();
//							break;
//						}
//						else {
//							mC.moveForward();
//						}
//					}
//					else {
//						mC.moveForward();
//					}
//				}
//				else {
//					mC.moveForward();
//				}
//				
//			}
//			else {
//				mC.moveForward();
//			}
		
		
			//bonker method
//			if(mC.check_sound() <= 0.05) {
//				mC.moveLeft();
//				if(mC.check_sound() <= 0.05) {
//					mC.moveRight();
//					mC.moveRight();
//					if(mC.check_sound() <= 0.05) {
//						mC.moveLeft();
//						mC.moveBackward();
//					}
//				}
//			}
//			else {
//				mC.moveForward();
//			}
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
//}
