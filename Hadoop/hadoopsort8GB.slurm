#!/bin/bash

#SBATCH --output=HadoopSort8GB.log

hadoop jar HadoopSort.jar HadoopSort /input/data-8GB /user/sajmera4/output-hadoop
hadoop jar /opt/hadoop-2.9.0/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.9.0.jar teravalidate /user/sajmera4/output-hadoop /user/sajmera4/report-hadoop
hadoop fs -get /user/sajmera4/report-hadoop/part-r-00000
