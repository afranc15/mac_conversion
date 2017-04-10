import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class address4forensics {
    private static final String[] months = {"Jan","Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public static void main(String[] args) {
         if(args.length != 0){
            String hex = getString(args);
            if(args[0].equalsIgnoreCase("-L")){
                //logical address
            }else if(args[0].equalsIgnoreCase("-P")){
                //physical address
            }else if (args[0].equalsIgnoreCase("-P")){
                //cluster address
            }
            else{
                System.out.println("Error. Incorrect input.");
            }
        }
    }

    private static String readFromFile(String file){
        String hex;
        try {
            FileReader fileReader = new FileReader(file);
            Scanner scanner =  new Scanner(fileReader);
            hex = scanner.nextLine();
            scanner.close();
            return hex;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //get string

    //logical address -L

    //physical address -P

    //cluster address -C

    //---------------

    //offset -b

    //byte-address -B

    //sector size -s (requires -B)

    //address -l when -C or -P is used. Else returns logical address

    //address -p when -C or -L are used. Else returns physical address

    //address -c when -P or -L are used. Else returns cluster address. -k -r -t -f are required for this.

}
