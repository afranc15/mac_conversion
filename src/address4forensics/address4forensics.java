import java.io.*;
import java.util.*;


//account for -B, --byte-address at some point

public class address4forensics {

    public static void main(String[] args) {
         if(args.length != 0){

             if(args[0].equalsIgnoreCase("-L") || args[0].equalsIgnoreCase("--logical")){
                logicaladdress(args);
             }else if(args[0].equalsIgnoreCase("-P") || args[0].equalsIgnoreCase("--physical")){
                physicaladdress(args);
             }else if (args[0].equalsIgnoreCase("-C") || args[0].equalsIgnoreCase("--cluster")){
                clusteraddress(args);
            }
            else{
                System.out.println("Your input is incorrect in main: " + Arrays.toString(args));
            }
        }
    }

    private static void logicaladdress(String[] val){

        try {
            System.out.print(val[0] + " ");

            if (val[1].equals("-b")) {
                System.out.print(val[1] + " ");
                System.out.print(val[2] + " ");
            } else if (val[1].contains("--partition-start")) {
                //parseAssignmentVal
            } else {
                //set -b to 0 otherwise
            }

            if (val[3].contains("--physical-known") || val[3].equals("-p")){
                System.out.println(val[3]);

            } else if (val[3].contains("--cluster-known") ||  val[3].equals("-c")){
                System.out.println(val[3]);
                clusterknown(val[5], val[7], val[9], val[11], "logical");
            }
        } catch (Exception e) {
            System.out.println("Your input is incorrect in logical address: " + Arrays.toString(val));
        }

    }


    private static void physicaladdress(String[] val){

        try {
            System.out.print(val[0] + " ");

            if (val[1].equals("-b")) {
                System.out.print(val[1] + " ");
                System.out.print(val[2] + " ");
            } else if (val[1].contains("--partition-start")) {
                //parseAssignmentVal
            } else {
                //set -b to 0 otherwise
            }

            if (val[3].contains("--logical-known") || val[3].equals("-l")){
                System.out.println(val[3]);

            } else if (val[3].contains("--cluster-known") ||  val[3].equals("-c")){
                System.out.println(val[3]);
                clusterknown(val[5], val[7], val[9], val[11], "physical");
            }
        } catch (Exception e) {
            System.out.println("Your input is incorrect in physical address: " + Arrays.toString(val));
        }
    }

    private static void clusteraddress(String[] val){

        try {
            System.out.print(val[0] + " ");

            if (val[1].equals("-b")) {
                System.out.print(val[1] + " ");
                System.out.print(val[2] + " ");
            } else if (val[1].contains("--partition-start")) {
                //parseAssignmentVal
            } else {
                //set -b to 0 otherwise
            }

            if (val[3].contains("--logical-known") || val[3].equals("-l")){
                System.out.println(val[3]);

            } else if (val[3].contains("--physical-known") ||  val[3].equals("-p")){
                System.out.println(val[3]);
                clusterknown(val[5], val[7], val[9], val[11], "cluster");
            }
        } catch (Exception e) {
            System.out.println("Your input is incorrect in cluster address: " + Arrays.toString(val));
        }
    }

    private static void physicalknown(String val, String addressType){
        //
    }

    private static void logicalknown(String val, String addressType){
        //
    }

    private static void clusterknown(String k, String r, String t, String f, String addressType){
        System.out.println("k: " + k + " r: " + r + " t: " + t + " f: " + f);
    }

    private static String parseAssignment(String k){
        return "";
    }
}
