# 환경 설정
## JDK 설치 
* 위치 : https://adoptopenjdk.net
* 버젼 : OpenJDK 8 (LTS)
* 빌드 실행법

## CLI환경에서의 실행 방법
* 실행 방법
```shell script
$ cd project-directory
$ ./mvnw spring-boot:run

[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------------< com.catchsecu:code-test >--------------------------
[INFO] Building code-test 0.1.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] >>> spring-boot-maven-plugin:2.0.2.RELEASE:run (default-cli) > test-compile @ code-test >>>
[INFO] 
[INFO] --- maven-resources-plugin:3.0.1:resources (default-resources) @ code-test ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 2 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ code-test ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:3.0.1:testResources (default-testResources) @ code-test ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/nhn/Downloads/challenge-project-feature-create_table/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.7.0:testCompile (default-testCompile) @ code-test ---
[INFO] No sources to compile
[INFO] 
[INFO] <<< spring-boot-maven-plugin:2.0.2.RELEASE:run (default-cli) < test-compile @ code-test <<<
[INFO] 
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.0.2.RELEASE:run (default-cli) @ code-test ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.2.RELEASE)


```

## API 테스트

# 목표
## 평가(요구사항이) 기준
* 기본 보안인증과 데이터 구조 구현
* 코드 퀄리티하고 기술적인 디자인
 
# 참고
* 자바 코드 규칙
    * https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html
* sqlite
    - 데이터 타잎 : https://www.sqlite.org/datatype3.html 여길 참조하면되고요
    - 쿼리 : https://www.tutorialspoint.com/sqlite/sqlite_overview.htm 여기가 제일 잘나와있네요

    
