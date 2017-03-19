//Генерим пароль
function generatePass(id) {
    var el = document.getElementById(id);
    el.type = 'text';
    el.value = Math.random().toString(36).slice(2, 2 + Math.max(1, Math.min(10, 10)));
}

jQuery(document).ready(function() {
    $('.list-to-top').click(function(e){
        e.preventDefault();
        console.log('TOP');
    });
    $('.list-to-bottom').click(function(e){
        e.preventDefault();
        console.log('BOTTOM');
    });
    replaceSpaceByNewline('matrix');
});
