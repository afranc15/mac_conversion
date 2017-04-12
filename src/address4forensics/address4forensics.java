import java.io.*;
import java.util.Scanner;

public class address4forensics {

    public static void main(String[] args) {
         if(args.length != 0){

            if(args[0].equalsIgnoreCase("-L")){
                logicaladdress(args);
            }else if(args[0].equalsIgnoreCase("-P")){
                physicaladdress(args);
            }else if (args[0].equalsIgnoreCase("-C")){
                clusteraddress(args);
            }
            else{
                System.out.println("Error. Incorrect input.");
            }
        }
    }

    private static void logicaladdress(String[] val){

        try {
            System.out.print(val[0] + " ");

            if (val[1].equals("-b")) {
                System.out.print(val[1] + " ");
                System.out.print(val[2] + " ");
            } else {
                //set -b to 0 otherwise
            }
            if (val[3].contains("--physical-known") || val[3].equals("-p")){
                System.out.println(val[3]);
                //calculate with physical-known
            } else if (val[3].contains("--cluster-known") ||  val[3].equals("-c")){
                System.out.println(val[3]);
                clusterknown(val[5], val[7], val[9], val[11]);
            }
        } catch (Exception e) {
            System.out.println("Unexcepted Exception");
            e.printStackTrace();
        }

    }


    private static void physicaladdress(String[] val){

        try {
            System.out.print(val[0] + " ");

            if (val[1].equals("-b")) {
                System.out.print(val[1] + " ");
                System.out.print(val[2] + " ");
            } else if (val[1].contains("--partition-start")) {
                //parseValue
            } else {
                //set -b to 0 otherwise
            }

            if (val[3].contains("--logical-known") || val[3].equals("-l")){
                System.out.println(val[3]);
                //calculate with physical-known
            } else if (val[3].contains("--cluster-known") ||  val[3].equals("-c")){
                System.out.println(val[3]);
                clusterknown(val[5], val[7], val[9], val[11]);
            }
        } catch (Exception e) {
            System.out.println("Unexcepted Exception");
            e.printStackTrace();
        }
    }

    private static void clusteraddress(String[] val){

        try {
            System.out.print(val[0] + " ");

            if (val[1].equals("-b")) {
                System.out.print(val[1] + " ");
                System.out.print(val[2] + " ");
            } else {
                //set -b to 0 otherwise
            }
            if (val[3].contains("--logical-known") || val[3].equals("-l")){
                System.out.println(val[3]);
                //calculate with physical-known
            } else if (val[3].contains("--physical-known") ||  val[3].equals("-p")){
                System.out.println(val[3]);
                clusterknown(val[5], val[7], val[9], val[11]);
            }
        } catch (Exception e) {
            System.out.println("Unexcepted Exception");
            e.printStackTrace();
        }
    }

    private static void physicalknown(String val){
        //
    }

    private static void logicalknown(String val){
        //
    }

    private static void clusterknown(String k, String r, String t, String f){
        System.out.println("k: " + k + " r: " + r + " t: " + t + " f: " + f);
    }
}
