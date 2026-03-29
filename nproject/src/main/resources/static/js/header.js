document.addEventListener('DOMContentLoaded', function () {
    let timeLeft = 3600; // 60 minutes in seconds
    const timerElement = document.getElementById('time-display'); // Header 화면의 타이머
    const extendBtn = document.getElementById('extend-btn'); // Header 화면의 연장 버튼
    const logoutBtns = document.querySelectorAll('.logout-btn'); // Header 화면의 로그아웃 아이콘/버튼

    let timerInterval;

    function updateTimer() {
        if (!timerElement) return;

        const minutes = Math.floor(timeLeft / 60);
        const seconds = timeLeft % 60;

        timerElement.textContent = String(minutes).padStart(2, '0') + ':' + String(seconds).padStart(2, '0');

        if (timeLeft > 0) {
            timeLeft--;
        } else {
            clearInterval(timerInterval);
            alert("세션 시간이 만료되었습니다. 안전을 위해 다시 로그인해주세요.");
            window.location.href = '/logout';
        }
    }

    // Initialize timer
    if (timerElement) {
        timerInterval = setInterval(updateTimer, 1000);
        updateTimer(); // Call initially
    }

    // Initialize extend button
    if (extendBtn) {
        extendBtn.addEventListener('click', function () {
            // Backend API call to extend session securely
            fetch('/api/session/extend', {
                method: 'POST'
            })
            .then(response => {
                if (response.ok) {
                    timeLeft = 3600; // Reset local timer to 60 minutes
                    updateTimer();
                    alert('세션 시간이 60분으로 정상 연장되었습니다.');
                } else {
                    alert('세션 연장에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('Session extend error:', error);
                alert('세션 연장 통신 중 오류가 발생했습니다.');
            });
        });
    }

    // Initialize logout buttons
    if (logoutBtns.length > 0) {
        logoutBtns.forEach(btn => {
            btn.addEventListener('click', function () {
                if (confirm('정말 로그아웃 하시겠습니까?')) {
                    window.location.href = '/logout';
                }
            });
        });
    }
});
