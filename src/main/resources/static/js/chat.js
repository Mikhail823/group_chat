$(function(){
    let initApplication = function(){
        $('.messages_and_users').css({display: 'flex'});
        $('.controls').css({display: 'flex'});
    };

    let registrationUser = function(name){
        $.post('/auth', {name: name}, function(response){
            if(response.result){
                initApplication();
            }
        });
    };
        $.get('/init', {}, function(response){
            if(!response.result){
                let name = prompt('Введите имя пользователя:');
                registrationUser(name);
                return;
            }else{
                initApplication();
            }
        });
 });