import java.io.*;
import java.util.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class address4forensics {

    public static String calculateAddress(String addressValue, String addressType, String knownTypeA, String knownTypeB){
        return "";
    }


public static void main(String[] args) {
    CommandLine commandLine;


        //============================ define options ========================================//
    Option L = Option.builder("L")
        .longOpt("logical")
        .build();

    Option P = Option.builder("P")
        .longOpt("physical")
        .build();

    Option C = Option.builder("C")
        .longOpt("cluster")
        .build();

    Option b = Option.builder("b")
        .longOpt("partition-start")
        .argName("offset")
        .valueSeparator('=')
        .hasArg()
        .build();

    Option byte_address = Option.builder("B")
        .longOpt("byte-address")
        .desc("Byte address of calculated value")
        .build();

    Option s = Option.builder("s")
        .longOpt("sector-size")
        .argName("bytes")
        .valueSeparator('=')
        .hasArg()
        .build();

    Option l = Option.builder("l")
        .longOpt("logical-known")
        .argName("address")
        .valueSeparator('=')
        .hasArg()
        .build();

    Option p = Option.builder("p")
        .longOpt("physical-known")
        .argName("address")
        .valueSeparator('=')
        .hasArg()
        .build();

    Option c = Option.builder("c")
        .longOpt("cluster-known")
        .argName("address")
        .valueSeparator('=')
        .hasArg()
        .build();

    Option k = Option.builder("k")
        .longOpt("cluster-size")
        .argName("sectors")
        .valueSeparator('=')
        .hasArg()
        .build();

    Option r = Option.builder("r")
        .longOpt("reserved")
        .argName("sectors")
        .valueSeparator('=')
        .hasArg()
        .build();

    Option t = Option.builder("t")
        .longOpt("fat-tables")
        .argName("tables")
        .valueSeparator('=')
        .hasArg()
        .build();

        Option f = Option.builder("f")
        .longOpt("fat-length")
        .argName("sectors")
        .valueSeparator('=')
        .hasArg()
        .build();

    //============================ add options ========================================//
    Options options = new Options();
    CommandLineParser parser = new DefaultParser();

    options.addOption(L);
    options.addOption(P);
    options.addOption(C);
    options.addOption(b);
    options.addOption(s);
    options.addOption(l);
    options.addOption(p);
    options.addOption(c);
    options.addOption(k);
    options.addOption(r);
    options.addOption(t);
    options.addOption(f);
    options.addOption(byte_address);

    //============================ Check which parameters were passed  ========================================//
    try
    {
        commandLine = parser.parse(options, args);

        if (commandLine.hasOption("L") && !commandLine.hasOption("P") && !commandLine.hasOption("C") )
        {
            System.out.println("Calculating Logical Address.");
        }

        if (commandLine.hasOption("P") && !commandLine.hasOption("L") && !commandLine.hasOption("C") )
        {
            System.out.println("Calculating Physical Address.");
        }

        if (commandLine.hasOption("C") && !commandLine.hasOption("P") && !commandLine.hasOption("L") )
        {
            System.out.println("Calculating Cluster Address.");
        }

        if (commandLine.hasOption("b"))
        {
            System.out.print("Option b is present. Offset is: ");
            System.out.println(commandLine.getOptionValue("b"));
        }

        if (commandLine.hasOption("byte-address"))
        {
            System.out.println("byte-address flagged. This option will return the byte address of calculated value.");
        }

        if (commandLine.hasOption("s"))
        {
            System.out.print("Option s is present. Bytes Per Sector: ");
            System.out.println(commandLine.getOptionValue("s"));
        }

        if (commandLine.hasOption("l"))
        {
            System.out.print("Option l is present. Known Logical Address: ");
            System.out.println(commandLine.getOptionValue("l"));
        }

        if (commandLine.hasOption("p"))
        {
            System.out.print("Option p is present. Known Physical Address: ");
            System.out.println(commandLine.getOptionValue("p"));
        }

        if (commandLine.hasOption("c"))
        {
            System.out.print("Option c is present. Known Cluster Address: ");
            System.out.println(commandLine.getOptionValue("c"));
        }

        if (commandLine.hasOption("k"))
        {
            System.out.print("Option k is present. Number of Sectors Per Cluster: ");
            System.out.println(commandLine.getOptionValue("k"));
        }

        if (commandLine.hasOption("r"))
        {
            System.out.print("Option r is present. Number of Reserved Sectors: ");
            System.out.println(commandLine.getOptionValue("r"));
        }

        if (commandLine.hasOption("t"))
        {
            System.out.print("Option t is present. Number of Fat Tables: ");
            System.out.println(commandLine.getOptionValue("t"));
        }

        if (commandLine.hasOption("f"))
        {
            System.out.print("Option f is present. Length of Each Fat Table in Sectors: ");
            System.out.println(commandLine.getOptionValue("f"));
        }

        {
            String[] remainder = commandLine.getArgs();
            System.out.print("Remaining arguments: ");
            for (String argument : remainder)
            {
                System.out.print(argument);
                System.out.print(" ");
            }

            System.out.println();
        }

     }
    catch (ParseException exception)
    {
        System.out.print("Parse error: ");
        System.out.println(exception.getMessage());
    }

}
}
