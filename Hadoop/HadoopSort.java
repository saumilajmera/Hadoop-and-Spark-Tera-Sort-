import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class HadoopSort {

	public static class MyMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
		public MyMapper() {

		}

		@Override
		public void map(LongWritable key, Text val, OutputCollector<Text, Text> outputValues, Reporter reporter)
				throws IOException {
			// TODO Auto-generated method stub
			outputValues.collect(new Text(val.toString().substring(0, 10)),
					new Text(val.toString().substring(10, val.toString().length())));

		}
	}

	public static class MyReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

		public MyReducer() {

		}

		@Override
		public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			// TODO Auto-generated method stub
			while (values.hasNext()) {
				output.collect(key, values.next());
			}

		}
	}

	public static class ValueComparator extends WritableComparator {

		public ValueComparator() {
			super(Text.class, true);
		}

		@Override
		public int compare(WritableComparable firstText, WritableComparable secondText) {
			// TODO Auto-generated method stub

			Text key1 = (Text) firstText;
			Text key2 = (Text) secondText;
			return super.compare(key1, key2);
		}

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub

		double startTime = System.currentTimeMillis();
		JobConf conf = new JobConf();

		// Path output = new Path(args[1]);
		// FileSystem hdfs = FileSystem.get(conf);

		// delete existing directory
		// if (hdfs.exists(output)) {
		// hdfs.delete(output, true);
		// }
		// Path partitionOutputPath = new Path(args[1]);
		conf.setJarByClass(HadoopSort.class);
		FileInputFormat.setInputPaths(conf, args[0]);
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		conf.setJobName("Sort By Hadoop");

		conf.setNumReduceTasks(6);
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		
		conf.setMapperClass(MyMapper.class);
		conf.setCompressMapOutput(true);
		// conf.setMapOutputCompressorClass(CompressionCodec.class);
		// conf.setCombinerClass(MyReducer.class);
		// conf.setCombinerKeyGroupingComparator(ValueComparator.class);
		conf.setOutputValueGroupingComparator(ValueComparator.class);

		conf.setReducerClass(MyReducer.class);

		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);

		
		JobClient.runJob(conf);
		System.out.println("Time taken in seconds to sort file using Hadoop is: "
				+ ((System.currentTimeMillis() - startTime) / 1000));

	}

}
