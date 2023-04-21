# CPUScheduler
This Java Program is a CPU scheduling Simulator with "First come first serve" and "Round Robin Scheduling". The program needs a File name with has Processor name, arriving time and duration saved, and an integer which is the time slice for the Round Robin Scheduling.
The text file should be in the following format:
"Processor_name Arival_time Duration"
All the processors should be seperated by a line.
Here is an example:
P2 5 4
P1 1 6
P0 0 3
P3 7 3

If we run these processors with a time slice of 2, the out put from the program would look like this:
-------------------------------------------------
                CPU Scheduling Simulator
-------------------------------------------------

-------------------------------------------------
        First come first served scheduling
-------------------------------------------------

[0-3]   P0 running
[3-9]   P1 running
[9-13]  P2 running
[13-16] P3 running

Turnaround times:
        P0: 3
        P1: 8
        P2: 8
        P3: 9
Wait times:
        P0: 0
        P1: 2
        P2: 4
        P3: 6
Response times:
        P0: 0
        P1: 2
        P2: 4
        P3: 6

Average Turn around time: 7.0
Average Wait time: 3.0
Average Response time: 3.0

-------------------------------------------------
                Round Robin Scheduling
-------------------------------------------------

[0-2]   P0 running
[2-4]   P1 running
[4-5]   P0 running
[5-7]   P1 running
[7-9]   P2 running
[9-11]  P3 running
[11-13] P1 running
[13-15] P2 running
[15-16] P3 running

Turnaround times:
        P0: 5
        P1: 12
        P2: 10
        P3: 9
Wait times:
        P0: 2
        P1: 6
        P2: 6
        P3: 6
Response times:
        P0: 0
        P1: 1
        P2: 2
        P3: 2

Average Turn around time: 9.0
Average Wait time: 5.0
Average Response time: 1.25

-------------------------------------------------
Project done by [Usama Ikram]
-------------------------------------------------
