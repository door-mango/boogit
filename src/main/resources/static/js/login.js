import { signin } from "./api.js";

const form = document.forms['fetch']; // document.forms.fetch;

const handleSubmit = async (e) => {
    e.preventDefault();
    const data = new FormData(e.target);
    const email = data.get("email");
    const password = data.get("password");

    signin({email: email, password: password});
}

form.addEventListener('submit', handleSubmit);