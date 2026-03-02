# 📘 개발 매뉴얼 (Developer Manual)


/**

*/
## 1. 개요 (Overview)
- 모바일에서도 적용 가능한 반응형 웹페지로 구현할 것
- 모바일에서의 터치감, 시각적 리듬을 고려하여 코드를 작성

## 2. 프론트엔드 아키텍처 및 화면 구현 방식

### 2.1. 디렉토리 구조 (Static Resources & Templates)
Spring Boot 환경 위에서 동작하며, 정적 자원과 뷰 템플릿이 엄격히 분리되어 있습니다.
- `src/main/resources/templates/`: `Thymeleaf` 기반의 HTML 템플릿 파일이 위치합니다. (`fragments` 폴더를 통해 공통 헤더/푸터 등 모듈화 적용)
- `src/main/resources/static/css/`: 스타일시트 자원 (`common.css` 및 페이지 독립적인 CSS 파일 존재)
- `src/main/resources/static/js/`: 자바스크립트 비즈니스 로직
- `src/main/resources/static/images/` & `img/`: 이미지 에셋

### 2.2. 디자인 시스템 규칙: "Antigravity (반중력)"
모든 HTML과 CSS 코딩에는 **'반중력'** 물리 법칙을 시각화하는 아래의 핵심 디자인 철학이 반영되어야 합니다.

- **색상 팔레트 (Color Palette)**
  - `Deep Base (#2550AA)`: 가장 무거운 요소 및 메인 배경 (상단으로 갈수록 밝아지는 그라디언트 권장)
  - `Floating Surface (#B1CDFF)`: 떠오르는 표면 및 카드 레이어 (`opacity: 0.2` 와 `backdrop-filter: blur()` 결합으로 투명한 글래스모피즘 효과 구현)
  - `Airy Highlight (#EFF4FF)`: 가벼운 요소, 최상단 텍스트, 빛이 맺히는 테두리
  - `Primary Point (#F26522)`: 에너지를 암시하는 강조 포인트 컬러
  - `Foundation (#FFFFFF)`: 깨끗한 여백과 기본 폰트 색상

- **그림자 및 테두리 (Elevation & Borders)**
  - 단순 블랙 그림자 지양. 배경색 기반의 **'Glow Shadow'** 사용 (예: `box-shadow: 0 20px 40px rgba(37, 80, 170, 0.4)...`)
  - 뚜렷한 실선 테두리를 피하고, 빛 반사를 표현하는 가벼운 반투명 보더 적용 (`border: 1px solid rgba(239, 244, 255, 0.3)`)

- **인터랙션 (Anti-gravity Physics)**
  - Hover (Levitation): 마우스 오버 시 `transform: translateY(-10px)` 를 주어 부력이 작용하는 형태로 반응
  - Transition: 극도로 부드러운 움직임을 위해 `ease-out quint` 곡선 적용 (`cubic-bezier(0.23, 1, 0.32, 1)`)

### 2.3. 스크립트(JS) 및 DOM 제어
- Vanilla JS 중심으로 구성하며, DOM 이벤트 위임을 활용하여 성능 최적화.
- 팝업이나 서브 워크플로우 호출 시 **Iframe Modal** 구조 및 `postMessage` 기반의 Message Event 통신(`window.addEventListener('message')`)을 사용하여 부모-자식 창 간의 결합도를 낮춤.

---

## 3. 백엔드 아키텍처 및 개발 표준

본 프로젝트는 Spring Boot 기반이며 `mybatis-spring-boot-starter`를 이용한 Data Access Layer 구조를 사용합니다. 

### 3.1. 네이밍 컨벤션 (Naming Convention)
| 항목 | 표준 내용 | 예시 |
| :--- | :--- | :--- |
| **패키지 명** | 소문자, 단수 명사 사용 | `com.project.board.controller` |
| **클래스 명** | 파스칼 케이스(PascalCase), 명사형 | `BoardController`, `UserService` |
| **인터페이스 명** | 클래스 명 규칙 + 컴포넌트 역할 명시 | `BoardRepository`, `UserMapper` |
| **메서드 명** | 카멜 케이스(camelCase), 동사+명사 조합 | `getBoardList()`, `insertUser()` |
| **변수 명** | 카멜 케이스(camelCase), 직관적 해석이 가능한 문구 | `maxCount`, `boardList` |
| **상수 명** | 모두 대문자, 단어 사이 언더스코어(_) 사용 | `DEFAULT_PAGE_SIZE` |

### 3.2. Mapper 및 데이터베이스 연동 가이드
- **Mapper 인터페이스**: Repository 패키지 하위에 작성하며 `@Mapper`를 사용합니다.
- **Mapper XML 파일**: `src/main/resources/mapper/{기능명}Mapper.xml` 형태로 생성. `application.yml`의 `mybatis.mapper-locations` 경로와 매칭되어야 합니다.
- **SQL 작성 규칙**: 
  - `SELECT`, `FROM`, `WHERE` 등 SQL 예약어는 **대문자** 사용.
  - MyBatis의 동적 SQL 태그 (`<if>`, `<where>`, `<foreach>` 등) 적극 활용.
  - DTO 혹은 resultMap을 적절히 사용하여 SQL의 Snake Case 데이터(예: `user_id`)와 DTO의 Camel Case 프로퍼티(예: `userId`) 간의 매핑을 명확히 처리합니다.

### 3.3. DB 테이블 설계 및 생성 규칙
테이블 스키마 생성 및 관리 시 다음 사항을 엄수합니다.
- 테이블명은 **소문자**를 사용하며, 업무 도메인 단위로 Prefix(`tb_`)를 반드시 붙입니다. (예: `tb_user_`, `tb_mart_`, `tb_hist_`)
- 극단적인 자음 및 모음 축약 등 **약어의 무분별한 사용을 금지**합니다. (예: `usr` (X) -> `user` (O), `plnr` (X) -> `planner` (O))
- PK(주키) 등의 식별자 컬럼에서는 `_seq`, `_no`의 사용을 금지하고, **`_id`**를 사용합니다. (예: `user_no` (X) -> `user_id` (O))
- `~여부(Yes/No)` 성격을 띠는 컬럼은 문자형('Y'/'N') 방식 이외에도 시스템 구조에 따라 **boolean** 사용을 고려합니다.
- **날짜(Date/Timestamp) 컬럼 규격화표**:
  - 생성일 : `created_at`
  - 수정일 : `updated_at`
  - 삭제일 : `deleted_at`
  - 최종 로그인 : `last_login_at`
  - 승인일 : `approved_at`

---

## 4. 주석 및 협업 규약
- **Javadoc 주석 의무화**: 모든 Controller, Service, Mapper의 주요 클래스 및 메서드에는 Javadoc 스타일(`/** ... */`)의 주석을 달아야 하며, **역할, 매개변수(`@param`), 반환 값(`@return`)** 등을 명료하게 기술하여 타 개발자가 즉각 이해할 수 있도록 해야 합니다.
