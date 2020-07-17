`use strict`;

class PlayCard {
    constructor(playObj) {
        this.cardElement = this._createCardElement(playObj);
    }

    _createCardElement(playObj) {
        let cardElement = document.createElement('section');
        cardElement.className = 'play-card'
        cardElement.insertAdjacentHTML('afterbegin', `
        <div class="play-card__poster">
            <img src="${playObj.img===undefined? "../img/poster-stub.png":playObj.img}" alt="poster">
        </div>
        <h2 class="play-card__title">${playObj.name}</h2>
        <div class="play-card__description">
            <p class="play-card__genre">${playObj.genre}</p>
            <p class="play-card__author">${playObj.author}</p>
        </div>
        <ul class="play-card__dates">
        </ul>
        `);
        let cardDates = cardElement.querySelector('.play-card__dates');
        playObj.dates.forEach(element => {
            cardDates.insertAdjacentHTML('beforeend', `<li class="play-card__li"><a href="#" class="play-card__date">${element}</a></li>`);
        });

        return cardElement;
    }
}