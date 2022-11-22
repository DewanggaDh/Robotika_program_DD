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
	int array = [[0, 0, 0, 0, 0],
	             [0, 0, 0, 0, 0],
	             [0, 0, 0, 0, 0],
	             [0, 0, 0, 0, 0],
	             [0, 0, 0, 0, 0]]
	            		 
	prev_pos_x = placeholder_x
	prev_pos_y = placeholder_y
	starting_point = [input_x, input_y]
	position = [input_position]
	
	while(inside a maze) {
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
