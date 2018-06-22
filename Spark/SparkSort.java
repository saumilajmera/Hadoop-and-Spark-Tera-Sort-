import scala.Tuple2;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SparkSort {

	public static <U> void main(String[] args) throws Exception {

		double startTime = System.currentTimeMillis();
		SparkSession spark = SparkSession.builder().appName("JavaSparSort").getOrCreate();

		JavaRDD<String> lines = spark.read().textFile(args[0]).javaRDD();

		JavaPairRDD<String, String> pairRDD = lines.mapToPair(new PairFunction() {

			@Override
			public Tuple2<String, String> call(Object text1) throws Exception {
				String w = String.valueOf(text1);
				return new Tuple2<String, String>(w.substring(0, 10), w.substring(10));
			}
		});

		JavaPairRDD<String, String> sortedtemp = pairRDD.sortByKey();
		sortedtemp.flatMap(new FlatMapFunction<Tuple2<String, String>, String>() {

			@Override
			public Iterator<String> call(Tuple2<String, String> tupData) throws Exception {
				// TODO Auto-generated method stub
				List<String> returnValues = new ArrayList<String>();
				returnValues.add(tupData._1() + tupData._2() + "\n");
				return returnValues.iterator();
			}
		}).saveAsTextFile(args[1]);
		;
		// JavaPairRDD<String, String> finalRDD =
		// pairRDD.repartitionAndSortWithinPartitions(new CustomPartitioner(4));
		// ones.sortByKey().mapToPair(x -> x.swap()).sortByKey().mapToPair(x ->
		// x.swap());
		// repartitionAndSortWithinPartitions
		

		// JavaPairRDD<Tuple2<String, String>, Integer> newRDD = ones.mapToPair(t -> new
		// Tuple2<>(t,1)).sortByKey(new SortByValue());

		// JavaPairRDD<String, String> finalRDD = ones.mapToPair(s -> new Tuple2<String,
		// String>(s._1._1(),s._1._2()));

		spark.stop();
		System.out.println("Time taken in seconds to sort file using Spark is: "
				+ ((System.currentTimeMillis() - startTime) / 1000));

	}
}
