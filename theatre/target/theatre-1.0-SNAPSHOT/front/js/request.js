const request = {
    getPage(url) {
        return fetch(url).then(res => res.text());
    },
    doGetJson(url) {
        return fetch(url).then(res => res.json());
    }
};