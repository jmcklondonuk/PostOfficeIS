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

Note: every minute, after the user hits enter, it lists parcels in the system.

## Implementation notes
Java doesn't implement a ReadKey capability, so parcel listing is intentionally outputted after a user command is entered.
This means the system also won't repeat itself when the user does not take any action. Mainly, user input will never get 
inconsistent or messy, so an errorneous input is prevented.

An option to add ReadKey into Java exists with JNI (Java Native Interface), i.e. write a quick C implementation. It is platform specific. Another option is to use GUI (i.e. JavaFX), however the requirement for a command line app. 
