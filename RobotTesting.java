/*3
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robottest;
import rxtxrobot.*;
/**
 *
 * @author Maya
 */


public class RobotTest {

    final private static int PING_PIN = 7;
    static int bump = 8;
    
    public static void main(String[] args) {
        RXTXRobot r = new ArduinoUno();
        r.setPort("/dev/tty.usbmodem1411");
        r.setVerbose(true);
        r.connect();
        r.attachMotor(RXTXRobot.MOTOR1, 5);
        r.attachMotor(RXTXRobot.MOTOR2, 6);
        r.attachMotor(RXTXRobot.MOTOR3, 9);
        r.attachServo(RXTXRobot.SERVO1, 10);
        
        
        
        
        r.runMotor(RXTXRobot.MOTOR1, -455, RXTXRobot.MOTOR2, 400, 0);
       r.sleep(6300);
       rotateClockwise90(r);
       ramp(r);
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
        r.sleep(2000);
                r.runMotor(RXTXRobot.MOTOR3, 0, 0);
                   r.sleep(2000);
                    

       retractWindProbe(r);
        
       
        rotateClockwise90(r);
       moveBackward(r, 6000);
       rotateCounterClockwise90(r);
       r.runMotor(RXTXRobot.MOTOR1, -455, RXTXRobot.MOTOR2, 400, 0);
        r.sleep(6000);
                rotateClockwise90(r);
                r.runMotor(RXTXRobot.MOTOR1, -455, RXTXRobot.MOTOR2, 400, 0);
        r.sleep(3000);
                rotateCounterClockwise90(r);
sandbox(r);
               moveBackward(r, 6000);

        rotateCounterClockwise90(r);
                        r.runMotor(RXTXRobot.MOTOR1, -455, RXTXRobot.MOTOR2, 400, 0);
        r.sleep(11000);
                rotateCounterClockwise90(r);
                                        r.runMotor(RXTXRobot.MOTOR1, -455, RXTXRobot.MOTOR2, 400, 0);
        r.sleep(8000);
        rotateCounterClockwise90(r);
                                r.runMotor(RXTXRobot.MOTOR1, -455, RXTXRobot.MOTOR2, 400, 0);
        r.sleep(10000);
        rotateClockwise90(r);
        ramp(r);
                        rotateCounterClockwise90(r);
                                r.runMotor(RXTXRobot.MOTOR1, -455, RXTXRobot.MOTOR2, 400, 0);
        r.sleep(5000);
            r.close();
    





       
      
        
        
        //Obstacle -> Ramp -> Sanbox -> Soccer ball -> back to Obstacle quadrant
        /*r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 370, 0);
       r.sleep(2000);
        /*
        
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
        
        
        
        /*
        r.runMotor(RXTXRobot.MOTOR1, -480, RXTXRobot.MOTOR2, 330, 0);
        r.sleep(5000);
        rotateCounterClockwise90(r);
        sandbox(r);
        moveBackward(r, 30);
        rotateClockwise90(r);
                r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0);
        r.sleep(10000);*/
        
        //ramp(r);
        //retractWindProbe(r);

        
        
        //Upper Left Quadrant
        /*
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0);
        r.sleep(7300);
        rotateClockwise90(r);
        ramp(r);
        sandbox(r);
        moveBackward(r, 10);
        rotateCounterClockwise90(r);
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0); //Move for a little bit
        r.sleep(?);
        rotateClockwise90(r);
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0); //Move into solar charging region
        r.sleep(?)
        */
        /*Upper Right Quadrant
        //Face the robot to the West
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0); //Move west
        r.sleep(8000);
        rotateCounterClockwise90(r); //Rotate
        while (pingMeasurement(r) < 20)
        {
            moveForward(r, 10);
            if (pingMeasurement(r) <= 20)
            {
                r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
                rotateClockwise90(r);
                break;
            }
        } 
        r.runMotor(RXTXRobot.MOTOR1, -500, RXTXRobot.MOTOR2, 360, 0); //Move west
        r.sleep(8000);
        //Position robot to line up with ramp
        ramp(r);
        sandbox(r);
        */
        /*Lower Left Quadrant
        //Move to line up with sandbox
        sandbox(r);
        moveBackward(r, 10);
        rotateClockwise90(r);
        //Move to Ramp Quadrant
        ramp(r);
        //Move to rock quadrant
        //Avoid rock
        //Move to solar quadrant
        */
        /*Lower Right Quadrant
        //Move to Sandbox
        sandbox(r);
        //Move to ramp
        ramp(r);
        //Move to rock
        //Avoid Rock
        */
        
        r.close();
    }
    public static void moveForward(RXTXRobot r, int distance)
    {
        
        r.runMotor(RXTXRobot.MOTOR1, -455, RXTXRobot.MOTOR2, 420, 0);
        r.sleep(13);
    }
        public static void moveBackward(RXTXRobot r, int distance)
    {
        
        r.runMotor(RXTXRobot.MOTOR1, 205, RXTXRobot.MOTOR2, -150, 0);
        r.sleep(7000);
    }
    public static void rotateClockwise90(RXTXRobot r)
     {
        r.runMotor(RXTXRobot.MOTOR1, 290, RXTXRobot.MOTOR2, 200, 0);
        r.sleep(3000);
     }
     public static void rotateCounterClockwise90(RXTXRobot r)
     {
        r.runMotor(RXTXRobot.MOTOR1, -270, RXTXRobot.MOTOR2, -200, 0);
        r.sleep(2700);
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
        r.sleep(29500);
    }
    public static void retractWindProbe(RXTXRobot r)
    {
        r.runMotor(RXTXRobot.MOTOR3, 200, 0);
        r.sleep(20000);
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
        r.sleep(3000);
    
 
        }
    public static void sandbox(RXTXRobot r)
    {
        boolean atSandbox = false;
        while (atSandbox == false) //Go towards sandbox
        {
            moveForward(r, 10);
            if (pingMeasurement(r) <= 3)
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
        r.runMotor(RXTXRobot.MOTOR1, -455, RXTXRobot.MOTOR2, 400, 0);
        r.sleep(11000);
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
        r.sleep(2000);
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
            if (pingMeasurement(r) <= 30)
            {
                r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
                atRock = true;
                break;
            }   
        }
        moveBackward(r, 3000);
        rotateClockwise90(r);
        r.runMotor(RXTXRobot.MOTOR1, -450, RXTXRobot.MOTOR2, 410, 0);
       r.sleep(3000);
       rotateCounterClockwise90(r);
       

    }
        
        
       
       
}



