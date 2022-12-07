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

public class LineFollowerWithTremaux {
    RegulatedMotor motorA;
    RegulatedMotor motorD;
    EV3ColorSensor eyesensor;
    EV3UltrasonicSensor earsensor;
    EV3TouchSensor button;
    EV3GyroSensor arah;

    SampleProvider range;
    SampleProvider touch;
    SampleProvider empat;

    public static void main(String args[]) {
        int x = 0, y = 0;
        LineFollowerWithTremaux mC = new LineFollowerWithTremaux(MotorPort.A, MotorPort.D, SensorPort.S1, SensorPort.S2, SensorPort.S3);
        int[][] array_maze = {{0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}};

        LineFollower lineFollower = new LineFollower();
        while (mC.sentuh() != 1) {
            lineFollower.run();
        }
        LCD.clear();

        while (!Button.ESCAPE.isDown()) {
            mC.moveForward();
            if (mC.check_sound() <= 0.05) {
                mC.moveLeft();
                mC.motorA.stop();
                mC.motorD.stop();
                if (mC.check_sound() <= 0.05) {
                    mC.moveRight();
                    mC.motorA.stop();
                    mC.motorA.stop();
                    mC.moveRight();
                    mC.motorA.stop();
                    mC.motorA.stop();
                    if (mC.check_sound() <= 0.05) {
                        mC.moveRight();
                    }
                }
            }

        }

    }


    public void moveForward() {
        RegulatedMotor[] motors = {this.motorD};
        //for (int i = 0; i <= 307; i++) {
        this.motorA.synchronizeWith(motors);
        this.motorA.startSynchronization();
        this.motorA.rotate(307);
        this.motorD.rotate(307);
        this.motorA.endSynchronization();
        //this.motorA.forward();
        //this.motorD.forward();

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


    public LineFollowerWithTremaux(Port motorAPort, Port motorDPort, Port sensor1Port, Port sensor2Port, Port sensor3Port) {
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
        if (y_1 < 0 || y_1 > y) {
            return 0;
        }
        if (x_1 < 0 || x_1 > x) {
            return 0;
        }
        return 1;
    }

    public float check_sound() {

        range = earsensor.getDistanceMode();
        float[] sample = new float[range.sampleSize()];
        range.fetchSample(sample, 0);
        return (float) sample[0];
    }

    public int junction() {
        this.moveLeft();
        int count = 0;
        range = earsensor.getDistanceMode();
        float[] sample = new float[range.sampleSize()];
        range.fetchSample(sample, 0);
        if ((int) sample[0] <= 0.05) {
            count += 1;
        }
        this.moveRight();
        this.moveRight();
        range = earsensor.getDistanceMode();
        //float[] sample = new float[range.sampleSize()];
        range.fetchSample(sample, 0);
        if ((int) sample[0] <= 0.05) {
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
        touch.fetchSample(touching, 0);
        return (int) touching[0];
    }

    public float kompas() {
        empat = arah.getAngleMode();
        float[] four = new float[empat.sampleSize()];
        empat.fetchSample(four, 0);
        LCD.drawString(String.format("Derajat : %.2f", (float) four[0]), 0, 0);
        return (float) four[0];
    }

    public void moveRight() {
        while (kompas() > -90) {
            //this.motorA.backward();
//			this.motorD.forward();
            this.motorA.setSpeed(90);
            this.motorD.setSpeed(90);
            this.motorA.backward();
            this.motorD.forward();
        }
        //this.motorA.stop();
        //this.motorD.stop();
        arah.reset();
    }

    public void moveLeft() {
        while (kompas() < 90) {
            this.motorA.setSpeed(90);
            this.motorD.setSpeed(90);
            this.motorA.forward();
            this.motorD.backward();
        }
        //this.motorA.stop();
        //this.motorD.stop();
        arah.reset();
    }
}

public class LineFollower {
    public LineFollower() {

        int prev_pos_x = -1;
        int prev_pos_y = -1;
        int input_x = 0;
        int input_y = 0;
        EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);
        SensorMode mode = colorSensor.getRedMode();

        LCD.drawString("white", 0, 0);
        Button.ESCAPE.waitForPressAndRelease();
        float[] sampleWhite = new float[1];
        mode.fetchSample(sampleWhite, 0);
        ;
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

    }

    public void run() {
        //empat.fetchSample(four, 0);
        //LCD.drawString(String.format("White = %.2f",  four[0]), 0, 0);
        float avgLight = (sampleBlack[0] + sampleWhite[0]) / 2;
        mode.fetchSample(color, 0);

        LCD.drawString(String.format("White = %.2f", sampleWhite[0] * 100), 0, 0);
        LCD.drawString(String.format("Black = %.2f", sampleBlack[0] * 100), 0, 1);
        LCD.drawString(String.format("AverageLight = %.2f", avgLight), 0, 2);


        //Backup plan
        if (avgLight < color[0]) {
            mC.motorA.stop(true);
            mC.motorD.forward();
        } else if (avgLight > color[0]) {
            mC.motorA.stop(true);
            mC.motorD.forward();
        } else {
            mC.motorA.forward();
            mC.motorD.forward();
        }
        //empat.fetchSample(four, 0);
    }
}
