Filecomparator
==============

Description.
------------
Program compares two files containing one column strings and extracts the common ones 
(no duplicates) in a third file. Steps are described in FileAggregator interface. 
	
	a. process		-- Enforce programs excecution steps and handles exceptions 
	b. validate     -- Validate user input (i.e files exist, not in use etc) and create a filehelper.
	c. sort			-- Sort input files and remove duplicates to make comparison more efficient
	d. prepare		-- Update filehelper for comparison. -- final
	e. compare      -- Perform comparison and create the output file.
	f. finalize     -- Release resources.
	
AbstractFileAggregator enforces the process(1), validation(2) and preparation(4) steps and DefaultFileAggregatorImpl implements the rest of them using 
strategy objects. 

NOTES
=====
For continues integration JUnit and Mockito should be introduced to the project. 
Test cases should be created against each step of the program.   

How to refactor/extend.
---------------------------
Update Launcher->run to read a fourth parameter or a configuration file where a custom FileAggregator implementation should be configured
Example:

	private void run(String args[]){
		Class<?> clazz = null;
		if(args != null && args.length  == 4 ){
			clazz = args[3];
			args[3] = null;
			try{
				Object customFileAggregator = clazz.newInstance();
			    if(customFileAggregator instanceOf FileAggregator)
					customFileAggregator.process(args);
			}catch(Exception ex){
				System.out.println(ex.toString());	
			}
		}else{
			(new DefaultFileAggregatorImpl()).process(args);
		}
	}

QA
==
1. What is the maximum number of strings in the input files that your project can handle ? 
Assume that the heap space available to the JVM is 200MB and that each string has on average 8 characters.
Answer:
Comparison method is not expected to run out of memory, since processing is done line by line using BufferedReader and size of strings are very small 
(GC should handle them efficiently, when line stream get out of scope).
In order to optimize performance, external sorting of files has been introduced, which is prone to memory run outs.
File to be sorted is splitted in 1024 files
Average string should be on a 32 bit VM around 8(chars)* 2(bytes) + 40(bytes overhead)= 56 bytes
Available bytes are 200 * 1.048.576 = 209.715.200 which provide a capacity of 3.700.000 lines per splitted file
In theory program should be able to handle source files of up to 38.000.000 lines

2. What would you do to increase this limit ?
Answer:
Nothing!

3. Is your program efficient ?
Answer:
Sorting is very efficient, but comparison can be improved.

4. What would you do to increase its execution performance ?
Answer:
Since compared file is sorted, BufferedReader should start processing from the last stream read! 
