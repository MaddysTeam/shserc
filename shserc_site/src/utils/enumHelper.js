export class Enum {

    put(key, value) {
        this[key] = value;
        return this;
    }

    get(key) {
        if (!key)
            return ''
        else
            return this[key]

    }

}