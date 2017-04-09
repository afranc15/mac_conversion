# mac_conversion
CSE 469 Computer and Network Forensics Class Project 
Task 1 
Tool B)MAC Conversion
The purpose of this task is to write code that performs the MAC conversion based on the following usage specification and input/output scheme. This conversion MUST follow the procedure that we discussed in the lecture. We assume a little endian ordering is applied.
mac_conversion -T|-D [–f filename | -h hex value ]
-T Use time conversion module. Either –f or –h must be given. -D Use date conversion module. Either –f or –h must be given. -f filename
            This specifies the path to a filename that includes a hex value
            of time or date. Note that the hex value should follow this
            notation: 0x1234. For the multiple hex values in either a file
            or a command line input, we consider only one hex value so the
            recursive mode for MAC conversion is optional.
      -h hex value
            This specifies the hex value for converting to either date or
            time value. Note that the hex value should follow this notation:
            Ox1234. For the multiple hex values in either a file or a
                     
  
command line input, we consider only one hex value so the
              recursive mode for MAC conversion is optional.
