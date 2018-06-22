JCC = javac
JFLAGS= -classpath /opt/hadoop-2.9.0/etc/hadoop:/opt/hadoop-2.9.0/share/hadoop/common/lib/*:/opt/hadoop-2.9.0/share/hadoop/common/*:/opt/hadoop-2.9.0/share/hadoop/hdfs:/opt/hadoop-2.9.0/share/hadoop/hdfs/lib/*:/opt/hadoop-2.9.0/share/hadoop/hdfs/*:/opt/hadoop-2.9.0/share/hadoop/yarn:/opt/hadoop-2.9.0/share/hadoop/yarn/lib/*:/opt/hadoop-2.9.0/share/hadoop/yarn/*:/opt/hadoop-2.9.0/share/hadoop/mapreduce/lib/*:/opt/hadoop-2.9.0/share/hadoop/mapreduce/*:/opt/hadoop-2.9.0/contrib/capacity-scheduler/*.jar

default:
	$(JCC) $(JFLAGS) HadoopSort.java
	jar cvf HadoopSort.jar *.class 

clean:
	$(RM) *.class
