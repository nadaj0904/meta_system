function handleUpdate() {
    try {
        var stdWordNameElement = document.getElementById('stdWordName');
        if (!stdWordNameElement) {
            console.error('stdWordName element not found');
            return;
        }

        var stdWordName = stdWordNameElement.value;
        if (!stdWordName) {
            alert("표준 단어명을 입력하세요.");
            return;
        }

        var useYnElement = document.querySelector('input[name="useYn"]:checked');
        var useYn = useYnElement ? useYnElement.value : 'Y';

        var wordIdElement = document.getElementById('wordId');
        var engAbbrNameElement = document.getElementById('engAbbrName');
        var engFullNameElement = document.getElementById('engFullName');
        var definitionElement = document.getElementById('definition');
        var domainTypeElement = document.getElementById('domainType');

        var data = {
            wordId: wordIdElement ? wordIdElement.value : null,
            stdWordName: stdWordName,
            engAbbrName: engAbbrNameElement ? engAbbrNameElement.value : '',
            engFullName: engFullNameElement ? engFullNameElement.value : '',
            definition: definitionElement ? definitionElement.value : '',
            domainType: domainTypeElement ? domainTypeElement.value : '',
            useYn: useYn
        };

        fetch('/std-word/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(response => response.text())
            .then(result => {
                if (result === 'SUCCESS') {
                    alert('수정되었습니다.');
                    // Send message to parent window
                    if (window.parent) {
                        window.parent.postMessage({ action: 'reloadAndClose' }, '*');
                    }
                } else {
                    alert('수정 중 오류가 발생했습니다.');
                }
            })
            .catch(error => {
                console.error('Error during fetch:', error);
                alert('요청 처리 중 오류가 발생했습니다.');
            });
    } catch (e) {
        console.error('Unexpected error in handleUpdate:', e);
        alert('예기치 않은 오류가 발생했습니다.');
    }
}

function handleCancel() {
    if (window.parent) {
        window.parent.postMessage({ action: 'closeModal' }, '*');
    }
}
