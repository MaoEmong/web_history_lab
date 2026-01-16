# 웹 서버의 변천사 실습 노트 (web_history_lab)

이 레포지토리는 웹 서버가 발전해 온 흐름을 1세대부터 4세대까지, 작은 예제 코드로 따라가며 이해하는 프로젝트다.  
정적 파일 전달 → 동적 HTML 생성 → 템플릿 엔진(Model 1) → MVC 구조로 이어지는 변화의 이유와 한계를 비교한다.

## 한눈에 보는 흐름

- 1세대: 서버는 파일을 그대로 내려준다. (정적 HTML)
- 2세대: 서버가 로직으로 HTML을 만들어낸다. (서블릿 기반 동적 응답)
- 3세대: 템플릿 엔진에서 화면을 조립한다. (Model 1)
- 4세대: 컨트롤러가 요청을 분리해 처리한다. (MVC)

## 세대별 요약과 예시

### v1 - 정적 HTML 전달

- 의미: 요청 URL이 곧 파일 경로가 되는 가장 단순한 웹 서버
- 위치: `v1/src/main/resources/static`
- 예시 URL
  - `http://localhost:8080/index.html`
  - `http://localhost:8080/a.html`
  - `http://localhost:8080/bio/c.html`

### v2 - 동적 HTML 전달 (서블릿)

- 의미: 요청마다 서버 로직이 HTML을 생성해 반환
- 핵심 코드: `v2/src/main/java/com/example/v2/MyServlet.java`
- 특징
  - `*.do` 매핑으로 요청을 수신
  - 쿼리 파라미터 `cmd`에 따라 서로 다른 HTML을 반환
- 예시 URL
  - `http://localhost:8080/hello.do?cmd=a`
  - `http://localhost:8080/hello.do?cmd=b`

### v3 - 템플릿 엔진 (Model 1)

- 의미: 템플릿 파일 안에서 로직과 화면이 함께 움직이는 구조
- 핵심 코드: `v3/src/main/resources/META-INF/resources/index.jsp`
- 특징
  - JSP 스크립틀릿으로 데이터를 출력
  - 개발 속도는 빠르지만 역할 분리가 약함

### v4 - MVC 구조

- 의미: 요청 처리의 책임이 컨트롤러로 분리되어 구조가 명확해짐
- 핵심 코드
  - `v4/src/main/java/com/example/v4/BoardController.java`
  - `v4/src/main/java/com/example/v4/UserController.java`
- 예시 URL
  - `http://localhost:8080/insert`
  - `http://localhost:8080/delete`
  - `http://localhost:8080/update`
  - `http://localhost:8080/join`

## 왜 이 흐름이 중요한가

- 정적 HTML은 단순하지만 반복과 변경에 약하다.
- 서버에서 HTML을 만들기 시작하면서 개인화와 데이터 기반 응답이 가능해졌다.
- 템플릿 엔진은 개발 생산성을 올렸지만, 역할 분리가 부족해 유지보수가 어려웠다.
- MVC는 책임을 분리해 규모가 커져도 안정적으로 확장할 수 있게 했다.

## 참고

세부 설명과 학습 포인트는 각 버전의 `README.md`에도 정리되어 있다.  
이 레포지토리는 "왜 이런 구조로 발전했는가"를 작은 코드로 체감하는 데 목적이 있다.
