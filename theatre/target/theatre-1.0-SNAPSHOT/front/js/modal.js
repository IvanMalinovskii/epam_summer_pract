"use strict";

class Modal {
    constructor(authUrl) {
        this.authUrl = authUrl;
        this.inputs = this._createInputs();
        this.button = createButton();
        this._addButtonHandler();
        this.modal = this._createModal(this.inputs, this.button);
        this.isAttached = false;
        this.validator = new Validator();

        function createButton() {
            let button = document.createElement('BUTTON');
            button.className = 'modal__button';
            button.innerText = 'sign in';
            button.onclick = () => false;

            return button;
        }
    }

    attach(DomElement) {
        DomElement.prepend(this.modal);
        this.isAttached = true;
    }

    show(type) {
        if (!this.isAttached) return new Error('modal is not attached');

        if(this.modal.id != type) {
            this.modal.id = type;
            this.modal.querySelector('.modal__description').innerText = 'do ' + type;
            for(let inp of this.inputs.values()) {
                if (inp.input.classList.contains('hidden'))
                    inp.input.classList.remove('hidden');
                else if (inp.hidden)
                    inp.input.classList.add('hidden');
            }
            this.button.innerText = type;
        }
        
        this.modal.classList.toggle('open');
    }

    _addButtonHandler() {
        this.button.addEventListener('click', () => {
            let isValid = true;
            for(let field of this.inputs.values()) {
                if (field.input.classList.contains('hidden')) continue;
                if (!this.validator.isValid(field.getData())) {
                    field.setInvalid();
                    isValid = false;
                }
            }

            if (isValid) {
                // do request
                alert('+');
            }
        });
    }

    _fillModal(type, inputs) {
        this.modal.id = type;
        this.modal.querySelector('.modal__description').innerText = type;
        this.modal.querySelector('.modal__content').innerHTML = inputs.join('\n');
    }

    _createInputs() {
        return new Map([
            ['login', new Input({id: 'login', type: 'text', description: 'login', hidden: true})],
            ['email', new Input({id: 'email', type: 'email', description: 'email'})],
            ['password', new Input({id: 'password', type: 'password', description: 'password'})],
            ['phone', new Input({id: 'phone', type: 'text', description: 'phone', hidden: true})]
        ]);
    }

    _createModal(inputs, button) {
        let modal = document.createElement('DIV');
        modal.id = 'sign in';
        modal.className = 'modal';
        modal.insertAdjacentHTML('afterbegin', `
            <div class="modal__container">
                <header class="modal__header">
                    <h2 class="modal__description">do sign in</h2>
                    <div class="modal__cancel">&times;</div>
                </header>
                <form class="modal__content">
                </form>
            </div>
        `);
        modal.querySelector('.modal__cancel').addEventListener('click', () => modal.classList.remove('open'));
        let modalContent = modal.querySelector('.modal__content');
        for(let inp of inputs.values())
            modalContent.append(inp.input);
        modalContent.append(button);
        return modal;
    }
}

class Validator {
    constructor() {
        this.regexes = new Map([
            ['password', /(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,15}/],
            ['login', /^[a-zA-Z0-9]+$/],
            ['email', /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/],
            ['phone', /^[+][0-9]{12}/]
        ]);
    }

    areValid(args = []) {
        args.forEach(element => {
            if(element.value.match(this.regexes.get(element.type)))
                return false;
        });
        return true;
    }

    isValid(arg = {}) {
        return arg.value.match(this.regexes.get(arg.type));
    }
}

class Input {
    constructor(params) {
        this.input = creator(params);
        this.input.querySelector('input').addEventListener('click', this.clear.bind(this));
        this.hidden = params.hidden;

        function creator(params) {
            let input = document.createElement('div');
            input.className = 'modal__input';
            if (params.hidden) input.classList.add('hidden');
            input.innerHTML = `
            <label>enter ${params.description}:</label>
            <input id=${params.id} type=${params.type} placeholder="${params.id}.."/> 
            `;

            return input;
        }
    }
    getData() {
        let inputField = this.input.querySelector('input');
        return {
            value: inputField.value,
            type: inputField.id
        };
    }
    clear() {
        let inputField = this.input.querySelector('input');
        inputField.value = '';
        inputField.placeholder = inputField.id + '..';
        inputField.classList.remove('text_incorrect');
    }
    setInvalid() {
        let inputField = this.input.querySelector('input');
        inputField.value = '';
        inputField.placeholder = 'incorrect ' + inputField.id + ' format';
        inputField.classList.add('text_incorrect');
    }
}


