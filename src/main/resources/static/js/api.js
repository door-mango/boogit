const ACCESS_TOKEN = 'ACCESS_TOKEN';
const ROOT_URL = 'http://localhost:8081';   // 나중에 yml 파일로 빼기

export const call = (api, method, request) => {
    let headers = new Headers({
        "Content-Type" : "application/json",
    });

    // 로컬 스토리지에서 ACCESS TOKEN 가져오기
    const accessToken = localStorage.getItem('ACCESS_TOKEN');
    if(accessToken) {
        headers.append('Authorization', 'Bearer ' + accessToken);
    }

    let options = {
        headers : headers,
        url : ROOT_URL + api,
        method : method,
    }

    if(request) {
        options.body = JSON.stringify(request);
    }

    return fetch(options.url, options)
            .then((response) => {
                if(!response.ok) {
                    return Promise.reject(response);
                }
                return response.json();
            })

        .catch((error) => {
            if(error.status === 403) {
                window.location.href = '/';
            }
            return Promise.reject(error);
        })
}

export const signin = (userDTO) => {
    return call('/user/login', 'POST', userDTO)
        .then(response => {
            if(response.token) {
                localStorage.setItem('ACCESS_TOKEN', response.token);
                window.location.href = '/index';
            }
        })
}

export const signout = () => {
    localStorage.removeItem('ACCESS_TOKEN');
    window.location.href = '/';
}

export const signup = (userDTO) => {
    return call('/user/signup', 'POST', userDTO);
}
