# 10-05-2026 12:20pm
During this session the goal is to understand the assignement and how the starting files work together. I'm currently running into a scanner error with the starting files, and trying to fix it before working further.
I have fixed the error and got the output set to #...
Sections needed:
-logger
-encryption program
-driver
-must use process class

The next session, I will begin the logger.

# 10-06 3:16 pm
I will begin to plan the logger section. I made notes about the requirements needed for the general use. 
I will start with writing the commands to the log file and add the details later on (date and format).

I have completed the logger's log file. It takes the statement written in the terminal, and reads it until 'QUIT' is stated.

# 10-06 8:08 pm
I am continuing to work on the logger. Trying to understand how to implement format description and date/time for the command statements.

# 10-06 9:31 pm
I have completed command input for the file, and formatting each statement as it goes into the log file.
The logger is completed as of now.

Next Session I plan to research the encryption program and make a plan to go about it.

# 10-07 10:12 am
I will look at the encryption section and plan it. I will use the same method for the logger, but add functions to specific keywords.

# 10-07 10:51am
I have added add, encrypt, and decrypt keyword methods. I have added error and results from those inputs.
The Encryption is completed as of now. 

I realized the encrytion does not use the process class. I will go back and edit this. 

# 10-07 11:51 pm 
I will use the current time to edit in processes now I have the basic idea for the encryption. The Encrypt and Decrypt do not work on printing results. The process is invalid for some reason.

# 10-07 2:00 pm 
Continuing to fix processes. The Encrypt and decrypt were not showing results, so I added java and class paths to set up communication pipes.

I will work on the driver program next session and plan it accordingly. 

# 10-08 8:52 am
I will begin to look at the driver requirements. I will start with the menu and connecting the logger and encrypter to the driver file. I plan that the menu will keep executing until quit. 
I set up the driver's main menu and will connect the logger with the Driver. 

I have set up the driver program, I will begin to work on the History section as result is not printing right now. This will help me see the overall issue. I have set up the commands, but it's
not writing to the log file. I will continue this next session. 

# 10-08 11:36 am
Continuing to solve the communication. I set up the command line, but there is still an issue reading pass from encryption because the command is sent without a message.