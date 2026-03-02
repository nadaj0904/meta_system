document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('loginForm');

    if (loginForm) {
        loginForm.addEventListener('submit', function (event) {
            event.preventDefault();

            const userId = document.getElementById('userId').value;
            const password = document.getElementById('password').value;
            const saveId = document.getElementById('saveId').checked;

            const data = {
                userId: userId,
                password: password,
                saveId: saveId
            };

            fetch('/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => response.text())
                .then(result => {
                    if (result === 'SUCCESS') {
                        alert('로그인이 되었습니다');
                        window.location.href = '/product/list';
                    } else {
                        alert('로그인 정보가 올바르지 않습니다.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('로그인 처리 중 오류가 발생했습니다.');
                });
        });
    }
});
