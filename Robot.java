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
        
        int xdistance = 0; //First Quadrant = 0, Second Quadrant = 1160, Third Quadrant = 0 , Fourth Quadrant = 1160
        int ydistance = 0; //First Quadrant = 0, Second Quadrant = 0, Third Quadrant = 1380, Fourth Quadrant = 1380
        
        
        /*Bump Sensor
        r.refreshAnalogPins();
        int reading = r.getAnalogPin(2).getValue();
        while (reading > 800)
        {
            r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 400, 0);
            reading = r.getAnalogPin(2).getValue();
            r.refreshAnalogPins();
        }
        */
        r.close();
    }
    public static void moveForward(RXTXRobot r)
    {
        
        r.runMotor(RXTXRobot.MOTOR1, -470, RXTXRobot.MOTOR2, 400, 0);
        
    }
    public static void moveBackward(RXTXRobot r, int distance)
    {
        
        r.runMotor(RXTXRobot.MOTOR1, 470, RXTXRobot.MOTOR2, -400, 0);
        r.sleep(270);
    }
    public static void rotateClockwise90(RXTXRobot r)
     {
        r.runMotor(RXTXRobot.MOTOR1, 260, RXTXRobot.MOTOR2, 220, 0);
        r.sleep(1800);
     }
     public static void rotateCounterClockwise90(RXTXRobot r)
     {
        r.runMotor(RXTXRobot.MOTOR1, -280, RXTXRobot.MOTOR2, -200, 0);
        r.sleep(1800);
     }
    public static int pingMeasurement(RXTXRobot r)
    {
        int ping = 0;
        ping = r.getPing(PING_PIN);
        return ping;
    }
    public static void searchLeft(RXTXRobot r)
    {
        boolean objectDetected = false;
        while (objectDetected == false)
        {
            moveForward(r);
            r.sleep(2000);
            rotateCounterClockwise90(r);
            r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
            if (pingMeasurement(r) < 40)
            {
                objectDetected = true;
                break;
            }
            else
            {
                rotateClockwise90(r);
            }
        }
       
        
    }
    public static void searchRight(RXTXRobot r)
    {
        boolean objectDetected = false;
        while (objectDetected == false)
        {
            moveForward(r);
            r.sleep(2000);
            rotateCounterClockwise90(r);
            r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
            if (pingMeasurement(r) < 40)
            {
                objectDetected = true;
                break;
            }
            else
            {
                rotateClockwise90(r);
            }
        }
       
        
    }
    public static double convertTemperature(double code, int pin)
    {
        double temp = 0;
        temp = (code-3116.5)/(-8.3865);
        temp -= 273.5;
        return temp;
    }
    public static double convertConductivity(double code)
    {
        double conduct = (code-1038.5)/(-19.394);
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
    reading = convertTemperature(reading, pin);
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
       windSpeed *= -.297;
       windSpeed -= (2.5387*tempDifference);
       windSpeed += .0075;
       
       return windSpeed;
    }
    public static void deployWindProbe(RXTXRobot r)
    {
        r.runMotor(RXTXRobot.MOTOR3, 200, 0);
        r.sleep(50000);
    }
    public static void retractWindProbe(RXTXRobot r)
    {
        r.runMotor(RXTXRobot.MOTOR3, -200, 0);
        r.sleep(30000);
    }
    public static void deployConductivityProbe(RXTXRobot r)
    {
        r.moveServo(RXTXRobot.SERVO1, 160);
        r.sleep(5000);
        double conduct = r.getConductivity();
        conduct = convertConductivity(conduct);
        System.out.println("Conductivity probe read" + conduct + ".");
        r.sleep(20000);
        r.moveServo(RXTXRobot.SERVO1, 45);
        r.sleep(2000);
    
 
        }
    public static void sandbox(RXTXRobot r)
    {
        boolean atSandbox = false;
        while (atSandbox == false) //Go towards sandbox
        {
            r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 300, 0);
            r.sleep(500);
            if (pingMeasurement(r) <= 7)
            {
                r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
                atSandbox = true;
                break;
            }   
        }
        deployConductivityProbe(r); //Get conductivity
    }
    public static void ramp(RXTXRobot r)
    {
        boolean atRamp = false;
        while (atRamp == false) //Go towards sandbox
        {
            moveForward(r);
            r.sleep(1000);
            r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
            if (pingMeasurement(r) <= 7)
            {
                r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
                atRamp = true;
                break;
            }   
        }
        moveForward(r);
        r.sleep(2000);
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
        deployWindProbe(r);
        r.runMotor(RXTXRobot.MOTOR3, 0,0);
        double windSpeed = getAnemometerReading(r);
        System.out.println("Windspeed equals " + windSpeed + ".");
        retractWindProbe(r);
        r.runMotor(RXTXRobot.MOTOR3, 0,0);
    }
    public static void avoidRock(RXTXRobot r)
    {
        boolean atRock = false;
        while (atRock == false) //Go towards sandbox
        {
            moveForward(r);
            r.sleep(1000);
            r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
            if (pingMeasurement(r) <= 7)
            {
                r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
                atRock = true;
                break;
            }   
        }
        moveBackward(r, 10);
        rotateClockwise90(r);
        moveForward(r);
        r.sleep(2000);
        rotateCounterClockwise90(r);
        moveForward(r);
        r.sleep(2000);
    }
    public static void upperLeftQuadrant(RXTXRobot r)
    {
        searchRight(r);
        ramp(r);
        moveForward(r);
        r.sleep(20000);
        sandbox(r);
        moveBackward(r, 10);
        rotateCounterClockwise90(r);
        boolean atWall = false;
        while (atWall == false)
        {
            moveForward(r);
            r.sleep(500);
            if (pingMeasurement(r) < 15)
            {
                atWall = true;
                break;
            }
        }
        rotateClockwise90(r);
        moveForward(r);
        r.sleep(10000);
        rotateCounterClockwise90(r);
        r.sleep(20000);
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
        r.sleep(10000);
        rotateCounterClockwise90(r);
        avoidRock(r);
    }
    public static void upperRightQuadrant(RXTXRobot r)
    {
        searchLeft(r);
        avoidRock(r);
        moveForward(r);
        r.sleep(10000);
        rotateCounterClockwise90(r);
        searchLeft(r);
        ramp(r);
        rotateCounterClockwise90(r);
        moveForward(r);
        r.sleep(20000);
        sandbox(r);
        moveBackward(r, 10);
        rotateCounterClockwise90(r);
        boolean atWall = false;
        while (atWall == false)
        {
            moveForward(r);
            r.sleep(500);
            if (pingMeasurement(r) < 15)
            {
                atWall = true;
                break;
            }
        }
        rotateClockwise90(r);
        moveForward(r);
        r.sleep(10000);
        rotateCounterClockwise90(r);
        r.sleep(10000);
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
        r.sleep(10000);
    }
    public static void lowerLeftQuadrant(RXTXRobot r)
    {
        int xdistance = 0;
        int ydistance = 0;
        searchLeft(r);
        sandbox(r);
        moveBackward(r, 10);
        rotateClockwise90(r);
        boolean atWall = false;
        while (atWall == false)
        {
            moveForward(r);
            r.sleep(500);
            if (pingMeasurement(r) < 15)
            {
                atWall = true;
                break;
            }
        }
        rotateCounterClockwise90(r);
        r.sleep(10000);
        rotateClockwise90(r);
        r.sleep(20000);
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
        r.sleep(10000);
        rotateCounterClockwise90(r);
        avoidRock(r);
        rotateClockwise90(r);
        moveForward(r);
        while (pingMeasurement(r) > 10)
        {
            moveForward(r);
        }
        ramp(r);
        rotateCounterClockwise90(r);
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0);
        r.sleep(2000);
    }
    public static void lowerRightQuadrant(RXTXRobot r)
    {
        int xdistance = 0;
        int ydistance = 0;
        moveForward(r); 
        rotateCounterClockwise90(r);
        moveForward(r); 
        rotateClockwise90(r);
        moveForward(r);
        avoidRock(r);
        rotateClockwise90(r);
        moveForward(r);
        while (pingMeasurement(r) > 10)
        {
            moveForward(r);
        }
        ramp(r);
        rotateCounterClockwise90(r);
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0);
        r.sleep(2000);
        sandbox(r);
        moveBackward(r, 10);
        rotateCounterClockwise90(r);
        while (xdistance < 300)
        {
            moveForward(r);
            xdistance += 20;
        }
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0); 
        r.sleep(10000);
    }       
}
