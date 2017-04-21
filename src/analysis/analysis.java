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
                URL path = analysis.class.getResource("TestImage1.img");

                File f = new File(path.getFile());

                HashCode sha = Files.hash(f , Hashing.sha1());
                HashCode md5 = Files.hash(f , Hashing.md5());

                URL pathb = analysis.class.getResource("TestImage2.img");

                File fb = new File(pathb.getFile());

                HashCode shab = Files.hash(fb , Hashing.sha1());
                HashCode md5b = Files.hash(fb , Hashing.md5());


                System.out.println("Test Image 1 MD5: " + md5.toString());
                System.out.println("Test Image 1 SHA-1: " + sha.toString());

                System.out.println("Test Image 2 MD5: " + md5b.toString());
                System.out.println("Test Image 2 SHA-1: " + shab.toString());

            }
        catch (IOException ex) {
            System.err.println("Could not hash file " + ": " + ex);
            throw new RuntimeException(ex);
        }
    }
}
