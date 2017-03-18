//Генерим пароль
function generatePass(id) {
    var el = document.getElementById(id);
    el.type = 'text';
    el.value = Math.random().toString(36).slice(2, 2 + Math.max(1, Math.min(10, 10)));
}