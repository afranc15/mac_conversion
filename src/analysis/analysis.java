import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.nio.file.Path;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

public class analysis {

    private static ArrayList<Long> fatList = new ArrayList<Long>();

    public static void main(String[] args) {
    	if(args.length != 0){
	        try
	            {
	        	
	                //===== Get File and Hash Value =======//
	        		
	
	                File f = new File(args[0]);
	
	                HashCode sha = Files.hash(f , Hashing.sha1());
	                HashCode md5 = Files.hash(f , Hashing.md5());
	
	                //===== Parse Name and Create File  =======//
	
	                String fName = f.getName();
	
	                fName = fName.substring(0, fName.lastIndexOf("."));
	
	                File md5File = new File("Md5-" + fName + ".txt");
	                File sha1File = new File("Sha1-" + fName + ".txt");
	                
	                //===== Print Value =======//
	            	
	                System.out.println(fName + " MD5: " + md5.toString());
	                System.out.println(fName + " SHA-1: " + sha.toString());
	
	                System.out.println("Image Name: " + f.getName());
	
	                md5File.createNewFile();
	                sha1File.createNewFile();
	
	
	                //===== Write Hashes to Files  =======//
	
	                FileOutputStream isa = new FileOutputStream(md5File);
	                OutputStreamWriter oswa = new OutputStreamWriter(isa);
	                Writer wa = new BufferedWriter(oswa);
	                wa.write(md5.toString());
	                wa.close();
	
	                FileOutputStream isb = new FileOutputStream(sha1File);
	                OutputStreamWriter oswb = new OutputStreamWriter(isb);
	                Writer wb = new BufferedWriter(oswb);
	                wb.write(sha.toString());
	                wb.close();
	
	
	                //===== Start reading Files =====//
	                System.out.println();
	                byte[] bytes = Files.toByteArray(f);
	                findAllPartitions(bytes);
	                findAllFAT(bytes);

	            }
	        catch (IOException ex) {
	            System.err.println("Could not hash file " + ": " + ex);
	            throw new RuntimeException(ex);
	        }
    	}
    }
    public static void findAllPartitions(byte[] bytes){
        int startPartition = 446;
        while(bytes[startPartition] == 0){
            printPartition(Arrays.copyOfRange(bytes, startPartition, startPartition+16));
            startPartition+=16;
        }
    }
    public static void printPartition(byte[] bytes){

        //bool for FAT
        boolean isFAT = false;

        // get info
        String startSectorStr = String.format("%02X", bytes[11]) +String.format("%02X", bytes[10]) +String.format("%02X", bytes[9]) + String.format("%02x", bytes[8]);
        long startSector = Long.parseLong(startSectorStr, 16);
        String type = String.format("%02X", bytes[4]);
        String sizeSectorStr = String.format("%02X", bytes[15]) + String.format("%02X", bytes[14]) +String.format("%02X", bytes[13]) + String.format("%02x", bytes[12]);
        long size = Long.parseLong(sizeSectorStr, 16);




        //Check for FAT and Make type String
        if(type.compareToIgnoreCase("01")==0){
            type = "(" + type + ") 12-bit FAT";
        }else if(type.compareToIgnoreCase("06")==0){
            type =  "(" + type + ") 16-bit FAT";
            isFAT= true;
        }else if(type.compareToIgnoreCase("07")==0){
            type =  "(" + type + ") NTFS";
        }else if(type.compareToIgnoreCase("0B")==0){
            type = "(" + type + ") 32-bit FAT";
            isFAT = true;
        }else{
            type =  "(" + type + ") unknown";
        }


        if(isFAT){
            fatList.add(startSector);
        }

        System.out.println(type +  ", " + startSector + ", " + size);

    }

    public static void findAllFAT(byte[] bytes){
        for(int i = 0; i < fatList.size(); i++){
            long x = fatList.get(i)*512;
            printFAT(Arrays.copyOfRange(bytes, (int)x, (int)(x+40)));
        }
    }

    public static void printFAT(byte[] bytes){
        boolean isFat32 =false;
        //info
        String sectorsPerClusterStr = String.format("%02X", bytes[13]);
        long sectorsPerCluster = Long.parseLong(sectorsPerClusterStr, 16);

        String sizeReserveSectorStr = String.format("%02X", bytes[15]) + String.format("%02x", bytes[14]);
        long sizeReserveSector = Long.parseLong(sizeReserveSectorStr, 16);

        String numOfFATS = String.format("%02X", bytes[16]);
        long numFATS = Long.parseLong(numOfFATS, 16);


        long sizeOfFATarea;

        String sizeSector16Str = String.format("%02X", bytes[23]) + String.format("%02x", bytes[22]);
        long sizeSector = Long.parseLong(sizeSector16Str, 16);

        if(sizeSector==0){
            isFat32 = true;
            String sizeSector32Str = String.format("%02X", bytes[39]) + String.format("%02X", bytes[38]) +String.format("%02X", bytes[37]) + String.format("%02x", bytes[36]);
            sizeSector = Long.parseLong(sizeSector32Str, 16);
        }
        sizeOfFATarea = numFATS*sizeSector;

        long cluster2Start = sizeReserveSector + sizeOfFATarea;
        if(!isFat32){
            String rootDirStr = String.format("%02X", bytes[18]) + String.format("%02x", bytes[17]);
            long rootDir = Long.parseLong(rootDirStr, 16);
            cluster2Start += rootDir;
        }

        System.out.println();
        System.out.println("Reserverd area: Start sector: 0 Ending sector: " + (sizeReserveSector - 1) + " Size: " + sizeReserveSector + " sectors");
        System.out.println("Sectors per Cluster: " + sectorsPerCluster + " sectors");
        System.out.println("FAT area: Start sector: " + sizeReserveSector + " Ending Sector: " + (sizeReserveSector + sizeOfFATarea));
        System.out.println("# of FATs: " + numFATS);
        System.out.println("The size of each FAT: " + sizeSector + " sectors");
        System.out.println("The first sector of cluster 2:  " + cluster2Start + " sectors");




    }
}
