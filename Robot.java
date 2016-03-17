/*3
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;
import rxtxrobot.*;
/**
 *
 * @author Maya
 */

public class Robot {

    final private static int PING_PIN = 13;
    static int bump = 8;
    
    public static void main(String[] args) {
        RXTXRobot r = new ArduinoUno();
        r.setPort("COM3");
        r.setVerbose(true);
        r.connect();
        r.attachMotor(RXTXRobot.MOTOR1, 5);
        r.attachMotor(RXTXRobot.MOTOR2, 4);
        r.attachServo(RXTXRobot.SERVO1, 9);
        
        
        /*Bump Sensor
        r.refreshAnalogPins();
        int reading = r.getAnalogPin(2).getValue();
        while (reading > 800)
        {
            r.runMotor(RXTXRobot.MOTOR1, 250, RXTXRobot.MOTOR2, -340, 0);
            reading = r.getAnalogPin(2).getValue();
            System.out.println(reading);
            r.refreshAnalogPins();
        } */
        //3 Meter Movement
        int distanceTraveled = 0;
        
        while (distanceTraveled <= 300)
        {
            moveForward(r, 100); //need to modify moveForward function to take in a distance and move that much
            if (pingMeasurement(r) < 30)
            {
                //avoidance
                avoidObstacle(r);
            }
            distanceTraveled += 10;
        }
       
        /*Servo Movement
        r.moveServo(RXTXRobot.SERVO1, 130);
        r.sleep(10000);
        */
        
        //Thermistor
        //double thermistorReading = getThermistorReading(r, 0);
        //System.out.println("The probe read the value: " + thermistorReading);
        //System.out.println("In volts: " + (thermistorReading * (5.0/1023.0))); 
        //Wind Speed
        //double windspeed = getAnemometerReading(r);
        
        r.close();
    }
    public static void moveForward(RXTXRobot r, int time)
    {
        r.runEncodedMotor(RXTXRobot.MOTOR1, 100, ticks);  //need to adjust for specific robot
    }
+    public static void rotateClockwise90(RXTXRobot r)
 +    {
 +        r.runMotor(RXTXRobot.MOTOR1, 300, RXTXRobot.MOTOR2, 0, 0);
 +        r.sleep(500);
 +    }
 +    public static void rotateCounterClockwise90(RXTXRobot r)
 +    {
 +        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 300, 0);
 +        r.sleep(500);
 +    }
    public static int pingMeasurement(RXTXRobot r)
    {
        int ping = 0;
        ping = r.getPing(PING_PIN);
        return ping;
    }

    public static void avoidObstacle(RXTXRobot r)
    {
    	rotateClockwise90(r);
	moveForward(r,30);
	rotateCounterClockwise90(r);
	moveForward(r,30);
	rotateCounterClockwise90(r);
	moveForward(r,30);
	rotateClockwise90(r);
    }
    public static double convertTemperature(double code)
    {
        double temp = (code-826.71)/(-9.90970853);
        return temp;
    }
    public static double getThermistorReading(RXTXRobot r, int pin) 
    {
    double sum = 0;
    int readingCount = 10;

    //Read the analog pin values ten times, adding to sum each time
    for (int i = 0; i < readingCount; i++) {

    //Refresh the analog pins so we get new readings
    r.refreshAnalogPins();
    double reading = r.getAnalogPin(pin).getValue();
    reading = convertTemperature(reading);
    sum += reading;
    }
    //Return the average reading
    System.out.println(sum/readingCount);
    return sum / readingCount;
    }
    public static double getAnemometerReading(RXTXRobot r)
    {
        r.refreshAnalogPins();
       double outsideTemp = getThermistorReading(r, 1);
       System.out.println("Outside temperature is "+outsideTemp+" degrees Celsius");
       double insideTemp = getThermistorReading(r, 0);
       System.out.println("Inside temperature is "+insideTemp+" degrees Celsius");
       double windSpeed = 0;
       
       return windSpeed;
    }
    
}
