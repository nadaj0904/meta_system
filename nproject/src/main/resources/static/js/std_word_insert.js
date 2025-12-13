/**
 * Standard Word Registration functions
 */

function handleInsertFormSubmit(event) {
    // 필수 입력값 검증은 HTML5 required 속성으로 1차 처리됨
    // 추가적인 JavaScript 검증이 필요하면 여기에 작성

    // 저장 확인 메시지
    if (!confirm('표준단어를 등록하시겠습니까?')) {
        event.preventDefault(); // 취소 시 폼 제출 중단
    }
}

function handleCancel(event) {
    if (!confirm('작성을 취소하고 목록으로 돌아가시겠습니까?')) {
        event.preventDefault();
        return false;
    }
    return true;
}
