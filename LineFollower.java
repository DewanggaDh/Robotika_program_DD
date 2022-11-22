package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class LineFollower {
	private float[] fetchColor(SensorMode mode, String type) {
		LCD.drawString(type, 0, 0);
		Button.ESCAPE.waitForPressAndRelease();
		float[] sampleColor = new float[1];
		mode.fetchSample(sampleColor, 0);
		LCD.drawInt(new Float(sampleColor[0] * 100).intValue(), 2, 2);
		Delay.msDelay(1000);
		LCD.clear();
		return sampleColor;
	}

	private void tremaux() {
		LCD.drawString("tremaux");
	}

	private void followTheLine(
			Float[] sampleBlack,
			Float[] sampleWhite,
			EV3LargeRegulatedMotor largeMotorkiri,
			EV3LargeRegulatedMotor largeMotorkanan) {
		float avgLight = (sampleBlack[0] + sampleWhite[0]) / 2;
		mode.fetchSample(color, 0);

		LCD.drawString(String.format("White = %.2f", sampleWhite[0] * 100), 0, 0);
		LCD.drawString(String.format("Black = %.2f", sampleBlack[0] * 100), 0, 1);
		LCD.drawString(String.format("AverageLight = %.2f", avgLight), 0, 2);

		// Backup plan
		if (avgLight < color[0]) {
			largeMotorkiri.stop(true);
			largeMotorkanan.forward();
		} else if (avgLight > color[0]) {
			largeMotorkanan.stop(true);
			largeMotorkiri.forward();
		} else {
			largeMotorkanan.forward();
			largeMotorkanan.forward();
		}

		LCD.drawString(String.format("Color = %.2f", color[0]), 0, 3);
	}

	public static void main(String[] args) {
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);
		SensorMode mode = colorSensor.getRedMode();

		float[] sampleWhite = fetchColor(mode, "white");
		float[] sampleBlack = fetchColor(mode, "black");

		Delay.msDelay(2000);

		int defaultPower = 20;
		int multiplyingFactor = 100;
		int multipleblack = 25;

		EV3LargeRegulatedMotor largeMotorkiri = new EV3LargeRegulatedMotor(MotorPort.A);
		EV3LargeRegulatedMotor largeMotorkanan = new EV3LargeRegulatedMotor(MotorPort.D);
		float[] color = new float[1];

		bool isLineFollowing = true;

		while (!Button.ESCAPE.isDown()) {
			if (isLineFollowing)
				followTheLine(sampleBlack, sampleWhite, largeMotorkiri, largeMotorkanan);
			else if (!isLineFollowing)
				tremaux();
		}

	}
}
