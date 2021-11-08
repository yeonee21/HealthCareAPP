# HealthCareAPP

https://github.com/yeonee21/WearableHealthCare.git 

위에서 구현한 예측모델을 활용하여 웨어러블 기기를 이용한 코로나 19 바이러스 감염 여부 예측 어플리케이션 개발

## 프로젝트 명칭(국문)
코로나19 감염 예측 어플리케이션

## 프로젝트 명칭(영문)
Covid19 infection prediction application

## 발명자 리스트
김연의, 심서영, 장유진

## 프로젝트 목적
이미 사용되고 있는 코로나19 자가진단 키트의 경우, 정확도가 떨어지고 신체적인 증상 및 기저질환 유무 등을 반영하지 못할 뿐만 아니라 즉각적인 결과값을 도출해내지 못한다는 문제점이 존재한다. 이를 해결하기 위하여 웨어러블 기기를 통해 사용자의 건강 상태를 예측하고, 코로나 감염 증상의 유무를 종합하여 간단한 방법으로도 코로나 바이러스 감염 예측을 가능하게 하는 프로그램을 솔루션으로 제시한다. 이를 통해 코로나 바이러스 감염 예측을 정확성을 높이고, 즉각적인 결과를 도출해 낼 수 있다.
본 어플리케이션은 웨어러블 센서를 통해 데이터를 불러와 LSTM모델을 통해 상태를 예측한다. Logistic Regression모델을 통해 코로나19 증상 데이터를 학습시켜 가중치를 도출하여 이후 설문조사 결과와 곱해 위험도로 수치화한다.
예측한 건강 상태와 코로나 바이러스 증상 데이터 및 기저 질환 유무를 위험도로 수치화하여 최종적으로 코로나 바이러스 감염 위험도를 도출한다.

## 어플리케이션 실행 방법
1. 어플리케이션 실행 후 로그인을 한다.
2. 하단 메뉴바에서 웨어러블 데이터 칸을 클릭해 데이터 불러오기를 실행하면 웨어러블 데이터를 불러올 수 있고 LSTM모델이 내부에서 작동하여 예측 심박수와 혈중 산소 농도를 저장한다.
3. 하단 메뉴바에서 설문조사 칸을 클릭해 코로나 증상 관련 설문조사를 진행한다.
4. 하단 메뉴바에서 결과확인 칸을 클릭해 코로나 위험도 결과를 확인한다.

## Lisence
https://github.com/yeonee21/HealthCareAPP/blob/master/LICENSE#Apache-2.0heading
[License](#Apache-2.0heading)


## Test 환경
-	Android Studio Arctic Fox 
- Android Virtual Device: 
    - Resolution | (Pixel 4) 5.7 1080x2280: xxhdpi
    - R | API Level 30, Android 11.0 x86(Google APIs)

## 어플리케이션 실행 영상
https://youtu.be/NmkYJDfP6V8


