function handleUpdate() {
    var stdWordName = document.getElementById('stdWordName').value;
    if (!stdWordName) {
        alert("표준 단어명을 입력하세요.");
        return;
    }

    var data = {
        wordId: document.getElementById('wordId').value,
        stdWordName: stdWordName,
        engAbbrName: document.getElementById('engAbbrName').value,
        engFullName: document.getElementById('engFullName').value,
        definition: document.getElementById('definition').value,
        domainType: document.getElementById('domainType').value,
        useYn: document.querySelector('input[name="useYn"]:checked').value
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
                window.parent.postMessage({ action: 'reloadAndClose' }, '*');
            } else {
                alert('수정 중 오류가 발생했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('오류가 발생했습니다.');
        });
}

function handleCancel() {
    window.parent.postMessage({ action: 'closeModal' }, '*');
}
