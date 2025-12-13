/**
 * Standard Word List functions
 */

// 1. 페이지 크기 변경
function changePageSize(size) {
    var url = new URL(window.location.href);
    url.searchParams.set('size', size);
    url.searchParams.set('page', 1);
    window.location.href = url.href;
}

// 2. 검색 폼 제출 (유저 샘플 적용)
function handleSearchSubmit(event) {
    event.preventDefault(); // 기본 submit 중단

    const form = event.target;
    // .search-input 클래스를 가진 요소를 찾음
    const keywordInput = form.querySelector('.search-input');
    const keyword = keywordInput.value.trim();

    // 간단한 유효성 체크
	/*
    if (keyword.length === 0) {
        alert('검색어를 입력하세요.');
        keywordInput.focus();
        return;
    }
*/
    form.submit(); // 검증 후 실제 submit
}

// 3. 단어 선택
function handleSelection(checkbox) {
    // 하단 입력창들을 배열로 가져옵니다.
    var inputs = [
        document.querySelector('input[name="text1"]'),
        document.querySelector('input[name="text2"]'),
        document.querySelector('input[name="text3"]'),
        document.querySelector('input[name="text4"]')
    ];

    var word = checkbox.value; // 선택된 체크박스의 단어 값

    if (checkbox.checked) {
        // 체크박스가 선택(Checked) 되었을 때

        var filled = false; // 빈 공간을 찾았는지 여부

        for (var i = 0; i < inputs.length; i++) {
            // 빈 입력창을 찾으면 단어를 채우고 반복을 종료합니다.
            if (inputs[i].value === "") {
                inputs[i].value = word;
                filled = true;
                break;
            }
        }

        // 빈 공간이 없어서 채우지 못했다면 경고를 띄우고 체크를 취소합니다.
        if (!filled) {
            alert("최대 4개까지만 선택할 수 있습니다.");
            checkbox.checked = false; // 체크 상태 되돌리기
        }
    } else {
        // 체크박스 선택이 해제(Unchecked) 되었을 때

        // 입력창들 중에서 해당 단어를 찾아 지웁니다.
        for (var i = 0; i < inputs.length; i++) {
            if (inputs[i].value === word) {
                inputs[i].value = ""; // 값 비우기
                shiftInputs(inputs); // 빘 공간이 생기지 않도록 당겨오기
                break;
            }
        }
    }
}

function shiftInputs(inputs) {
    var values = [];
    inputs.forEach(function (input) {
        if (input.value) {
            values.push(input.value);
        }
        input.value = "";
    });

    for (var i = 0; i < values.length; i++) {
        inputs[i].value = values[i];
    }
}
