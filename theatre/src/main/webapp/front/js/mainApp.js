
`use strict`;
let modalWindow = null;
const plays = [
    {
        name: "some play",
        genre: "some genre",
        author: "some author",
        description: "...",
        img: "https://st.kp.yandex.net/im/poster/2/2/4/kinopoisk.ru-Gangster-Squad-2245832--o--.jpg",
        dates: ['08.04', '04.12']
    },
    {
        name: "some play",
        genre: "some genre",
        author: "some author",
        description: "...",
        img: "https://i.imged.com/indian-vintage-old-bollywood-movie-poster-sarfarosh-jeeterndra-sridevi.jpg",
        dates: ['08.04', '04.12']
    },
    {
        name: "some play",
        genre: "some genre",
        author: "some author",
        description: "...",
        dates: ['08.04', '04.12']
    }
];

document.addEventListener('DOMContentLoaded', function() {
    // let playsSection = document.querySelector('.play-cards');
    // plays.forEach(element => {
    //     playsSection.append(new PlayCard(element).cardElement);
    // });
    modalWindow = new Modal();
    modalWindow.attach(document.body);
    createHeader('guest');
    // fetch('../pages/repertoire.html')
    // .then(response => response.text())
    //request.getPage('../pages/repertoire.html')
    request.getPage('http://localhost:8080/theatre/front/pages/repertoir.html')
    .then(text => {
        document.querySelector('.app').innerHTML = text;
        let playCards = document.querySelector('.play-cards');
        plays.forEach(play => playCards.append(new PlayCard(play).cardElement));

        return request.doGetJson('http://localhost:8080/theatre/plays');
    })
    .then(json => {json.forEach(play =>  document.querySelector('.play-cards').append(new PlayCard(play).cardElement))})
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