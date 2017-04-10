import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class address4forensics {

    public static void main(String[] args) {
         if(args.length != 0){

            String val = getString(args);

            if(args[0].equalsIgnoreCase("-L")){
                logicaladdress(val);
            }else if(args[0].equalsIgnoreCase("-P")){
                physicaladdress(val);
            }else if (args[0].equalsIgnoreCase("-P")){
                clusteraddress(val);
            }
            else{
                System.out.println("Error. Incorrect input.");
            }
        }
    }

    private static String getString(String[] args){

        String val;

        if (args[1].equals("-b")) {
            val = offset(args[2]);

        }else if (!args[1].equals("-B")){
            val = byteaddress(args[2]);

        }else if (!args[1].equals("--physical-known")){ //need to still fix as well as others
            val = physicalknown(args[1]);

        }else if (!args[1].equals("--logical-known")){
            val = logicalknown(args[1]);

        }else if (!args[1].equals("--cluster-known")){
            val = clusterknown(args[1]);

        }else {
            System.out.println("Error. Incorrect input.");
            return null;
        }
        return val;
    }



    private static string logicaladdress(String val){
        return ""
    }


    private static string physicaladdress(String val){
        return ""
    }



    private static string clusteraddress(String val){
        return ""
    }

    private static string offset(String val){
        return ""
    }

    private static string byteaddress(String val){
        return ""
    }

    //sector size -s (requires -B)

    //address -l when -C or -P is used. Else returns logical address

    //address -p when -C or -L are used. Else returns physical address

    //address -c when -P or -L are used. Else returns cluster address. -k -r -t -f are required for this.

}
