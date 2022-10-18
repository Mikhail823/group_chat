$(function(){
    $('.send_message').on('click', function(){
        $.get('/init', {}, function(response){
            alert(response);
        });
    });
});