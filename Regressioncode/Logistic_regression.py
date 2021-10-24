import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression



#read data
symptom = pd.read_csv("covid-19 symptoms dataset.csv")
print(symptom.head())

#feature/ target data
feature_columns = symptom.columns.difference(["infectionProb"])
X = symptom[feature_columns]
y = symptom["infectionProb"]
symptom = symptom.drop(['age'], axis=1)

#split training set/ test set
X_train, X_test, y_train, y_test = train_test_split(X, y,  train_size=0.7, test_size=0.3, random_state = 123)
print(X_train.shape, X_test.shape, y_train.shape, y_test.shape)

#Logistic Regression
model = LogisticRegression(random_state=45)
model.fit(X_train, y_train)
y_pred = model.predict(X_test)


#coef
feature = np.array(symptom.columns[0:14])
log_feature = pd.DataFrame({"feature":feature, "coefficient(LR)":model.coef_[0]})
log_feature.sort_values(by=['coefficient(LR)'], axis=0, ascending=False, inplace=True)
print('Important feature of Logistic Regression Algorithm')
print(log_feature.head(14), end='\n\n')

print('##############################################\n')
