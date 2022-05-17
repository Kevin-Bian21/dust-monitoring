package cn.bianwenkai.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * @author BianWenKai
 * @DATE 2022/5/14 - 13:28
 **/
public class GenerateMockData {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\Code\\Web\\dust-monitor\\src\\main\\resources\\mock_data.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 4320; i++) {
            for (int j = 0; j < 10; j++) {
                switch (j) {
                    case 0 : System.out.println("(default,\t"  + "'一号监测点'" + ",\t" +  getDust()  + ",\t" + "'"+ tempDate.format(calendar.getTime()) +"'" + ",\t" + getTemperature()  + ",\t" + getHumidity()  + ",\t" +getWindSpeed()  + "),\t" ); break;
                    case 1 : System.out.println("(default,\t"  + "'二号监测点'" + ",\t" +  getDust()  + ",\t" + "'"+ tempDate.format(calendar.getTime()) +"'" + ",\t" + getTemperature()  + ",\t" + getHumidity()  + ",\t" +getWindSpeed()  + "),\t" ); break;
                    case 2 : System.out.println("(default,\t"  + "'三号监测点'" + ",\t" +  getDust()  + ",\t" + "'"+ tempDate.format(calendar.getTime()) +"'" + ",\t" + getTemperature()  + ",\t" + getHumidity()  + ",\t" +getWindSpeed()  + "),\t" ); break;
                    case 3 : System.out.println("(default,\t"  + "'四号监测点'" + ",\t" +  getDust()  + ",\t" + "'"+ tempDate.format(calendar.getTime()) +"'" + ",\t" + getTemperature()  + ",\t" + getHumidity()  + ",\t" +getWindSpeed()  + "),\t" ); break;
                    case 4 : System.out.println("(default,\t"  + "'五号监测点'" + ",\t" +  getDust()  + ",\t" + "'"+ tempDate.format(calendar.getTime()) +"'" + ",\t" + getTemperature()  + ",\t" + getHumidity()  + ",\t" +getWindSpeed()  + "),\t" ); break;
                    case 5 : System.out.println("(default,\t"  + "'六号监测点'" + ",\t" +  getDust()  + ",\t" + "'"+ tempDate.format(calendar.getTime()) +"'" + ",\t" + getTemperature()  + ",\t" + getHumidity()  + ",\t" +getWindSpeed()  + "),\t" ); break;
                    case 6 : System.out.println("(default,\t"  + "'七号监测点'" + ",\t" +  getDust()  + ",\t" + "'"+ tempDate.format(calendar.getTime()) +"'" + ",\t" + getTemperature()  + ",\t" + getHumidity()  + ",\t" +getWindSpeed()  + "),\t" ); break;
                    case 7 : System.out.println("(default,\t"  + "'八号监测点'" + ",\t" +  getDust()  + ",\t" + "'"+ tempDate.format(calendar.getTime()) +"'" + ",\t" + getTemperature()  + ",\t" + getHumidity()  + ",\t" +getWindSpeed()  + "),\t" ); break;
                    case 8 : System.out.println("(default,\t"  + "'九号监测点'" + ",\t" +  getDust()  + ",\t" + "'"+ tempDate.format(calendar.getTime()) +"'" + ",\t" + getTemperature()  + ",\t" + getHumidity()  + ",\t" +getWindSpeed()  + "),\t" ); break;
                    case 9 : System.out.println("(default,\t"  + "'十号监测点'" + ",\t" +  getDust()  + ",\t" + "'"+ tempDate.format(calendar.getTime()) +"'" + ",\t" + getTemperature()  + ",\t" + getHumidity()  + ",\t" +getWindSpeed()  + "),\t" ); break;
                    default:  break;
                }
            }
            calendar.add(Calendar.SECOND,20);
        }
        fileOutputStream.close();
        printStream.close();
    }

    private static float getDust() {
        Random random = new Random();
        return (float) (Math.round(random.nextFloat()*10000)/100.0);
    }

    private static float getTemperature() {
        Random random = new Random();
        return (float) (Math.round(random.nextFloat()*1000)/20.0 - 5);
    }
    private static float getHumidity() {
        return (float) (Math.round(Math.random() *10000)/100.0);
    }
    private static float getWindSpeed() {
        return (float) (Math.round(Math.random() * 80) / 20.0);
    }
}
