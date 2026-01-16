# 서버 역사 실습: 1세대 정적 → 2세대 동적 → 3세대 템플릿(Model1) → 4세대 MVC

이 프로젝트는 서버 기술의 세대 변화를 이해하기 위한 예제 모음이다. 정적 HTML 전달에서 시작해 동적 HTML 생성, 템플릿 엔진(Model1) 흐름을 거쳐, DispatcherServlet 중심의 MVC 구조로 넘어가는 과정을 작은 코드로 단계별로 체험한다.  
모든 예제는 실제 HTTP 서버 없이 `main`에서 가짜 URI 문자열을 입력받는 방식으로 동작하며, “요청 라우팅이 어떻게 점점 구조화되는지”에 집중한다.

## 구성 요약
- `src/main/java/ex01` 하드코딩 분기(Model1 감성)
- `src/main/java/ex02` 리플렉션으로 메서드 이름 매핑
- `src/main/java/ex03` 커스텀 애노테이션 기반 매핑
- `src/main/java/ex04` 간이 DispatcherServlet + IoC 컨테이너

## 예제별 설명

### ex01 - 하드코딩된 URI 분기
- `App`에서 URI를 `if/else`로 직접 분기해 `BoardController` 메서드를 호출한다.
- 새 기능이 늘어날수록 분기문이 길어지고 유지보수가 어려워진다.
- 1~2세대(정적/동적 HTML 전달)의 단순 구조를 의도적으로 보여준다.

### ex02 - 리플렉션으로 메서드 이름 매핑
- URI에서 `/`를 제거하고, 그 문자열을 메서드 이름과 비교해 호출한다.
- `getDeclaredMethods()`로 모든 메서드를 순회하고 이름이 일치하면 실행한다.
- 분기문은 사라지지만, “URI = 메서드 이름”이라는 강한 결합이 생긴다.

### ex03 - 애노테이션 기반 매핑
- `@RequestMapping("/insert")`처럼 URI를 애노테이션에 선언한다.
- 리플렉션으로 애노테이션 값을 읽어 매핑한다.
- 메서드 이름과 URI가 분리되어 선언적 구조로 전환된다.

### ex04 - DispatcherServlet + IoC 컨테이너 흉내
- `@RestController`가 붙은 클래스를 스캔해 IoC 컨테이너에 담는다.
- `DispatcherServlet`이 모든 컨트롤러의 `@RequestMapping`을 탐색해 요청을 분배한다.
- 여러 컨트롤러(`BoardController`, `UserController`, `ReplyController`)가 공존하며 MVC 진입 구조를 모사한다.

## 세대 변화 핵심 포인트
- 분기 로직이 “코드 내부”에서 “메타데이터(애노테이션)”로 이동한다.
- 라우팅 책임이 개별 코드에서 DispatcherServlet로 집중된다.
- IoC 컨테이너 개념이 도입되면서 객체 생성과 호출 흐름이 분리된다.

## 실행 방법 (간단 테스트)
각 예제의 `App`에서 `uri` 문자열을 바꿔가며 실행한다.  
예: `src/main/java/ex04/App.java`의 `"/reply/write"`를 `"/login"` 등으로 변경해 매핑 결과를 확인한다.

## 출력 예시
예시는 대표 흐름을 보여주기 위한 것으로, `uri` 값에 따라 출력이 달라질 수 있다.

### ex01
```text
select 요청 완료
```

### ex02
```text
join 요청 완료
```

### ex03
```text
insert 요청 완료
응답 버퍼 : apple
```

### ex04
```text
댓글 작성 완료
```

## 예제별 흐름도

### ex01 - 하드코딩 분기
요청 문자열 → `App`의 `if/else` → 컨트롤러 메서드 직접 호출 → 콘솔 출력

### ex02 - 메서드 이름 매핑
요청 문자열 → `/` 제거 → 리플렉션으로 메서드 목록 탐색 → 이름 일치 시 호출 → 콘솔 출력

### ex03 - 애노테이션 매핑
요청 문자열 → 리플렉션으로 애노테이션 탐색 → `@RequestMapping` 값 일치 → 메서드 호출 → 콘솔 출력/반환값

### ex04 - DispatcherServlet + IoC
요청 문자열 → `componentScan`으로 컨트롤러 등록 → `DispatcherServlet`이 매핑 탐색 → 해당 메서드 호출 → 콘솔 출력

## 학습 포인트 정리
- 리플렉션과 애노테이션은 “라우팅 규칙”을 코드에서 분리하는 데 유용하다.
- DispatcherServlet 구조는 MVC의 진입점 역할을 한다.
- 선언적 매핑은 유지보수성을 높이지만, 런타임 오류 가능성을 높일 수 있다.
- IoC 컨테이너가 도입되면 객체 생성과 의존 관계 관리가 분리된다.

## 확장 아이디어
- 파라미터 바인딩(쿼리 문자열 → 메서드 인자)
- 반환값을 View로 연결하는 ViewResolver 구현
- HTTP 서버와 연결해 실제 요청을 받아 처리
- 예외 처리 및 공통 인터셉터 추가
