#!/bin/bash

#SBATCH --output=SparkSort8GB.log

spark-submit --class SparkSort --master yarn --deploy-mode client --driver-memory 1g --executor-memory 2g --executor-cores 4 --num-executors 16 SparkSort.jar /input/data-8GB /user/sajmera4/output-spark
hadoop jar /opt/hadoop-2.9.0/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.9.0.jar teravalidate /user/sajmera4/output-spark /user/sajmera4/report-spark
hadoop fs -get /user/sajmera4/report-spark/part-r-00000
