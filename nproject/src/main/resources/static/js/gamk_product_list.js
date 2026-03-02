// ==========================================
// 1. 타이머 로직 (setInterval)
// ==========================================
let totalTimeInSeconds = 3600; // 60분
const timeDisplayEl = document.getElementById('time-display');
const extendBtnEl = document.getElementById('extend-btn');
let timerInterval;

function updateTimer() {
    const minutes = Math.floor(totalTimeInSeconds / 60);
    const seconds = totalTimeInSeconds % 60;
    timeDisplayEl.textContent =
        String(minutes).padStart(2, '0') + ':' + String(seconds).padStart(2, '0');
}

function startTimer() {
    timerInterval = setInterval(() => {
        if (totalTimeInSeconds > 0) {
            totalTimeInSeconds--;
            updateTimer();
        } else {
            clearInterval(timerInterval);
            alert("세션 시간이 만료되었습니다.");
        }
    }, 1000);
}

extendBtnEl.addEventListener('click', () => {
    totalTimeInSeconds = 3600; // 60분 리셋
    updateTimer();
    console.log("타이머가 60분으로 연장되었습니다.");
});

startTimer();

// ==========================================
// 2. Select 상자 연동 로직
// ==========================================
const insuranceTypeSelect = document.getElementById('sel-insurance-type');
const companySelect = document.getElementById('sel-company');
const productSelect = document.getElementById('sel-product');
const docTypeSelect = document.getElementById('sel-doc-type');
const cardContainer = document.getElementById('card-container');
const searchBtn = document.getElementById('search-btn');

// 보험사 목록 가져오기
function fetchCompanies(insuranceTypeCode = 'ALL') {
    fetch(`/api/products/companies?insuranceTypeCode=${insuranceTypeCode}`)
        .then(res => res.json())
        .then(data => {
            companySelect.innerHTML = '<option value="ALL">전체</option>';
            data.forEach(comp => {
                const opt = document.createElement('option');
                opt.value = comp.companyCode;
                opt.textContent = comp.companyName;
                companySelect.appendChild(opt);
            });
            // 보험사가 변경되었으니 상품 목록도 리셋
            fetchProducts('ALL');
        }).catch(err => console.error(err));
}

// 상품 목록 가져오기
function fetchProducts(companyCode = 'ALL') {
    fetch(`/api/products/list?companyCode=${companyCode}`)
        .then(res => res.json())
        .then(data => {
            productSelect.innerHTML = '<option value="ALL">전체</option>';
            data.forEach(prod => {
                const opt = document.createElement('option');
                opt.value = prod.productId;
                opt.textContent = prod.productName;
                productSelect.appendChild(opt);
            });
        }).catch(err => console.error(err));
}

// 자료 탭(유형) 목록 가져오기
function fetchMaterialTypes() {
    fetch('/api/products/material-types')
        .then(res => res.json())
        .then(data => {
            docTypeSelect.innerHTML = '<option value="ALL">전체</option>';
            data.forEach(type => {
                const opt = document.createElement('option');
                opt.value = type.code;
                opt.textContent = type.name;
                docTypeSelect.appendChild(opt);
            });
        }).catch(err => console.error(err));
}

// ==========================================
// 3. 페이지 로드 시 기본 데이터 호출
// ==========================================
fetchCompanies('ALL');
fetchMaterialTypes();

// 이벤트 리스너 등록
insuranceTypeSelect.addEventListener('change', (e) => {
    fetchCompanies(e.target.value);
});

companySelect.addEventListener('change', (e) => {
    fetchProducts(e.target.value);
});

// ==========================================
// 4. 상품 마케팅 자료 카드 리스트 검색 로직
// ==========================================
function renderCardList(materials) {
    cardContainer.innerHTML = ''; // 초기화

    if (materials.length === 0) {
        cardContainer.innerHTML = '<div style="grid-column: 1/-1; text-align: center; padding: 2rem;">검색된 자료가 없습니다.</div>';
        return;
    }

    materials.forEach(card => {
        // 시맨틱 태그 article 생성
        const article = document.createElement('article');
        article.className = 'product-card';
        article.innerHTML = `
            <div class="product-card__body" style="padding: 1.5rem; display: flex; flex-direction: column; justify-content: center; align-items: center; text-align: center; background: rgba(255, 255, 255, 0.05); border-radius: 8px;">
                <i class="fa-solid ${card.iconClass}" style="font-size: 3rem; color: #B1CDFF; margin-bottom: 1rem;"></i>
                <h3 style="font-size: 1.2rem; font-weight: 700; margin-bottom: 0.5rem; word-break: keep-all;">${card.materialTitle}</h3>
                <p style="font-size: 0.9rem; color: #B1CDFF; margin-bottom: 0.25rem;">${card.companyName}</p>
                <p style="font-size: 0.8rem; color: #EFF4FF; opacity: 0.7; margin-bottom: 1.5rem;">[${card.materialTypeName}] ${card.productName}</p>
                <button class="product-card__btn" onclick="handleDownload(${card.materialId}, '${card.fileUrl}')">
                    <i class="fa-solid fa-download product-card__icon"></i>
                    <span>다운로드</span>
                </button>
            </div>
        `;
        cardContainer.appendChild(article);
    });
}

// 검색 버튼 클릭 이벤트
searchBtn.addEventListener('click', () => {
    const searchParam = {
        insuranceTypeCode: insuranceTypeSelect.value,
        companyCode: companySelect.value,
        productId: productSelect.value === 'ALL' ? null : parseInt(productSelect.value),
        materialTypeCode: docTypeSelect.value
    };

    fetch('/api/products/search', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(searchParam)
    })
        .then(res => res.json())
        .then(data => {
            renderCardList(data);
        })
        .catch(err => {
            console.error('검색 오류:', err);
        });
});

// 다운로드 버튼 클릭 이벤트 처리
window.handleDownload = function (id, url) {
    console.log('다운로드 실행. 요청 파일:', url);
    alert('파일을 다운로드합니다: ' + url);
};

// 페이지 로드 시 자동 검색 1회 실행
searchBtn.click();
