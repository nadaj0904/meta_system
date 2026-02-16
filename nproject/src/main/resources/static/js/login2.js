/**
 * 로그인 페이지 스크립트
 * - 로그인 폼 제출 처리
 * - 비밀번호 표시/숨기기 토글
 */
document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('loginForm');
    const passwordToggle = document.getElementById('passwordToggle');
    const passwordInput = document.getElementById('password');

    /* ========================================
     * 비밀번호 표시/숨기기 토글
     * ======================================== */
    if (passwordToggle && passwordInput) {
        passwordToggle.addEventListener('click', function () {
            const icon = this.querySelector('.material-icons-outlined');

            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                icon.textContent = 'visibility_off';
            } else {
                passwordInput.type = 'password';
                icon.textContent = 'visibility';
            }
        });
    }

    /* ========================================
     * 로그인 폼 제출 처리
     * ======================================== */
    if (loginForm) {
        loginForm.addEventListener('submit', function (event) {
            event.preventDefault();

            const userId = document.getElementById('userId').value;
            const password = passwordInput.value;
            const rememberLogin = document.getElementById('rememberLogin').checked;

            const data = {
                userId: userId,
                password: password,
                saveId: rememberLogin
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
                        window.location.href = '/std-dashboard';
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
