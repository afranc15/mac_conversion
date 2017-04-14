import java.io.*;
import java.util.*;
import java.lang.Integer;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class address4forensics {

    public static void calculateAddress(Options opt, CommandLine cmdLine, CommandLineParser prs){

        int finalResult = 0;
        int offSet = 0;
        if(cmdLine.hasOption("b"))
            {
                offSet = Integer.parseInt(cmdLine.getOptionValue("b"));
            }

        if (cmdLine.hasOption("L"))
            {
                if (cmdLine.hasOption("p"))
                    {
                        finalResult = Integer.parseInt(cmdLine.getOptionValue("p")) - offSet;
                    }
                else if (cmdLine.hasOption("c"))
                    {
                        finalResult = ((((Integer.parseInt(cmdLine.getOptionValue("c")) -2 )  * Integer.parseInt(cmdLine.getOptionValue("k"))) + Integer.parseInt(cmdLine.getOptionValue("r"))) +
                                       (Integer.parseInt(cmdLine.getOptionValue("t")) * Integer.parseInt(cmdLine.getOptionValue("f"))) - offSet);
                    }
                else //Logical-known
                    {
                        finalResult = Integer.parseInt(cmdLine.getOptionValue("l"));
                    }
            }
        else if (cmdLine.hasOption("P"))
            {
                if (cmdLine.hasOption("p"))
                    {
                        finalResult = Integer.parseInt(cmdLine.getOptionValue("p"));
                    }
                else if (cmdLine.hasOption("c"))
                    {
                        finalResult = ((((Integer.parseInt(cmdLine.getOptionValue("c")) -2 )  * Integer.parseInt(cmdLine.getOptionValue("k"))) + Integer.parseInt(cmdLine.getOptionValue("r"))) +
                                       (Integer.parseInt(cmdLine.getOptionValue("t")) * Integer.parseInt(cmdLine.getOptionValue("f"))) + offSet);
                    }
                else //Logical-known
                    {
                        finalResult = Integer.parseInt(cmdLine.getOptionValue("l")) + offSet;
                    }
            }
        else //Cluster
            {
                if (cmdLine.hasOption("p"))
                    {
                        finalResult = ((((Integer.parseInt(cmdLine.getOptionValue("p")) - offSet) - (Integer.parseInt(cmdLine.getOptionValue("r")) + Integer.parseInt(cmdLine.getOptionValue("t")) * Integer.parseInt(cmdLine.getOptionValue("f")))) / Integer.parseInt(cmdLine.getOptionValue("k"))) + 2);
                    }
                else if (cmdLine.hasOption("c"))
                    {
                        finalResult = Integer.parseInt(cmdLine.getOptionValue("c"));
                    }
                else //Logical-known
                    {
                        finalResult = (((Integer.parseInt(cmdLine.getOptionValue("l")) - (Integer.parseInt(cmdLine.getOptionValue("r")) + Integer.parseInt(cmdLine.getOptionValue("t")) * Integer.parseInt(cmdLine.getOptionValue("f")))) / Integer.parseInt(cmdLine.getOptionValue("k"))) + 2);
                    }
            }

        if(cmdLine.hasOption("B"))
            {
                if(cmdLine.hasOption("s"))
                    {

                        finalResult = finalResult * Integer.parseInt(cmdLine.getOptionValue("s"));
                    }
                else
                    {
                        finalResult = finalResult * 512;
                    }

            }
        System.out.println("Final Result: " + finalResult);
    }

    public static void main(String[] args) {
        CommandLine commandLine;
        boolean byteAddress = false;

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

                if (commandLine.hasOption("L") && !commandLine.hasOption("P") && !commandLine.hasOption("C"))
                    {
                        if (commandLine.hasOption("p") || commandLine.hasOption("c"))
                            {
                                System.out.println("Calculating Logical Address.");
                            }
                        else
                            {
                                System.out.println("Parse Error: Physical Known or Cluster Known values are needed for logical address");
                                //                               System.exit(1);
                            }
                    }

                if (commandLine.hasOption("P") && !commandLine.hasOption("L") && !commandLine.hasOption("C"))
                    {
                        if (commandLine.hasOption("l") || commandLine.hasOption("c"))
                            {
                                System.out.println("Calculating Physical Address.");
                            }
                        else
                            {
                                System.out.println("Parse Error: Logical Known or Cluster Known values are needed for cluster address");
                                //                                System.exit(1);
                            }

                    }

                if (commandLine.hasOption("C") && !commandLine.hasOption("P") && !commandLine.hasOption("L"))
                    {
                        if (commandLine.hasOption("p") || commandLine.hasOption("l"))
                            {
                                System.out.println("Calculating Cluster Address.");
                            }
                        else
                            {
                                System.out.println("Parse Error: Physical Known or Logical Known values are needed for cluster address");
                                //                               System.exit(1);
                            }
                    }

                if ((commandLine.hasOption("C") && commandLine.hasOption("P")) ||
                    (commandLine.hasOption("C") && commandLine.hasOption("L")) ||
                    (commandLine.hasOption("L") && commandLine.hasOption("P")))
                    {
                        System.out.println("Parse Error. Select only one -C -P -L");
                        System.exit(1);
                    }

                if (commandLine.hasOption("b"))
                    {
                        System.out.print("The Offset is: ");
                        System.out.println(commandLine.getOptionValue("b"));
                    }
                else
                    {
                        System.out.print("The Offset is: ");
                        System.out.println("0");
                    }

                if (commandLine.hasOption("byte-address"))
                    {
                        System.out.println("Byte Address will be calculated.");

                        if (commandLine.hasOption("s"))
                            {
                                System.out.print("Option s is present. Bytes Per Sector: ");
                                System.out.println(commandLine.getOptionValue("s"));
                            }
                        else
                            {
                                System.out.print("Option s is NOT present. Bytes Per Sector: ");
                                System.out.println(commandLine.getOptionValue("s"));
                            }
                    }

                if (commandLine.hasOption("l"))
                    {

                        if (commandLine.hasOption("P"))
                            {
                                System.out.print("Option l is present. Known Logical Address: ");
                                System.out.println(commandLine.getOptionValue("l"));
                            }
                    }

                if (commandLine.hasOption("p"))
                    {
                        if (commandLine.hasOption("P"))
                            {
                                System.out.print("Option p is present. Known Physical Address is the same: ");
                                System.out.println(commandLine.getOptionValue("p"));
                            }
                    }

                if (commandLine.hasOption("c") &&
                    commandLine.hasOption("k") &&
                    commandLine.hasOption("r") &&
                    commandLine.hasOption("t") &&
                    commandLine.hasOption("f"))
                    {
                        if (commandLine.hasOption("C"))
                            {
                                System.out.print("Option c is present. Known Cluster Address is the same: ");
                                System.out.println(commandLine.getOptionValue("c"));
                            }
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
                    calculateAddress(options, commandLine, parser);
                }

            }
        catch (ParseException exception)
            {
                System.out.print("Parse error: ");
                System.out.println(exception.getMessage());
            }
    }
}
