# Exchange rate calculator <img src="img_1.png" alt="" width="30">

------------
- 이 프로그램은 **한국수출입은행의 open API**를 활용한 환율 계산기 입니다.
<div>
<img style="display: block; margin: 0 auto" alt="image-removebg-preview.png" src="logo.png" width="300"/>
</div>
<div style="text-align: center">
    <a href="https://hits.seeyoufarm.com"><img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fnamung08%2FautoExchanged&count_bg=%23000000&title_bg=%23000000&icon=uplabs.svg&icon_color=%23FFFFFF&title=%EB%B0%A9%EB%AC%B8%EC%9E%90+%EC%88%98&edge_flat=false"/></a>
</div>


<h1>Exchange rate calculator using JFrame and the Export-Import Bank of Korea open API v1.0</h1>

---------
> **KIC Campus**  
> **개발기간 : 1.8 ~ 1.26**

# 개발자 <img src="img_4.png" alt="" width="30">

---
|                 **이상준**                  |
|:----------------------------------------:|
|           ![img.png](img.png)            |
| [@namung08](https://github.com/namung08) |
|            안산대학교 컴퓨터 정보학과 졸업             |

# 프로젝트 소개 <img src="img_3.png" alt="" width="30">

---
- **일일 환율 조회** : 한국수출입은행의 open API 를 사용하여 일일 환율 데이터를 가지고 환율을 계산해 주는 프로그램 입니다.
- **환율 변환** : 23개국의 환율을 가져와 KRW 를 시작하여 구매와 다른 나라의 돈을 우리나라 돈으로 얼마를 구매 할 수 있는지 확인할 수 있습니다.
- **간편한 사용자 인터페이스** : JFrame을 사용하여 직관적이고 사용하기 쉬운 인터페이스를 제공합니다.

# 시작 가이드 <img src="img_2.png" alt="" width="30">

---
- **프로그래밍 언어** : [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- **라이브러리** : Simple-Json (JSON 처리)
- **API** : 한국 수출입은행 Open API
- **빌드 도구** : Gradle
- **사용 툴** : [IntelliJ IDEA](https://www.jetbrains.com/ko-kr/idea/)

```bash
git clone https://github.com/namung08/autoExchanged.git
cd autoExchanged
```

# 기술 스텍 📚

---
<div align=center> 
<h3>Environment</h3>
    <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white">
<h3>Config</h3>
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<h3>Development</h3>
<img src="https://img.shields.io/badge/openapi_initiative-6BA539?style=for-the-badge&logo=openapiinitiative&logoColor=white">
<img src="https://img.shields.io/badge/java_17-007396?style=for-the-badge&logo=openjdk&logoColor=white">
<img src="https://img.shields.io/badge/JFrame-EF2D5E?style=for-the-badge&logo=openjdk&logoColor=white">
<img src="https://img.shields.io/badge/json-000000?style=for-the-badge&logo=json&logoColor=white">
<h3>Communication </h3>
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>

# 화면 구성 <img src="img_5.png" alt="" width="30">

---
| **메인**                  | **통화코드 확인 화면**          |
|-------------------------|-------------------------|
| ![img_6.png](img_6.png) | ![img_7.png](img_7.png) |

| **외화 판매 화면**              | **판매 결과 화면**            |
|---------------------------|-------------------------|
| ![img_11.png](img_11.png) | ![img_10.png](img_10.png) |

| **외화 구매 화면**              |**구매 결과 화면**|
|---------------------------|---|
| ![img_12.png](img_12.png) |![img_8.png](img_8.png)|

# 주요 기능 <img src="img_13.png" alt="" width="30">

---
## ⭐️ 통화 코드 확인 기능
- 통화코드 확인 버튼을 눌러 23개국의 통화코드
- 코드 별 나라 이름을 확인 가능하다
## ⭐️ 외화 판매 기능
- 판매할 통화 코드와 외화의 판매 금액을 작성하면 KRW 로 변환
- 수수료는 계산 하지 않음
## ⭐️ 외화 구매 기능
- 구매할 통화 코드와 외화의 구매 금액을 작성하면 해당 나라의 돈으로 변환
- 수수료는 계산 하지 않음

# 아키텍쳐 <img src="img_14.png" alt="" width="30">

--- 
```perforce
autoExchanged/
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── README.md
├── settings.gradle
└── src/
└── main/
└── java/
└── com/
└── exchanger/
├── fram/
│   ├── CurrencyFram.java
│   ├── CurrencyFrambuy.java
│   ├── CurrencyFrameCheck.java
│   └── CurrencyFramSell.java
├── run/
│   └── Main.java
└── utils/
├── CheckCurrencyCode.java
├── CurrencyCodeMap.java
├── Exchange.java
└── ExchangeRateUtils.java
```