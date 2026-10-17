# Operating Systems Project 1

## Description

This project demonstrates interprocess communication in Java using separate processes to handle user commands, encryption and decryption, and logging. It also includes a CPU and Main Memory simulation.

## Features

- Encryption and Decryption: Encrypts and decrypts text using a Caesar cipher with a shift of three characters.
- Password Management: Allows users to enter a password or select one from session history.
- Command History: Stores text and results during the current session.
- Logging: Records commands and timestamps in a log file without recording password values.
- Input Validation: Validates text entered for encryption and decryption.
- Interprocess Communication: Uses Java processes and input/output streams to communicate between program components.
- CPU and Main Memory Simulation: Demonstrates reading and writing integer values between separate processes.

## Project Files


Driver.java - Main interactive program that handles commands and communicates with other processes. 
Encryption.java - Handles encryption, decryption, and password commands. 
Logger.java - Records commands and timestamps in a log file. 
CPU.java - Simulates a CPU that sends read and write commands to Main Memory. 
MainMemory.java - Stores an integer and responds to read and write commands. 
log.txt - Log file containing recorded commands. 
devlog.md - Documents the project's development progress. 

## Requirements

- Visual Studio Code
- Java Development Kit (JDK)
- Extension Pack for Java in VS Code

## How to Open and Run the Project

1. Open Visual Studio Code.
2. Select File -> Open Folder.
3. Select the folder.
4. Install the **Extension Pack for Java.
5. Open Driver.java.
6. Click Run.
7. Enter the file name on the first entry & make sure the file is in the project.
8. Run the program and follow the prompts in the terminal.

## Available Commands

PASSWORD - Set a password or select one from session history. 
ENCRYPT - Encrypt text or select text from history. 
DECRYPT - Decrypt text or select text from history. 
HISTORY - Display the current session history. 
QUIT - Exit the program. 


## Logging

The Logger process records commands and timestamps in the specified log file. Existing log entries are preserved when new entries are added. Password values are not recorded.


## Implementation Details

The project uses Java processes and input/output streams to communicate between components. The encryption component uses a Caesar cipher with a shift of three positions.

