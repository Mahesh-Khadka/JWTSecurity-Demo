// auth.js
async function checkAuth() {
    const token = localStorage.getItem('jwtToken');
    if (!token) {
        window.location.href = '/login.html';
        return;
    }

    try {
        const res = await fetch('/api/auth/me', {
            headers: { 'Authorization': 'Bearer ' + token }
        });
        if (!res.ok) {
            localStorage.removeItem('jwtToken');
            window.location.href = '/login.html';
        }
    } catch (err) {
        localStorage.removeItem('jwtToken');
        window.location.href = '/login.html';
    }
}

// Call the function immediately
if (window.location.pathname.endsWith("home.html")) {
  checkAuth();
}
