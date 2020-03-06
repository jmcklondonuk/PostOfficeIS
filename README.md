# Post Office Information System

## System Requirements
- Linux OS or Windows 10 with Windows Subsystem for Linux (WSL).
- JDK 11 (OpenJDK or Oracle)
- Internet connectivity to download Gradle dependencies for build

## Build and run
$ ``` ./postoffice.sh```

## Command line options
$ ``` ./postoffice.sh --prices=prices.txt --parcels=parcels.txt```

This above loads a weight-based price list and a list of parcels

## Documentation
PostOfficeIS is a simple Information System for Post Office.

The contract is fully specified in requirements including supported inputs and outputs.

Note: every 2 minutes, after the user hits enter, it lists parcels in the system.

## Implementation notes
Java doesn't implement a ReadKey capability. 
It only implements "read line". It is implemented 
by delegating control to the native OS read line 
function.

Once the user starts typing, we do not have 
any control until he hits enter.

While outputting a list of parcels is possible 
at the same time as the user is typing a command, 
it messes up the screen output, so that his 
command becomes unreadable (interrupted by 
our output).
 
To avoid the screen getting messed up,  I have 
implemented parcel listing intentionally synchronously
 after the user command is entered.

Better solutions are possible only when 
1. I would implement a JNI function (Java Native Interface)
in C which would do native-OS call of ReadKey which 
is probably an overkill for this test.

2. I would create a graphical application 
such as JavaFX where the ReadKey event obviously works.

3. Or, I would create a web or mobile application. 

ReadKey simply doesn't exist in Java for console applications, 
as it waits until return.
