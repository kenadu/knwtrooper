/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;
import rxtxrobot.*;
/**
 *
 * @author kenadu
 */
public class Robot {

    final private static int PING_PIN = 13;
    static int bump = 8;
    
    public static void main(String[] args) {
        RXTXRobot r = new ArduinoUno();
        r.setPort("/dev/tty.usbmodem1411");
        r.setVerbose(true);
        r.connect();
        r.attachMotor(RXTXRobot.MOTOR1, 5);
        r.attachMotor(RXTXRobot.MOTOR2, 6);
        r.attachMotor(RXTXRobot.MOTOR3, 8);
        r.attachServo(RXTXRobot.SERVO1, 10);

        
        
        
        
        
        //starts from the Northeastern side facing to the south
        //moving 15'
        moveForward(r, 10000);
        //facing to the west
        rotateClockwise90(r);
        //moving 18'
        moveForward(r, 10000);
        //facing to the north
        rotateClockwise90(r);
        //moving 9'
        moveForward(r, 5000);
        //facing to the west
        rotateCounterClockwise90(r);
        //moving 8'up to the ramp
        moveForward(r, 10000);
        //extend the windprobe
        deployWindProbe(r);
        //get the windspeed
        getAnemometerReading(r);
        //uncovered
        getThermistorReading(r, 1);
        //covered
        getThermistorReading(r, 0);
        //facing to the south
        rotateCounterClockwise90(r);
        //moving 12' off the ramp 
        moveForward(r, 10000);
        //facing the east
        rotateCounterClockwise90(r);
        //moveing 5' 
        moveForward(r, 10000);
        //facing the south
        rotateClockwise90(r);
        //moving 9'close to the sandbox
        moveForward(r, 10000);
        //facing the west
        rotateClockwise90(r);
        //to the sandbox
        moveForward(r, 10000);
        //get the conductivity
        deployConductivityProbe(r);
        //facing the south
        rotateCounterClockwise90(r);

        
        
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
        //Navigation
        // A general outline for our navigation.  If you guys could double check the map and see if this makes sense as well as determine
        //how to move these distances we should be good.

        /*Move South for < 3 feet 
        rotateCounterClockwise90(r);
        Move east for enough time to get onto ramp (test time it takes to ascend ramp)
        Stop Robot
        deployWindProbe(r)
        getAnemometerReading(r);
        rotateClockwise90(r);
        Move south for about 20 feet
        rotateCounterClockwise90(r);
        Move east for 3 feet
        rotateClockwise90(r);
        Move south until ping reads 1 cm away from sandbox (take ping directly after rotation and at cm away to get distance for x)
        Stop robot
        deployConductivityProbe(r);
        rotateCounterClockwise90(r);
        Move east until ping sensor detects middle object
        rotateClockwise90(r);
        Move South for 2 feet
        rotateCounterClockwise90(r);
        Move east for 6 feet
        rotateCounterClockwise90(r);
        Move north until ping sensor detects rock object
        rotateClockwise90(r);
        Move east until ping sensor detects eastern wall
        rotateCounterClockwise90(r);
        Move north until ping sensor detects Northern wall
        Stop
        */
        
        r.close();
    }
    public static void moveForward(RXTXRobot r, int ticks)
    {
        r.runEncodedMotor(RXTXRobot.MOTOR1, 255, 100, RXTXRobot.MOTOR2, 255, 500);  //need to adjust for specific robot
    }
    public static void rotateClockwise90(RXTXRobot r) //Please adjust accordingly
     {
         r.runMotor(RXTXRobot.MOTOR1, 300, RXTXRobot.MOTOR2, 0, 0);
         r.sleep(500);
     }
     public static void rotateCounterClockwise90(RXTXRobot r) //Please adjust accordingly
     {
         r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 300, 0);
         r.sleep(500);
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
    public static void deployWindProbe(RXTXRobot r) //Method for extending wind probe
    {
        r.runMotor(RXTXRobot.MOTOR3, -100, 0);
        r.sleep(90000);
    }
    public static void deployConductivityProbe(RXTXRobot r) //Method for deploying conductivity probe
    {
        r.moveServo(RXTXRobot.SERVO1, 180);
        r.sleep(15000);
        r.moveServo(RXTXRobot.SERVO1, 45);
        r.sleep(2000);
        int conduct = r.getConductivity();
        System.out.println("Conductivity probe read" + conduct + ".");
        r.sleep(20000);
    }
    
}
