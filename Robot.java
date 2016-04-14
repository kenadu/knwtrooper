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

    final private static int PING_PIN = 7;
    static int bump = 8;
    
    public static void main(String[] args) {
        RXTXRobot r = new ArduinoUno();
        r.setPort("COM3");
        r.setVerbose(true);
        r.connect();
        r.attachMotor(RXTXRobot.MOTOR1, 5);
        r.attachMotor(RXTXRobot.MOTOR2, 6);
        r.attachMotor(RXTXRobot.MOTOR3, 9);
        r.attachServo(RXTXRobot.SERVO1, 10);
        /*
        Upper Left Corner
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0);
        r.sleep(7300);
        rotateClockwise90(r);
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0);
        r.sleep(12000);
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
        deployWindProbe(r);
        double windSpeed = getAnemometerReading(r);
        System.out.println("Windspeed equals " + windSpeed + ".");
        rotateCounterClockwise90(r);
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0);
        r.sleep(20000);
        */
        /* Sandbox code
        boolean atSandbox = false;
        while (atSandbox == false)
        {
            moveForward(r, 10);
            if (pingMeasurement(r) <= 7)
            {
                r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
                atSandbox = true;
                break;
            }
        } 
        deployConductivityProbe(r);
        */
        //r.runMotor(RXTXRobot.MOTOR1, -400, RXTXRobot.MOTOR2, 270, 0);
        //r.sleep(6000);
        //rotateClockwise90(r);
        //r.runMotor(RXTXRobot.MOTOR1, -380, RXTXRobot.MOTOR2, 270, 0);
        //r.sleep(10000);
        //r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
        /*deployWindProbe(r);
        double windSpeed = getAnemometerReading(r);
        System.out.println("Windspeed equals " + windSpeed + ".");
        */
        /*Bump Sensor
        r.refreshAnalogPins();
        int reading = r.getAnalogPin(2).getValue();
        while (reading > 800)
        {
            r.runMotor(RXTXRobot.MOTOR1, 250, RXTXRobot.MOTOR2, -340, 0);
            reading = r.getAnalogPin(2).getValue();
            System.out.println(reading);
            r.refreshAnalogPins();
        } 
        */
        r.close();
    }
    public static void moveForward(RXTXRobot r, int distance)
    {
        
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 400, 0);
        r.sleep(13);
    }
    public static void rotateClockwise90(RXTXRobot r)
     {
        r.runMotor(RXTXRobot.MOTOR1, 220, RXTXRobot.MOTOR2, 200, 0);
        r.sleep(1800);
     }
     public static void rotateCounterClockwise90(RXTXRobot r)
     {
        r.runMotor(RXTXRobot.MOTOR1, -220, RXTXRobot.MOTOR2, -200, 0);
        r.sleep(1800);
     }
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
    public static double convertConductivity(double code)
    {
        double conduct = (code-1093.6)/(-3958.2);
        return conduct;
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
       double tempDifference;
       double windSpeed = 0;
       tempDifference = insideTemp - outsideTemp;
       windSpeed = Math.pow(tempDifference, 2);
       windSpeed *= .1068;
       windSpeed -= (.112*tempDifference);
       windSpeed += .1085;
       
       return windSpeed;
    }
    public static void deployWindProbe(RXTXRobot r)
    {
        r.runMotor(RXTXRobot.MOTOR3, -200, 0);
        r.sleep(35000);
    }
    public static void retractWindProbe(RXTXRobot r)
    {
        r.runMotor(RXTXRobot.MOTOR3, 200, 0);
        r.sleep(24000);
    }
    public static void deployConductivityProbe(RXTXRobot r)
    {
        r.moveServo(RXTXRobot.SERVO1, 160);
        r.sleep(15000);
        r.moveServo(RXTXRobot.SERVO1, 45);
        r.sleep(2000);
        double conduct = r.getConductivity();
        conduct = convertConductivity(conduct);
        System.out.println("Conductivity probe read" + conduct + ".");
        r.sleep(20000);
    }
    
}
