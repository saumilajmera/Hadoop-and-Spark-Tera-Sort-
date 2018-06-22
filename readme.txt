###### Read Me ######

There are two folders namely Hadoop and Spark which contains Makefile, java file, slurms file and log file for individual application.

So both should be run from their respective folder.


###### Compile ######

make

###### Steps to Run HadoopSort  ############


1) Run the Makefile by make command which will compile the HadoopSort.java code with required flags into executable HadoopSort.jar jar file 

2) Run script file hadoopsort8GB.slurm,hadoopsort20GB.slurm and hadoopsort80GB.slurm using sbatch command but remember to delete part-r-0000 file generated everytime.

3) Only input file directory and output file directory is given to the hadoop run command to run HadoopSort.jar

4) Also teravalidate command is run after above command which validates the sorted file and put the output generated file containing checksum in login node with file name part-r-0000

4) Output is written into file named HadoopSort8GB.log, HadoopSort20GB.log and HadoopSort80GB.log which contains different timings written as per operation and valsort verified output 


###### Steps to Run SparkSort  ############


1) Run the Makefile by make command which will compile the SparkSort.java code with required flags into executable SparkSort.jar jar file 

2) Run script file sparksort8GB.slurm,sparksort20GB.slurm and sparksort80GB.slurm using sbatch command but remember to delete part-r-0000 file generated everytime.

3) Only input file directory and output file directory is given to the spark run command to run SparkSort.jar

4) Also teravalidate command is run after above command which validates the sorted file and put the output generated file containing checksum in login node with file name part-r-0000

4) Output is written into file named SparkSort8GB.log, SparkSort20GB.log and SparkSortGB.log which contains different timings written as per operation and valsort verified output 

###### Clean ######

make clean

###################
