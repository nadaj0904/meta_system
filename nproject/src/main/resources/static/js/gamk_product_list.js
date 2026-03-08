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

// 로컬 템플릿 데이터 정의
const localTemplates = [
    { id: 1, typeName: "약관", title: "교보생명 약관 템플릿 01", url: "/img/product/kyobo/methods_thum/01.PNG" },
    { id: 2, typeName: "약관", title: "교보생명 약관 템플릿 02", url: "/img/product/kyobo/methods_thum/02.PNG" },
    { id: 3, typeName: "약관", title: "교보생명 약관 템플릿 03", url: "/img/product/kyobo/methods_thum/03.PNG" },
    { id: 4, typeName: "약관", title: "교보생명 약관 템플릿 04", url: "/img/product/kyobo/methods_thum/04.PNG" },
    { id: 5, typeName: "약관", title: "교보생명 약관 템플릿 05", url: "/img/product/kyobo/methods_thum/05.PNG" },
    { id: 6, typeName: "약관", title: "교보생명 약관 템플릿 06", url: "/img/product/kyobo/methods_thum/06.PNG" },
    { id: 7, typeName: "약관", title: "교보생명 약관 템플릿 07", url: "/img/product/kyobo/methods_thum/07.PNG" },
    { id: 8, typeName: "약관", title: "교보생명 약관 템플릿 08", url: "/img/product/kyobo/methods_thum/08.PNG" },
    { id: 9, typeName: "약관", title: "교보생명 약관 템플릿 09", url: "/img/product/kyobo/methods_thum/09.PNG" },
    { id: 10, typeName: "약관", title: "교보생명 약관 템플릿 10", url: "/img/product/kyobo/methods_thum/10.PNG" },
    { id: 11, typeName: "약관", title: "교보생명 약관 템플릿 11", url: "/img/product/kyobo/methods_thum/11.PNG" },
    { id: 12, typeName: "약관", title: "교보생명 약관 템플릿 12", url: "/img/product/kyobo/methods_thum/12.PNG" },
    { id: 13, typeName: "약관", title: "교보생명 약관 템플릿 14", url: "/img/product/kyobo/methods_thum/14.PNG" },
    { id: 14, typeName: "약관", title: "교보생명 약관 템플릿 15", url: "/img/product/kyobo/methods_thum/15.PNG" },
    { id: 15, typeName: "약관", title: "교보생명 약관 템플릿 16", url: "/img/product/kyobo/methods_thum/16.PNG" },
    { id: 16, typeName: "약관", title: "교보생명 약관 템플릿 17", url: "/img/product/kyobo/methods_thum/17.PNG" },
    { id: 17, typeName: "상품요약서", title: "교보생명 요약서 01", url: "/img/product/kyobo/summary_thum/01.png" },
    { id: 18, typeName: "상품요약서", title: "교보생명 요약서 02", url: "/img/product/kyobo/summary_thum/02.png" },
    { id: 19, typeName: "사업방법서", title: "교보생명 방법서 01", url: "/img/product/kyobo/terms_thum/01.png" }
];

let currentPage = 1;
const itemsPerPage = 15;
let filteredTemplates = [];

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
        article.style.cssText = 'background: rgba(255, 255, 255, 0.05); border-radius: 8px; overflow: hidden; display: flex; flex-direction: column; cursor: pointer; transition: transform 0.2s, box-shadow 0.2s;';
        article.onmouseover = () => { article.style.transform = 'translateY(-5px)'; article.style.boxShadow = '0 10px 20px rgba(0,0,0,0.2)'; };
        article.onmouseout = () => { article.style.transform = 'translateY(0)'; article.style.boxShadow = 'none'; };

        article.innerHTML = `
            <div style="width: 100%; height: 350px; overflow: hidden; background-color: #fff; display: flex; align-items: flex-start; justify-content: center;">
                <img src="${card.url}" alt="${card.title}" style="width: 100%; height: auto; object-fit: cover; object-position: top; min-height: 100%;">
            </div>
            <div class="product-card__body" style="padding: 1.5rem; text-align: center;">
                <h3 style="font-size: 1.1rem; font-weight: 700; margin-bottom: 0.5rem; word-break: keep-all;">${card.title}</h3>
                <p style="font-size: 0.8rem; color: #B1CDFF; margin-bottom: 1rem;">[${card.typeName}]</p>
                <button class="product-card__btn" style="width: 100%; border: none; padding: 0.5rem; border-radius: 4px; background: rgba(255,255,255,0.1); color: #fff; cursor: pointer;" onclick="handleDownload(${card.id}, '${card.url}')">
                    <i class="fa-solid fa-download product-card__icon"></i>
                    <span>다운로드</span>
                </button>
            </div>
        `;
        cardContainer.appendChild(article);
    });
}

function renderPagination(totalItems) {
    const paginationContainer = document.getElementById('pagination-container');
    paginationContainer.innerHTML = '';

    const totalPages = Math.ceil(totalItems / itemsPerPage);
    if (totalPages <= 1) return;

    // Previous Button
    const prevBtn = document.createElement('button');
    prevBtn.innerHTML = '&lt;';
    prevBtn.style.cssText = 'padding: 0.5rem 1rem; border: none; background: rgba(255,255,255,0.1); color: #fff; cursor: pointer; border-radius: 4px;';
    prevBtn.disabled = currentPage === 1;
    if (currentPage === 1) prevBtn.style.opacity = '0.5';
    prevBtn.onclick = () => {
        if (currentPage > 1) {
            currentPage--;
            updatePageView();
        }
    };
    paginationContainer.appendChild(prevBtn);

    // Page Buttons
    for (let i = 1; i <= totalPages; i++) {
        const pageBtn = document.createElement('button');
        pageBtn.textContent = i;
        pageBtn.style.cssText = `padding: 0.5rem 1rem; border: none; cursor: pointer; border-radius: 4px; ${i === currentPage ? 'background: #B1CDFF; color: #0b1120; font-weight: bold;' : 'background: rgba(255,255,255,0.1); color: #fff;'}`;
        pageBtn.onclick = () => {
            currentPage = i;
            updatePageView();
        };
        paginationContainer.appendChild(pageBtn);
    }

    // Next Button
    const nextBtn = document.createElement('button');
    nextBtn.innerHTML = '&gt;';
    nextBtn.style.cssText = 'padding: 0.5rem 1rem; border: none; background: rgba(255,255,255,0.1); color: #fff; cursor: pointer; border-radius: 4px;';
    nextBtn.disabled = currentPage === totalPages;
    if (currentPage === totalPages) nextBtn.style.opacity = '0.5';
    nextBtn.onclick = () => {
        if (currentPage < totalPages) {
            currentPage++;
            updatePageView();
        }
    };
    paginationContainer.appendChild(nextBtn);
}

function updatePageView() {
    const startIdx = (currentPage - 1) * itemsPerPage;
    const endIdx = startIdx + itemsPerPage;
    const pageItems = filteredTemplates.slice(startIdx, endIdx);

    renderCardList(pageItems);
    renderPagination(filteredTemplates.length);
}

// 검색 버튼 클릭 이벤트
searchBtn.addEventListener('click', () => {
    // 실제 검색 로직을 대체하여 로컬 템플릿의 필터링 로직 구현 
    // (현재는 추가 필터링 없이 전체 페이지네이션 노출)
    filteredTemplates = [...localTemplates];
    currentPage = 1;
    updatePageView();
});

// 다운로드 버튼 클릭 이벤트 처리
window.handleDownload = function (id, url) {
    console.log('다운로드 실행. 요청 파일:', url);
    alert('파일을 다운로드합니다: ' + url);
};

// 페이지 로드 시 자동 검색 1회 실행
searchBtn.click();

