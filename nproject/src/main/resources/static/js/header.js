document.addEventListener('DOMContentLoaded', function () {
    // Timer Logic
    let timeLeft = 3600; // 60 minutes in seconds
    const timerElement = document.getElementById('sessionTimer');
    const extendBtn = document.getElementById('extendTimerBtn');

    function updateTimer() {
        if (!timerElement) return;

        const minutes = Math.floor(timeLeft / 60);
        const seconds = timeLeft % 60;

        timerElement.textContent = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;

        if (timeLeft > 0) {
            timeLeft--;
        } else {
            // Timer expired logic (optional: auto logout)
            clearInterval(timerInterval);
            alert("세션이 만료되었습니다. 다시 로그인해주세요.");
            window.location.href = '/logout';
        }
    }

    let timerInterval = setInterval(updateTimer, 1000);
    updateTimer(); // Initial call

    if (extendBtn) {
        extendBtn.addEventListener('click', function () {
            timeLeft = 3600;
            updateTimer();
        });
    }

    // Logout Modal Logic
    const logoutBtn = document.getElementById('logoutBtn');
    const logoutModal = document.getElementById('logoutModal');
    const cancelLogoutBtn = document.getElementById('cancelLogoutBtn');
    const confirmLogoutBtn = document.getElementById('confirmLogoutBtn');

    if (logoutBtn) {
        logoutBtn.addEventListener('click', function () {
            logoutModal.style.display = 'flex';
        });
    }

    if (cancelLogoutBtn) {
        cancelLogoutBtn.addEventListener('click', function () {
            logoutModal.style.display = 'none';
        });
    }

    if (confirmLogoutBtn) {
        confirmLogoutBtn.addEventListener('click', function () {
            window.location.href = '/logout';
        });
    }

    // Close modal when clicking outside
    window.addEventListener('click', function (event) {
        if (event.target === logoutModal) {
            logoutModal.style.display = 'none';
        }
    });
});
