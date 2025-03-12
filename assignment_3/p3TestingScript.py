# Dr. Ali
# This script automatically compiles, runs, and compares your results to a provided txt file.
# DO NOT MODIFY THE CONTENTS OF THIS FILE!


import os
import subprocess
import functools
import re

# List to store test input cases (if needed)
qinputs = []

def result():
    cwd = os.getcwd()
    
    # Open the sample solution file and student output file
    f1 = open(cwd + '/samplesolution_greedyrobots.txt', "r")
    f2 = open(cwd + '/student_solution_output.txt', "r")
    
    # Read output from each text file
    l1prestrip = f1.readlines()
    l2prestrip = f2.readlines()
    
    # Remove leading and trailing whitespace
    l1 = [s.strip() for s in l1prestrip]
    l2 = [s.strip() for s in l2prestrip]
    
    # Compare output files
    if len(l1) == len(l2) and functools.reduce(lambda x, y: x and y, map(lambda p, q: p == q, l1, l2), True): 
        print("YES!!!! My file output matched Dr. Ali's sample output! :)")
    else: 
        print("Oh no! My file output didn't match Dr. Ali's sample output! :(")
        print("Check to make sure you didn't add extra whitespace or newlines.\n") 
    

def compilerunfile():
    cwd = os.getcwd()
    
    # Compile Java file
    javac = "javac " + cwd + "/GreedyRobotsDriver.java"
    proc = subprocess.run([javac], capture_output=True, text=True, shell=True)
    
    # Run Java file
    java = "java GreedyRobotsDriver"
    f = open('student_solution_output.txt', "w")
    
    # Prepare input if needed
    inputs = ' '.join([str(item) for item in qinputs])
    
    try:
        proc2 = subprocess.run([java], capture_output=True, input=inputs, text=True, shell=True, timeout=2)
        f.write(str(proc2.stdout))
        f.close() 
    except subprocess.TimeoutExpired:
        raise TimeoutError("Your program exceeded the time limit... Submissions like this will receive a score of 0 with no partial credit.")

    print("Done running the student's solution.")


def setupchecker():
    print("Checking to ensure all necessary files are in the directory...")
    cwd = os.getcwd()  # Get current working directory
    
    # Check if Java source file exists
    if not os.path.exists(cwd + "/GreedyRobots.java"):
        raise FileNotFoundError("Missing Java source file! Please put your Java file in the directory. Script Exiting!")
        
    # Open Java file to extract student name
    with open("GreedyRobots.java", 'r') as f:
        name = f.readline().strip()
        name = re.findall("\s(.*)", name)
        name = ''.join([str(item) for item in name])
    
    print(f"The name that will appear in the grader script is {name}.")
    print("If this is incorrect, you need to fix your comment header as per Dr. Ali's instructions.")
    
    # Check if sample solution text file exists
    if not os.path.exists(cwd + "/samplesolution_greedyrobots.txt"):
        raise FileNotFoundError("The provided sample solution text file is missing. Please place the text file in this directory and try again. Script Exiting!")
        
    print("All necessary files are present.")


def main():
    print("Starting the test of my program compared with a provided text file from Dr. Ali.")
    print("Before testing the program, we need to ensure all files are in order.")
    setupchecker()
    print("Setup is complete. Beginning testing.")
    print("Compiling and running the file...")
    compilerunfile()
    print("Now for the moment of truth. Comparing my file output with Dr. Ali's sample solution file.")
    result()
    
    
if __name__ == "__main__":
    main()
