$(function(){
    let getMessagesElement = function(message){
            let item = $('<div class="message-item"></div>');
            let header = $('<div class="message-header"></div>');
            header.append($('<div class="datetime">' + message.datetime + '</div>'));
            header.append($('<div class="username">' + message.username + '</div>'));
            let textElement = $('<div class="message-text"></div>');
            textElement.text(message.text);
            item.append(header, text);
            return item;

    };

    let updateMessages = function(){
                $('.messages_list').html('<i>Сообщений нет</i>');
                $.get("/message", {}, function(response){
                    if(response.length == 0){
                        return;
                    }
                    $('.messages_list').html('');
                    for(i in response){
                        let element = getMessagesElement(response[i]);
                        $('.messages_list').append(element);
                    }
                });
    };

    let initApplication = function(){
        $('.messages_and_users').css({display: 'flex'});
        $('.controls').css({display: 'flex'});
        $('.send_message').on('click', function()
                {
                    let message = $('.new_message').val();
                    $.post('/message', {message: message}, function(response){
                        if(response.result) {
                            console.log('YES');
                            $('.new-message').val('');
                        } else {
                            alert('Ошибка :( Повторите попытку позже');
                        }
                    });
                });

                setInterval(updateMessages, 10000);
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