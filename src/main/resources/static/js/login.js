const form = document.forms['fetch']; // document.forms.fetch;

const postForm = (body) => {

    return fetch('http://localhost:8081/user/login', {
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json'
        },
        body
    })
    //     .catch((error) => {
    //     if(error.status === 403) {
    //         // window.location.href = '/login'; // redirect
    //     }
    // })
}

const handleSubmit = async (e) => {
    e.preventDefault();

    const body = JSON.stringify(Object.fromEntries(new FormData(e.target)));

    const response = await postForm(body);
    const data = await response.json();
    console.log(data);

    // 토큰 처리
    if(data.token) {
        // 로컬스토리지에 토큰 저장
        localStorage.setItem('ACCESS_TOKEN', data.token);
        window.location.href = '/index';
    }

}

form.addEventListener('submit', handleSubmit);