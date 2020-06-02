import axios from 'axios';
import { JSEncrypt } from "jsencrypt";

const instance = axios.create({
  baseURL: "http://13.125.236.67:8080",
  // baseURL: "http://124.50.218.182:8080/test-server",
  // baseURL: "http://192.168.219.108:8080/test-server",
});


function encryptData(publicKey, data) {
  // New JSEncrypt object
  let encryptor = new JSEncrypt();
  // Setting public key
  encryptor.setPublicKey(publicKey);
  // Encrypted data
  return encryptor.encrypt(data);
}

export const sendAuthInfo = function(userData) {
  window.alert(JSON.stringify(userData));
  return instance.post("/getKakaoAuth", userData);
};

export const kakaoLogin = async function(userData) {
  var unEncryptedUserId = userData.id;
  var encryptedUserId = null;
  await instance
    .post("/keyRequest")
    .then(function(res) {
      // console.log(JSON.stringify(res.data));
      return res.data;
    })
    .then(function(parsedRes) {
      // console.log(JSON.stringify(parsedRes));
      // console.log(parsedRes.pubKey);
      // console.log(unEncryptedUserId);
      encryptedUserId = encryptData(parsedRes.pubKey, unEncryptedUserId);
      // console.log(encryptedUserId);
      if (parsedRes.pubKey !== null) {
        window.localStorage.setItem("pubKey", parsedRes.pubKey);
      }
      var authObj = {
        encryptedUserId: encryptedUserId,
        pubKey: parsedRes.pubKey,
      };
      console.log(authObj);
      return authObj;
    })
    .then(function(authObj) {
      return instance.post("/joinAndLoginRequest", authObj);
    })
    .then(function(loginRes) {
      return loginRes.data;
    })
    .then(function(parsedRes) {
      if (parsedRes.loggedIn === true) {
        window.localStorage.setItem("id", encryptedUserId);
      }
      console.log(parsedRes.loggedIn);
      return parsedRes;
    })
    .catch((error) => {
      console.log(error);
    });
};

// var sendAuthObjToServe = async function(unEncryptedUserId) {
// 	await getAuthObj(unEncryptedUserId);
// };

// var getEncryptedId = async function (unEncryptedUserId) {
//   var encryptedUserId = await
// }

export const autoLoginRequest = function() {
  var authObj = {
    encryptedUserId: window.localStorage.getItem("id"),
    pubKey: window.localStorage.getItem("pubKey"),
  };

  var loginSuccess = instance.post("/loginRequest", authObj);

  return loginSuccess;
};
