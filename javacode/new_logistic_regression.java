package application;

import weka.classifiers.*;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.functions.Logistic;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.J48;
import weka.core.*;


public class logistic_regression {
	String[] labels = null;
	 public static void main(String args[]) throws Exception{
			
		 logistic_regression obj = new logistic_regression();
	obj.logisticRegressions();
		 }
	 public void logisticRegressions() throws Exception {
	
		 System.out.println("=============================================================================");
			System.out.println("covid-19 dataset 호출");
			System.out.println("=============================================================================");
		    String fileNames[] = {"covid-19 symptoms dataset"}; // 퀴즈에서 사용하는 3개 arff 파일을 배열로 저장
		    Classifier[] models = {new Logistic(),new J48(), new ZeroR(), new NaiveBayes()}; // 퀴즈 문항별 비교할 4개 분류기를 배열로 저장
//			String fileNames[] = {"diabetes"}; Classifier[] models = {new Logistic()};
			for(String fileName : fileNames){
				System.out.println(fileName + " : ");
				this.logisticRegression(fileName,models);    
			}		
	}

	public void logisticRegression(String fileName,Classifier[] models) throws Exception{
		int numfolds = 10;
		int numfold = 0;
		int seed = 1;
	// 1) data loader 
			Instances data=new Instances(new BufferedReader(new FileReader("COVIDAPP/javacode"+fileName+".arff")));
			data.setClassIndex(data.numAttributes()-1);
			
			Instances train = data.trainCV(numfolds, numfold, new Random(seed));
			Instances test  = data.testCV (numfolds, numfold);
			
			// 2) class assigner
			train.setClassIndex(train.numAttributes()-1);
			test.setClassIndex(test.numAttributes()-1);
			  
			// 3) cross validate setting  
			Evaluation eval=new Evaluation(train);
			  
			// 분류기별 실행 루핑
			for(Classifier model : models){ // models 배열내 분류기를 model 이란 객체에 하나씩 추출하여 실행 (index 지정 없어 편리)
				// 3) 교차검증 실행
				eval.crossValidateModel(model, train, numfolds, new Random(seed));
		
				// 4) model run 
				model.buildClassifier(train);
				   
				// 5) evaluate
				eval.evaluateModel(model, test);
				
				// 6) print Result text (분류기 정분류율 및 평균제곱편차 출력)
				this.printClassfiedInfo(model, eval);
				   			   
				// 7) print out (with test)
				this.printDistribution(test, eval, model);
				
				// 8) 로지스틱 회귀식의 원인변수별 상관계수 추출
				if( model instanceof Logistic)
					this.fetchCoefficientsInfo(model, data);
			} // end-of-for-model
}
	public void printClassfiedInfo(Classifier model, Evaluation eval){
		System.out.println("=============================================================================");
		System.out.println("\t 2) 분류기 정분류율 및 평균제곱편차 출력");
		System.out.println("=============================================================================");
		System.out.print("Correctly Classified Instances : " + String.format("%.2f",eval.pctCorrect()));
		System.out.print(", Root mean squared error  :" + String.format("%.2f",eval.rootMeanSquaredError()));
		System.out.println(", (" + getModelName(model) + ")");
	}
	public String getModelName(Classifier model){
		String modelName = "";
		if ( model instanceof  Logistic)
			modelName = "Logistic";
		else if ( model instanceof  J48)
			modelName = "J48";
		else if ( model instanceof  ZeroR)
			modelName = "ZeroR";
		else if ( model instanceof  NaiveBayes)
			modelName = "NaiveBayes";
	return modelName;
	}
	public void setLabels(Instances data){
		int labelSize = data.classAttribute().numValues();
		this.labels = new String[labelSize];
		for(int x=0 ; x < labelSize ; x++){
			labels[x] = data.classAttribute().value(x);
		}
	}
	 public void printDistribution(Instances test, Evaluation eval, Classifier model) throws Exception{
		 System.out.println("=============================================================================");
		 System.out.println("\t 3) distribution 출력");
		 System.out.println("=============================================================================");
		 
		 this.setLabels(test); // lable 설정 (신규추가 메소드)
		 for (int x=0; x<test.size() ; x++){
			 Instance oneData = test.instance(x);
			 int actual    = (int)oneData.classValue();  // 목표변수 (class) 변수값을 actual 로 할당
			   
			 Prediction prediction = eval.predictions().get(x); // 분류기에서 추출된 결과값을 prediction 에 할당
			 int predicted = (int)prediction.predicted(); 
			   
			 double[] distribution = model.distributionForInstance(oneData); // 모델에서 추출된 distribution 을 distributio 에 할당
			 System.out.print((x+1) + " ");
			 System.out.print( 
						 (actual+1)    + ":" + labels[actual] + " " +
						 (predicted+1) + ":" + labels[predicted] + " " +
						 ((actual == predicted)?" ":"+") + " " + 
						 String.format("%.2f",distribution[0]) + " " + 
						 String.format("%.2f",distribution[1])
			 );  
			 System.out.println("");
		 }    
	 }

	 /*****************************
	  * Print by Prediction object
	  * Prediction 객채로는 distribution 을 찾을 수 없다. (실제 객체안에 값이 있으나 추출메소드 없음)
	  *****************************/
	 public void printPrediction(Evaluation eval){
		 ArrayList<Prediction> list = eval.predictions();
		 int x=0;
		 for (Prediction prediction : list) {
			 x++;
			 int actual = (int)prediction.actual();
			 int predicted = (int)prediction.predicted();
			 System.out.print((x+1) + " ");
			 System.out.print( 
						 (prediction.actual()+1)    + ":" + labels[actual] + " " +
						 (prediction.predicted()+1) + ":" + labels[predicted] + " " +
						 ((actual == predicted)?"":"+") + " "  
						 ); 
			 System.out.println("");
		 }
	 }
	 
	 /*****************************
	  * 4) Logistic coefficients info
	  *****************************/
	 public HashMap<String, Double> fetchCoefficientsInfo(Classifier model, Instances data){
		System.out.println("=============================================================================");
		System.out.println("\t 4) Logistic coefficients info 출력");
		System.out.println("=============================================================================");
		HashMap<String, Double> coeffMap = new HashMap<String, Double>();
		double[][] coeff = ((Logistic)model).coefficients();       // 로지스틱 회귀분류기에 저장된 변수별 상관계수 할당
		Enumeration<Attribute> enums = data.enumerateAttributes(); // 변수명  할당
		while (enums.hasMoreElements()) {
			Attribute attribute = (Attribute) enums.nextElement(); // 변수명 추출			
			int col = attribute.index()+1;
			System.out.println(attribute.name() + " : " + String.format("%.4f",coeff[col][0])); // 추출된 변수명 별 상관계수 출력
			coeffMap.put(attribute.name(), Double.valueOf(coeff[col][0])); // 추출된 변수명 별 상관계수 저장
		}
		System.out.println("Intercept : " + String.format("%.4f",coeff[0][0])); // intercept = bias
		coeffMap.put("Intercept", Double.valueOf(coeff[0][0])); // 맨마지막 intercept 과 그의 상관계수 저장
		System.out.println(model);		   
		return coeffMap;
	 }

}
