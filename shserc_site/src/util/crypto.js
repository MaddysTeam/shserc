import CryptoJS from 'crypto-js'

const KEY = parseUTF8("1234567890123456");
const IV = parseUTF8("1234567890123456");

export function encrypt(word, input_key, input_iv) {
    let key = KEY
    let iv = IV
    if (input_key) {
        key = parseUTF8(input_key);
        iv = parseUTF8(input_iv);
    }

    let utf8_word = parseUTF8(word);
    let encrypted = CryptoJS.AES.encrypt(utf8_word, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.ZeroPadding
    });

    let result = encrypted.ciphertext.toString(CryptoJS.enc.Base64);  // CryptoJS.enc.Base64.stringify(encryped);
    console.log("--------------- encrypt --------------------")
    console.log(result);
    return result;
}

function parseUTF8(str) {
    return CryptoJS.enc.Utf8.parse(str);
}