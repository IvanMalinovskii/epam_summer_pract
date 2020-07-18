`use strict`;

class PlayCard {
    constructor(playObj) {
        this.cardElement = this._createCardElement(playObj);
    }

    _createCardElement(playObj) {
        let cardElement = document.createElement('section');
        cardElement.className = 'play-card';
        cardElement.id = playObj.play.id;
        cardElement.insertAdjacentHTML('afterbegin', `
        <div class="play-card__poster">
            <img src="${playObj.play.imgUrl===undefined? "../img/poster-stub.png":playObj.play.imgUrl}" alt="poster">
        </div>
        <h2 class="play-card__title">${playObj.play.name}</h2>
        <div class="play-card__description">
            <p class="play-card__genre">${playObj.play.genre.name}</p>
            <p class="play-card__author">${playObj.play.author.name}</p>
        </div>
        <ul class="play-card__dates">
        </ul>
        `);

        let cardDates = cardElement.querySelector('.play-card__dates');
        playObj.dates.forEach(element => {
            cardDates.insertAdjacentHTML('beforeend', `<li class="play-card__li"><a href="#" class="play-card__date">${element.date.toString()}</a></li>`);
        });

        return cardElement;
    }
}