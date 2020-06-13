import axios from 'axios';
// import { JSEncrypt } from "jsencrypt";

// CryptoJS
// var CryptoJS = require("crypto-js");
// var message = "123151563";  // id
// var key = "ZouFzBswbrhzhbKONckeJzCCyfoMiPWF"; // key
// var encrypt = CryptoJS.AES.encrypt(message, key); // 암호화 method
// console.log(encrypt.toString());



const instance = axios.create({
  baseURL: "http://13.125.236.67:8080",
  // baseURL: "http://124.50.218.182:8080/test-server",
  // baseURL: "http://192.168.219.108:8080/test-server",
  // baseURL: "http://localhost:8080"
});


export const sendAuthInfo = function(userData) {
  window.alert(JSON.stringify(userData));
  return instance.post("/getKakaoAuth", userData);
};


export const kakaoLogin = function() {
  var unEncryptedUserId;
  var userNickName;
  var encryptedUserId;
  var authObj = {};
  var key;

  // var kakaoRes = {
  //   id: "13115813224242343413252525326465476",
  //   properties: {
  //     nickname: "혁"
  //   }
  // }

  //   unEncryptedUserId = kakaoRes.id;
  //   userNickName = kakaoRes.properties.nickname;
  //   encryptedUserId = null;

  //   instance
  //     .post("/keyRequest")
  //     .then(function (res) {
  //       // console.log(JSON.stringify(res.data));
  //       return res.data;
  //     })
  //     .then(function (parsedRes) {
  //       // console.log(JSON.stringify(parsedRes));
  //       // console.log(parsedRes.pubKey);
  //       // console.log(unEncryptedUserId);

  //       key = parsedRes.key;
  //       encryptedUserId = CryptoJS.AES.encrypt(unEncryptedUserId, key).toString(); // 암호화 method
  //       // console.log(CryptoJS.AES.encrypt(unEncryptedUserId, key));
  //       // console.log(key);
  //       // console.log(encryptedUserId);
  //       if (parsedRes.key !== null) {
  //         window.localStorage.setItem("key", key);
  //       }
  //       authObj = {
  //         encryptedUserId: encryptedUserId,
  //         nickname: userNickName,
  //         cliKey: key
  //       };
  //       // console.log(authObj);
  //       return authObj;
  //     })
  //     .then(function (authObj) {
  //       instance.post("/joinAndLoginRequest", authObj)
  //         .then(function (loginRes) {
  //           return loginRes.data;
  //         })
  //         .then(function (parsedRes) {
  //           if (parsedRes.loggedIn === true) {
  //             window.alert("로그인 성공\n" + JSON.stringify(authObj));
  //             window.localStorage.setItem("id", encryptedUserId);
  //             window.localStorage.setItem("nickname", userNickName);
  //           }
  //           // console.log(parsedRes.loggedIn);
  //           // location.reload();
  //           return parsedRes;
  //         }).catch((error) => {
  //           // console.log(error);
  //           window.alert(error);
  //         });
  //     })
  //     .catch((error) => {
  //       // console.log(error);
  //       window.alert(error);
  //     });


  let loginOptions = {
    authTypes: [1],
  };
  
  let failed = function () {
    window.alert("로그인에 실패했습니다.");
  }
  let success = function (kakaoRes){
    userId = kakaoRes.id;
    userNickName = kakaoRes.properties.nickname;
    let authObj = {
          userId: userId,
          nickname: userNickName,
        };
    instance.post("/joinAndLoginRequest", authObj)
        .then(function (loginRes) {
          return loginRes.data;
        })
        .then(function (parsedRes) {
          if (parsedRes.loggedIn === true) {
            window.alert("로그인 성공\n" + JSON.stringify(authObj));
            window.localStorage.setItem("id", userId);
            window.localStorage.setItem("nickname", userNickName);
          }
          // console.log(parsedRes.loggedIn);
          location.reload();
          return parsedRes;
        }).catch((error) => {
          // console.log(error);
          window.alert(error);
        });
  KakaoCordovaSDK.login(loginOptions, success, failed);
  }
}
// var sendAuthObjToServe = async function(unEncryptedUserId) {
// 	await getAuthObj(unEncryptedUserId);
// };

// var getEncryptedId = async function (unEncryptedUserId) {
//   var encryptedUserId = await
// }