# 웹 서버의 구조 진화: Model1에서 MVC, 그리고 DispatcherServlet까지

이 프로젝트는 **웹 서버 구조가 어떻게 진화했는지**를 코드로 단계별 체험하기 위한 실습 모음이다.

특히 다음 흐름에 집중한다.

> JSP(Model1) 구조 → DispatcherServlet 기반 MVC 구조(Spring 스타일)

모든 예제는 실제 HTTP 서버 대신 `main` 메서드에서 가짜 URI 문자열을 입력받아  
**요청 → 라우팅 → 컨트롤러 호출** 흐름만 집중적으로 구현한다.

---

## 전체 흐름 요약

| 단계 | 핵심 변화 |
|------|-----------|
| ex01 | 요청 분기를 코드에 직접 작성 |
| ex02 | 리플렉션으로 메서드 자동 탐색 |
| ex03 | 애노테이션으로 라우팅 정보 분리 |
| ex04 | DispatcherServlet + IoC 컨테이너 |

---

## 구조 개요

```
ex01 → ex02 → ex03 → ex04
하드코딩 → 리플렉션 → 애노테이션 → MVC 구조
```

---

## ex01 – 하드코딩된 요청 분기 (초기 서버 구조)

### 개념

요청 URI를 코드에서 직접 분기 처리하는 방식이다.

```java
if(uri.equals("/select")) { ... }
else if(uri.equals("/insert")) { ... }
```

### 의미

- 가장 원시적인 요청 처리 방식
- 서블릿 초창기 혹은 단일 JSP 구조(Model1)에 가까운 형태

### 한계

- URL 추가 시마다 코드 수정
- 분기문 폭증
- 공통 처리 불가능
- 구조 확장 어려움

> 서버가 커질수록 유지보수가 급격히 어려워진다.

---

## ex02 – 리플렉션 기반 메서드 탐색

### 개념

> URI 문자열을 메서드 이름으로 해석해서 실행한다.

```java
Method[] methods = BoardController.class.getDeclaredMethods();
```

### 구조

```
URI → 문자열 변환 → 메서드 목록 순회 → 이름 일치 → invoke()
```

### 개선점

- if/else 제거
- 코드 수정 없이 메서드 추가 가능

### 새로운 문제

- URI = 메서드 이름 (강한 결합)
- 의미 불분명
- 실무에 쓰기 어려움

---

## ex03 – 애노테이션 기반 라우팅

### 개념

URI 정보를 **코드에서 분리하여 메타데이터로 선언**

```java
@RequestMapping("/insert")
public void insert() { }
```

### 구조

```
URI → 모든 메서드 스캔 → @RequestMapping 값 비교 → 실행
```

### 효과

- URI와 메서드 이름 분리
- 선언적 구조
- 가독성 상승
- 유지보수 용이

> 이 단계가 Spring MVC 구조의 핵심 아이디어다.

---

## ex04 – DispatcherServlet + IoC 컨테이너 구조

### 개념

> 모든 요청은 하나의 진입점(DispatcherServlet)을 거친다.

### 구조

```
요청
 ↓
DispatcherServlet
 ↓
컨트롤러 탐색
 ↓
메서드 실행
```

### 주요 특징

#### 1. IoC 컨테이너

```text
@RestController 가 붙은 클래스 자동 등록
```

- 객체 생성 책임 분리
- 컨트롤러 중앙 관리

#### 2. DispatcherServlet

- 모든 요청 수신
- 모든 매핑 관리
- 모든 컨트롤러 호출 담당

#### 3. 다중 컨트롤러 구조

```text
BoardController
UserController
ReplyController
```

각각 독립된 책임을 가진다.

---

## 세대 변화의 본질

### 이전

```
요청 → 코드 분기
```

### 이후

```
요청 → 중앙 관제 시스템(DispatcherServlet) → 컨트롤러
```

---

## 핵심 변화 요약

| 항목 | 변화 |
|------|------|
| 라우팅 | 코드 → 메타데이터 |
| 진입점 | 다수 → 단일 |
| 객체 관리 | new → 컨테이너 |
| 구조 | 절차적 → MVC |

---

## 이 프로젝트의 학습 목적

- Spring MVC 구조가 왜 이렇게 되었는지 이해
- DispatcherServlet의 존재 이유 체감
- 애노테이션 + 리플렉션의 역할 파악
- MVC 구조의 필요성 인식

---

## 정리 한 문장

> 이 예제들은 웹 서버 구조가 "코드 분기"에서 "중앙 제어(MVC)"로 진화하는 과정을 단계적으로 보여준다.

---

## 추천 제목 (선택)

- 웹 서버 라우팅 구조의 진화
- DispatcherServlet이 등장하기까지
- Model1에서 Spring MVC로
- 웹 서버 구조가 발전한 이유
