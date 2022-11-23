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

public class testtremaux2 {
	public int inside_maze(int y_1, int x_1) {
		if(y_1 < 0 || y_1 > 0) {
			return 0;
		}
		if(x_1 < 0 || x_1 > 0) {
			return 0;
		}
		return 1;
	}
	public static void main(String args[]) {
		int x = 0, y = 0;
		while(!Button.ESCAPE.isDown()) {
			LCD.drawString(String.format("Lebar Maze : %d", x), 0, 0);
			Button.UP.waitForPressAndRelease();
			x += 1;
			Delay.msDelay(1000);
			LCD.clear();
		}
		while(!Button.ESCAPE.isDown()) {
			LCD.drawString(String.format("Panjang Maze : %d", y), 0, 0);
			Button.UP.waitForPressAndRelease();
			y += 1;
			Delay.msDelay(1000);
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
		while(!Button.ESCAPE.isDown()) {
			LCD.drawString(String.format("Posisi x awal : %d", input_x), 0, 0);
			Button.UP.waitForPressAndRelease();
			input_x += 1;
			if (input_x > x) {
				input_x = 0;
			}
			Delay.msDelay(1000);
			LCD.clear();
		}
		while(!Button.ESCAPE.isDown()) {
			LCD.drawString(String.format("Posisi x awal : %d", input_y), 0, 0);
			Button.UP.waitForPressAndRelease();
			input_y += 1;
			if (input_y > y) {
				input_y = 0;
			}
			Delay.msDelay(1000);
			LCD.clear();
		}
		int[] starting_point = {input_y, input_x};
		String[] posisi = {"Utara", "Timur", "Selatan", "Barat"};
		int posisi_awal = 0;
		while(!Button.ESCAPE.isDown()) {
			LCD.drawString(String.format("Posisi x awal : %s", posisi[posisi_awal]), 0, 0);
			Button.UP.waitForPressAndRelease();
			posisi_awal += 1;
			if (posisi_awal > 4) {
				posisi_awal = 0;
			}
			Delay.msDelay(1000);
			LCD.clear();
		}
		
		while(inside_maze(starting_point[0], starting_point[1]) != 0) {
			if(junction() == True) {
				if(forward != wall || forward != mark) {
					if position(N, E, S, W) {
						starting_point[0] +- 1
						starting_point[1] +- 1
					}
					mark(next_point (starting_point +- 1))
					if prev_pos != null{
						mark(prev_pos)
					}
					move forward
				}
				elif(left != wall || left != mark){
					rotate to left
					if position (change position)
					mark
				}
				elif(right) (you know the drill)
				else //blocked
				{
					rotate 180 degrees
					mark bigger
				}
		}
			else {
				move forward
			}
		
	}
}
}
