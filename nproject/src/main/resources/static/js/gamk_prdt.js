/**
 * 보험사 상품관리 공통 JavaScript
 * 파일명: gamk_prdt.js
 * 설명: 상품조회, 등록, 상세, 수정 화면에 적용되는 공통 유틸리티 및 이벤트 바인딩
 */

const GamkPrdt = {
    // 1. Toast Notification 기반 에러/성공 메시지 알림
    showToast: function (message, type = 'success') {
        let container = document.querySelector('.toast-container');
        if (!container) {
            container = document.createElement('div');
            container.className = 'toast-container';
            document.body.appendChild(container);
        }

        const toast = document.createElement('div');
        toast.className = `toast ${type === 'error' ? 'error' : ''}`;

        const icon = type === 'error' ? '<i class="fa-solid fa-circle-exclamation"></i>' : '<i class="fa-solid fa-circle-check"></i>';
        toast.innerHTML = `${icon} <span>${message}</span>`;

        container.appendChild(toast);

        // 3초 후 제거
        setTimeout(() => {
            toast.style.opacity = '0';
            toast.style.transform = 'translateY(20px)';
            setTimeout(() => toast.remove(), 400); // transition 시간 0.4s 후 요소 삭제
        }, 3000);
    },

    // 2. 입력 Validation 로직
    validateRequired: function (containerId) {
        const container = document.getElementById(containerId) || document;
        const requiredInputs = container.querySelectorAll('[required]');

        for (const input of requiredInputs) {
            if (!input.value.trim()) {
                const label = input.closest('.form-group')?.querySelector('.form-label')?.innerText.replace('*', '').trim() || '필수 항목';
                this.showToast(`${label}을(를) 입력해주세요.`, 'error');
                input.focus();

                // 에러 하이라이트 효과 (Glow)
                input.style.boxShadow = '0 0 0 3px rgba(242, 101, 34, 0.3)';
                input.style.borderColor = 'var(--color-primary-point)';

                setTimeout(() => {
                    input.style.boxShadow = '';
                    input.style.borderColor = '';
                }, 2000);

                return false;
            }
        }
        return true;
    },

    // 3. 시작일 - 종료일 정합성 검사
    validateDateRange: function (startId, endId) {
        const startDate = document.getElementById(startId)?.value;
        const endDate = document.getElementById(endId)?.value;

        if (startDate && endDate) {
            if (startDate > endDate) {
                this.showToast('판매 종료일은 판매 시작일보다 이후여야 합니다.', 'error');
                document.getElementById(endId).focus();
                return false;
            }
        }
        return true;
    },

    // 4. 대표상품 검색 모달 띄우기 (Iframe 접근 방식 시뮬레이션)
    openRepPrdtSearchModal: function (callbackFunc) {
        // 실제 구현 시 iframe modal 로직 필요
        // 현재는 예시 데이터로 프로토타이핑 동작 모의
        const result = prompt("검색할 대표상품코드 또는 명칭을 입력하세요. (예시: REP001 입력 시 자동 선택)");
        if (result === 'REP001' || result === '테스트대표상품') {
            if (typeof callbackFunc === 'function') {
                callbackFunc({ rep_prdt_cd: 'REP001', rep_prdt_nm: '슈퍼라이프 암보험(대표)' });
                this.showToast('대표상품 선택 완료');
            }
        } else if (result) {
            this.showToast('검색된 대표상품이 없습니다.', 'error');
        }
    },

    // 5. 서버 통신 (Fetch) 공통 래퍼 (에러/로딩 처리 포함)
    fetchData: async function (url, options = {}) {
        // 옵션 바인딩
        const defaultHeaders = {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        };

        const config = {
            ...options,
            headers: { ...defaultHeaders, ...options.headers }
        };

        // TODO: Full-screen Glassmorphism Loader 표시 로직 추가 가능

        try {
            const response = await fetch(url, config);
            if (!response.ok) {
                throw new Error(`HTTP Error: ${response.status}`);
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Fetch Error:', error);
            this.showToast('데이터 처리 중 오류가 발생했습니다.', 'error');
            throw error;
        } finally {
            // TODO: Loader 제거 로직
        }
    }
};

// DOM 로드 시 초기 이벤트 바인딩
document.addEventListener('DOMContentLoaded', () => {
    // hover levitation 효과를 주고싶은 요소가 동적으로 생기지 않는다면 여기서 이벤트 바인딩도 가능
});
