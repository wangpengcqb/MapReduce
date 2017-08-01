import XXXXX;


public class myMapper extends MapReduceBase implements Mapper <LongWritable, Text, Text, DoubleWritable>{
	@Override
	public void map(LongWritable Key, Text values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException{
	
	String line = values.toString().trim();
	String[] arr = line.split("/t");
	String store = arr[2];
	Doule sale =  Double.parseDouble(arr[4]);
	
	output.collect(new Text(store), new DoubleWritable(sale)); 
	
	}

}


public class myReducer extends MapReduceBase implements Reducer <Text, DoubleWritable, Text, DoubleWritable> {
	public void reduce(Text Key, Iterator <DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter ) throws ICException{
		
		Double totalSales = 0.0;
		
		while(values.hasNext()){
			totalSales += values.next().get();
		}
		
		output.collect(Key, new DoubleWritable(totalSales));
	}
	
}