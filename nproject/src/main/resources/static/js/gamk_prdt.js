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

    // 4. 대표상품 검색 모달 띄우기
    openRepPrdtSearchModal: function (callbackFunc) {
        let modal = document.getElementById('repPrdtSearchModal');
        
        // 모달 DOM이 없다면 동적으로 생성
        if (!modal) {
            modal = document.createElement('div');
            modal.id = 'repPrdtSearchModal';
            modal.className = 'glass-modal';
            modal.innerHTML = `
                <div class="glass-modal__content">
                    <div class="glass-modal__header">
                        <h4><i class="fa-solid fa-magnifying-glass"></i> 대표상품 검색</h4>
                        <button class="btn-close" onclick="document.getElementById('repPrdtSearchModal').style.display='none'"><i class="fa-solid fa-xmark"></i></button>
                    </div>
                    <div class="glass-modal__body">
                        <div class="search-box">
                            <input type="text" id="repPrdtSearchInput" class="form-control" placeholder="검색어 입력 후 Enter" onkeyup="if(event.key==='Enter') GamkPrdt.searchRepPrdt()">
                            <button class="btn btn--primary" onclick="GamkPrdt.searchRepPrdt()"><i class="fa-solid fa-search"></i> 검색</button>
                        </div>
                        <div class="search-result-table-wrapper" style="margin-top: 15px; max-height: 250px; overflow-y: auto;">
                            <table class="data-table">
                                <thead>
                                    <tr>
                                        <th>대표코드</th>
                                        <th>대표상품명</th>
                                        <th>선택</th>
                                    </tr>
                                </thead>
                                <tbody id="repPrdtSearchTbody">
                                    <tr><td colspan="3" style="text-align:center;">검색어를 입력하세요.</td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            `;
            document.body.appendChild(modal);

            // 스코프 내에서 사용할 콜백 함수 임시 저장 객체
            window.__repPrdtSelectCallback = callbackFunc;
        } else {
            // 재생성하지 않더라도 콜백 업데이트
            window.__repPrdtSelectCallback = callbackFunc;
            document.getElementById('repPrdtSearchTbody').innerHTML = '<tr><td colspan="3" style="text-align:center;">검색어를 입력하세요.</td></tr>';
            document.getElementById('repPrdtSearchInput').value = '';
        }

        modal.style.display = 'flex';
        setTimeout(() => document.getElementById('repPrdtSearchInput').focus(), 100);
    },

    // 4-1. 대표상품 검색 API 호출 로직
    searchRepPrdt: async function () {
        const keyword = document.getElementById('repPrdtSearchInput').value.trim();
        const tbody = document.getElementById('repPrdtSearchTbody');
        
        try {
            const data = await this.fetchData(`/api/prdt/rep-list?keyword=${encodeURIComponent(keyword)}`);
            tbody.innerHTML = '';

            if(!data || data.length === 0) {
                tbody.innerHTML = '<tr><td colspan="3" style="text-align:center; padding: 15px;">조회된 대표상품이 없습니다.</td></tr>';
                return;
            }

            data.forEach(item => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${item.repPrdtCd}</td>
                    <td style="text-align: left; font-weight: 500;">${item.repPrdtNm}</td>
                    <td><button class="btn btn--secondary" style="padding: 4px 10px; font-size: 0.8rem;" onclick="GamkPrdt.selectRepPrdt('${item.repPrdtCd}', '${item.repPrdtNm.replace(/'/g, "\\'")}')">선택</button></td>
                `;
                tbody.appendChild(tr);
            });
        } catch(error) {
            tbody.innerHTML = '<tr><td colspan="3" style="text-align:center; padding: 15px; color: red;">검색 중 오류가 발생했습니다.</td></tr>';
        }
    },

    // 4-2. 모달 내 상품 선택 시
    selectRepPrdt: function(cd, nm) {
        if(typeof window.__repPrdtSelectCallback === 'function') {
            window.__repPrdtSelectCallback({ rep_prdt_cd: cd, rep_prdt_nm: nm });
        }
        document.getElementById('repPrdtSearchModal').style.display = 'none';
        this.showToast('대표상품 선택 완료');
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
