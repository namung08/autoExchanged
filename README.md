# autoExchanged

autoExchanged는 JSON을 사용하여 데이터를 자동으로 교환하는 Java 애플리케이션입니다. 이 프로젝트는 Gson 라이브러리를 사용한 JSON 데이터 처리와 JUnit 5를 사용한 테스트를 시연합니다.

## 특징

- 자동 데이터 교환
- Gson을 사용한 JSON 데이터 처리
- JUnit 5를 사용한 단위 테스트

## 시작하기

### 사전 준비

- Java 8 이상
- Gradle

### 설치

1. 리포지토리 클론:

    ```bash
    git clone https://github.com/namung08/autoExchanged.git
    cd autoExchanged
    ```

2. 프로젝트 빌드:

    ```bash
    ./gradlew build
    ```

### 애플리케이션 실행

애플리케이션을 실행하려면 다음 명령어를 사용하세요:

```bash
./gradlew run
```

### 3. 프로젝트 디렉토리 구조 확인

현재 프로젝트 디렉토리 구조는 다음과 같습니다:
```
autoExchanged/
├── .git/
├── .gitignore
├── .gradle/
├── .idea/
├── build.gradle
├── build/
├── gradle/
├── gradlew
├── gradlew.bat
├── README.md
├── settings.gradle
└── src/
├── main/
│ ├── java/
│ │ └── ... (Java 소스 파일들)
```