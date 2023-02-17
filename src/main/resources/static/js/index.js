import {signin, signout} from "./api.js";

const logout = document.querySelector('#logout');
logout.addEventListener('click', signout);