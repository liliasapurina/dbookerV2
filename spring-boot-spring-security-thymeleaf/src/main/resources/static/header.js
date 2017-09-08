function logout() {
    $.post(
        "/logout",
        ""
    );
    location.href = "/";
}