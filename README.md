# Post Office Information System

## System Requirements
- Linux OS or Windows 10 with Windows Subsystem for Linux (WSL).
- JDK 11 (OpenJDK or Oracle)
- Internet connectivity to download Gradle dependencies for build

## Build and run
$ ``` ./postoffice.sh```

## Command line options
$ ``` ./postoffice.sh --prices=prices.txt --parcels=parcels.txt```

The above loads a weight-based price list and a list of parcels

## Documentation
PostOfficeIS is a simple Information System for Post Office.

The contract is fully specified in requirements including the supported inputs and outputs.

Note: every minute, after the user hits enter, it lists parcels in the system.

## Implementation notes
Java doesn't implement a ReadKey capability. Due to this limitation, parcels are listed only after the user enters a new command. If the user does not enter commands, parcels are not listed. If we printed a list of parcels at any time, we would have an inconsistent command line, i.e. overwritten by the output.

An option to add ReadKey into Java exists with JNI (Java Native Interface), i.e. write a quick C implementation. It is platform specific. Another option is to use a GUI (i.e. JavaFX), however the requirement is for a command line app. 
