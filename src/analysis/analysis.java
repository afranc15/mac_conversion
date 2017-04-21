import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import java.io.*;
import java.nio.file.Path;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

public class analysis {

    public static void main(String[] args) {
        try
            {


                //===== Get File and Hash Value =======//

                URL path = analysis.class.getResource("TestImage1.img");

                File f = new File(path.getFile());

                HashCode sha = Files.hash(f , Hashing.sha1());
                HashCode md5 = Files.hash(f , Hashing.md5());

                URL pathb = analysis.class.getResource("TestImage2.img");

                File fb = new File(pathb.getFile());

                HashCode shab = Files.hash(fb , Hashing.sha1());
                HashCode md5b = Files.hash(fb , Hashing.md5());

                //===== Print Value =======//

                System.out.println("Test Image 1 MD5: " + md5.toString());
                System.out.println("Test Image 1 SHA-1: " + sha.toString());

                System.out.println("Test Image 2 MD5: " + md5b.toString());
                System.out.println("Test Image 2 SHA-1: " + shab.toString());

                System.out.println("Image Name: " + f.getName());
                System.out.println("Image Name: " + fb.getName());

                //===== Parse Name and Create File  =======//

                String fName = f.getName();
                String fbName = fb.getName();

                fName = fName.substring(0, fName.lastIndexOf("."));
                fbName = fbName.substring(0, fbName.lastIndexOf("."));

                File md5File = new File("Md5-" + fName + ".txt");
                File sha1File = new File("Sha1-" + fName + ".txt");
                File md5Fileb = new File("Md5-" + fbName + ".txt");
                File sha1Fileb = new File("Sha1-" + fbName + ".txt");

                md5File.createNewFile();
                sha1File.createNewFile();
                md5Fileb.createNewFile();
                sha1Fileb.createNewFile();

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

                FileOutputStream isc = new FileOutputStream(md5Fileb);
                OutputStreamWriter oswc = new OutputStreamWriter(isc);
                Writer wc = new BufferedWriter(oswc);
                wc.write(md5b.toString());
                wc.close();

                FileOutputStream isd = new FileOutputStream(sha1Fileb);
                OutputStreamWriter oswd = new OutputStreamWriter(isd);
                Writer wd = new BufferedWriter(oswd);
                wd.write(shab.toString());
                wd.close();

            }
        catch (IOException ex) {
            System.err.println("Could not hash file " + ": " + ex);
            throw new RuntimeException(ex);
        }
    }
}
