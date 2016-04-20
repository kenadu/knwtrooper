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
        
        
         //Obstacle -> Ramp -> Sanbox -> Soccer ball -> back to Obstacle quadrant
        r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 370, 0);
        r.sleep(12000);
        rotateCounterClockwise90(r);
        r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 375, 0);
        r.sleep(8000);
        rotateClockwise90(r);
        ramp(r);
        sandbox(r);
        moveBackward(r, 300);
        rotateClockwise90(r);
                r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0);
        r.sleep(10000);
        
                r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 375, 0);
        r.sleep(4500);
        rotateCounterClockwise90(r);
                        r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 375, 0);
        r.sleep(18000);
        //

        
        /*
        //Sandbox -> Soccer ball -> Obstacle -> ramp -> back to sandbox quadrant
        =================================
        r.runMotor(RXTXRobot.MOTOR1, -480, RXTXRobot.MOTOR2, 330, 0);
        r.sleep(5000);
        rotateCounterClockwise90(r);
        sandbox(r);
        moveBackward(r, 300);
        rotateClockwise90(r);
                r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0);
        r.sleep(10000);
        
                r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 375, 0);
        r.sleep(4500);
        rotateCounterClockwise90(r);
                        r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 375, 0);
        r.sleep(18000);
                rotateCounterClockwise90(r);
                                        r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 375, 0);
        r.sleep(5000);
        rotateClockwise90(r);
                                r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 375, 0);
        r.sleep(5500);
        rotateCounterClockwise90(r);
        ramp(r);
        deployWindProbe(r);
        retractWindProbe(r);
        rotateCounterClockwise90(r);
                                r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 375, 0);
        r.sleep(18000);
        rotateClockwise90(r);
        =============================================
        */
        
        
        
        
        
        
        //Upper Left Quadrant
        upperLeftQuadrant(r);
        //Upper Right Quadrant
        upperRightQuadrant(r);
        //Lower Left Quadrant
        lowerLeftQuadrant(r);
        //Lower Right Quadrant
        lowerRightQuadrant(r);
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
    while (distance > traveled)
        {
            if (pingMeasurement(r) <= 10)
            {   
                r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
                int reping = pingMeasurement(r);
                if (reping > 10)
                {
                    r.runMotor(RXTXRobot.MOTOR1, -470, RXTXRobot.MOTOR2, 360, 0); //Move west
                    r.sleep(270);
                    traveled += 20;
                }
                else
                {
                    while (pingMeasurement(r) <= 10)
                    {
                        rotateClockwise90(r);
                        r.runMotor(RXTXRobot.MOTOR1, -470, RXTXRobot.MOTOR2, 360, 0); 
                        r.sleep(500);
                        rotateCounterClockwise90(r);
                    }
                    r.runMotor(RXTXRobot.MOTOR1, -470, RXTXRobot.MOTOR2, 360, 0); 
                    r.sleep(270);
                    rotateCounterClockwise90(r);
                    r.runMotor(RXTXRobot.MOTOR1, -470, RXTXRobot.MOTOR2, 360, 0);
                    r.sleep(500);
                    traveled += 20;
                }
                
            }
            else
            {
                r.runMotor(RXTXRobot.MOTOR1, -470, RXTXRobot.MOTOR2, 360, 0); //Move west
                r.sleep(270);
                traveled += 20;
            }            
        }   
    }
        public static void moveBackward(RXTXRobot r, int distance)
    {
        
        r.runMotor(RXTXRobot.MOTOR1, 470, RXTXRobot.MOTOR2, -400, 0);
        r.sleep(270);
    }
    public static void rotateClockwise90(RXTXRobot r)
     {
        r.runMotor(RXTXRobot.MOTOR1, 240, RXTXRobot.MOTOR2, 200, 0);
        r.sleep(1800);
     }
     public static void rotateCounterClockwise90(RXTXRobot r)
     {
        r.runMotor(RXTXRobot.MOTOR1, -240, RXTXRobot.MOTOR2, -200, 0);
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
        r.runMotor(RXTXRobot.MOTOR3, 200, 0);
        r.sleep(35000);
    }
    public static void retractWindProbe(RXTXRobot r)
    {
        r.runMotor(RXTXRobot.MOTOR3, -200, 0);
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
    public static void sandbox(RXTXRobot r)
    {
        boolean atSandbox = false;
        while (atSandbox == false) //Go towards sandbox
        {
            moveForward(r, 10);
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
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0);
        r.sleep(12000);
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
        deployWindProbe(r); 
        double windSpeed = getAnemometerReading(r);
        System.out.println("Windspeed equals " + windSpeed + ".");
    }
    public static void avoidRock(RXTXRobot r)
    {
        boolean atRock = false;
        while (atRock == false) //Go towards sandbox
        {
            moveForward(r, 10);
            if (pingMeasurement(r) <= 7)
            {
                r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
                atRock = true;
                break;
            }   
        }
        rotateClockwise90(r);
        moveForward(r, 100);
        rotateCounterClockwise90(r);
        moveForward(r, 100);
    }
    public static void upperLeftQuadrant(RXTXRobot r)
    {
        int xdistance = 0;
        int ydistance = 0;
        moveForward(r, 100);
        ydistance += 100;
        rotateCounterClockwise90(r);
        while (pingMeasurement(r) > 10)
        {
            r.runMotor(RXTXRobot.MOTOR1, -470, RXTXRobot.MOTOR2, 360, 0); 
            r.sleep(10);
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
            moveForward(r, 20);
            xdistance += 20;
        }
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0); 
        r.sleep(10000);
        rotateCounterClockwise90(r);
        moveForward(r, 100);
        avoidRock(r);
    }
    public static void upperRightQuadrant(RXTXRobot r)
    {
        int xdistance = 0;
        int ydistance = 0;
        moveForward(r, 100);
        rotateClockwise90(r);
        moveForward(r, 100);
        rotateCounterClockwise90(r);
        avoidRock(r);
        rotateClockwise90(r);
        moveForward(r, 200);
        while (pingMeasurement(r) > 10)
        {
            moveForward(r, 20);
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
            moveForward(r, 20);
            xdistance += 20;
        }
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0); 
        r.sleep(10000);
    }
    public static void lowerLeftQuadrant(RXTXRobot r)
    {
        int xdistance = 0;
        int ydistance = 0;
        moveForward(r, 100);
        rotateClockwise90(r);
        sandbox(r);
        moveBackward(r, 10);
        rotateCounterClockwise90(r);
        moveForward(r, 60);
        rotateClockwise90(r);
        while (xdistance < 400)
        {
            moveForward(r, 20);
            xdistance += 20;
        }
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0); 
        r.sleep(10000);
        rotateCounterClockwise90(r);
        moveForward(r, 100);
        avoidRock(r);
        rotateClockwise90(r);
        moveForward(r, 200);
        while (pingMeasurement(r) > 10)
        {
            moveForward(r, 20);
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
        moveForward(r, 400);
        rotateCounterClockwise90(r);
        moveForward(r, 200);
        rotateClockwise90(r);
        moveForward(r, 500);
        avoidRock(r);
        rotateClockwise90(r);
        moveForward(r, 200);
        while (pingMeasurement(r) > 10)
        {
            moveForward(r, 20);
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
            moveForward(r, 20);
            xdistance += 20;
        }
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0); 
        r.sleep(10000);
    }
        
}
