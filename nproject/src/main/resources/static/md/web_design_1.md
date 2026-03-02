# 🌌 Antigravity Design System: Ruleset

이 문서는 **'Antigravity(반중력)'** 디자인 컨셉을 정의합니다. 모든 UI 요소는 중력을 거슬러 부유하는 느낌을 주어야 하며, 아래의 색상 및 물리 법칙을 엄격히 준수해야 합니다.

---

## 모든 html과 css파일에 아래 컨셉을 적용해서 코딩할것

모든 html과 css파일에 아래 디자인 컨셉을 적용해서 개발할것


## 1. Color Palette (Core)

| Role | Hex Code | Description |
| :--- | :--- | :--- |
| **Deep Base**		| `#2550AA` | 중력의 중심. 배경 또는 가장 무거운 요소 (Deep Blue) |
| **Floating Surface** 	| `#B1CDFF` | 떠오르는 레이어. 버튼, 강조 카드 (Soft Blue) |
| **Airy Highlight** 	| `#EFF4FF` | 반중력의 정점. 최상단 텍스트, 얇은 보더 (Sky White) |


---

## 2. Elevation Strategy: "The Buoyancy"

물체가 수면 위로 떠오르는 물리 법칙을 시각화합니다.

* **Background:** `#2550AA`를 메인 배경으로 사용하되, 상단으로 갈수록 밝아지는 그라디언트를 권장합니다.
* **Surface (Level 1):** `#B1CDFF`에 `opacity: 0.2` 정도를 적용하고 `backdrop-filter: blur()`를 결합하여 유리처럼 투명하게 표현합니다.
* **Top Layer (Level 2):** 가장 중요한 정보는 `#EFF4FF` 색상을 사용하여 배경과 확연히 대비되게 배치합니다.

---

## 3. Visual Ruleset

### 🪐 Elevation & Shadows

/** 단순한 검은색 그림자가 아닌, 배경색에 기반한 **'Glow Shadow'**를 사용합니다. */
/** **Low Lift:** `box-shadow: 0 4px 15px rgba(177, 205, 255, 0.2); */
/** **High Lift:** `box-shadow: 0 20px 40px rgba(37, 80, 170, 0.4), 0 0 20px rgba(239, 244, 255, 0.2);` */

### ✨ Borders & Glassmorphism
* 모든 카드는 테두리가 뚜렷하지 않아야 합니다.
* `border: 1px solid rgba(239, 244, 255, 0.3);`를 사용하여 빛이 맺히는 느낌만 전달합니다.

---

## 4. Interaction (Anti-gravity Physics)

### 1) Levitation (Hover)
* 요소에 호버 시 `transform: translateY(-10px);` 적용.
* 배경색이 `#B1CDFF`에서 `#EFF4FF`로 서서히 밝아지며 광채가 강해집니다.

### 2) Smooth Transition
* 모든 움직임은 `cubic-bezier(0.23, 1, 0.32, 1)` (Ease-out Quint)을 사용하여 부드럽고 가볍게 반응합니다.

---

## 5. Implementation Guidance for AI
- 이 규칙을 적용할 때, CSS 변수(`--color-deep`, `--color-surface` 등)를 먼저 선언하세요.
- 모든 컴포넌트는 `backdrop-filter`를 적극적으로 활용하여 투명도를 유지해야 합니다.
- 텍스트 가독성을 위해 배경이 짙은 곳에서는 항상 `#EFF4FF`를 우선 사용하세요.


