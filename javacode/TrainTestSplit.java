package application;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class TrainTestSplit {
	
		public static void main(String[] args) throws Exception {
	
			String dataFileName = "covid-19 symptoms dataset.cs";	// use appropriate path
			Instances data = (new DataSource(dataFileName)).getDataSet();
			data.setClassIndex(data.numAttributes() - 1);
			data.randomize(new java.util.Random());	// randomize instance order before splitting dataset
			
		
			int trainsize = (int) Math.round(data.numInstances()*0.7);
			int testsize = data.numInstances()-trainsize;
			Instances train = new Instances(data,0,trainsize);
			Instances test = new Instances(data, trainsize, testsize);
		
		}

	
}
