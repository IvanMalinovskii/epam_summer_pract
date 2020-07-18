`use strict`;

let modalWindow = null;

document.addEventListener('DOMContentLoaded', function() {
    modalWindow = new Modal();
    modalWindow.attach(document.body);
    createHeader('guest');

    request.getPage(appConfig.domain + 'front/pages/repertoire.html')
    .then(text => {
        document.querySelector('.app').innerHTML = text;
        return request.doGetJson(appConfig.domain + 'plays');
    })
    .then(json => {
        let cardContainer = document.querySelector('.play-cards');
        json.forEach(play =>  {
            let playCard = new PlayCard(play);
            playCard.cardElement.addEventListener('click', () => {
                request.getPage(appConfig.domain + 'front/pages/play.html')
                    .then(text => {
                        document.querySelector('.app').innerHTML = text;
                        return request.doGetJson(appConfig.domain + 'plays?id=' + playCard.cardElement.id);
                    })
                    .then(json => console.log(json));
            })
            cardContainer.append(playCard.cardElement);
        });
    })
    .catch(error => console.log('error ' + error));
});


function createHeader(role) {
    let header = document.querySelector('.header');
    let fill = function(role, items) {
        header.querySelector('.role-name').innerText = role;
        let list = header.querySelector('.authorization');
        items.forEach(item => list.insertAdjacentHTML('beforeend', `
            <li id="${item.id}" class="authorization-item">${item.text}</li>
        `));
    }
    if(role === 'guest'){
        //fill('guest', [{id='sign-in', text='sign in'}, {id='sign-up', text='sign up'}]);
        fill('guest', [{id: 'sign-in', text: 'sign in'}, {id: 'sign-up', text: 'sign up'}]);
        document.getElementById('sign-in').addEventListener('click', ()=>{
            modalWindow.show('sign in');
        });
        document.getElementById('sign-up').addEventListener('click', ()=>{
            modalWindow.show('sign up');
        });
    }
    else if(role === 'user') {
        fill('user', [{id: 'log-out', text: 'log out'}]);
    }
}