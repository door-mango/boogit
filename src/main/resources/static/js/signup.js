/**
 *  회원가입 유효성 검사
 *  1. 이메일은 이메일 형식을 갖춰야 함
 *  2. 비밀번호는 숫자/문자/특수문자 포함 형태의 8~15자리
 *  3. 비밀번호와 비밀번호 확인 필드가 같아야 함
 *  4. 이름은 공백없이 2~12자리
 *
 */
const signup = () => {

    const checkRegExp = (regExp, elem, errMsg) => {
        const target = elem.nextElementSibling;

        if(elem.value === '') {
            target.style.visibility = "hidden";
            elem.className = elem.className.replaceAll('red', 'emerald');
            return false;
        }
        target.style.visibility = "visible";

        if(!regExp.test(elem.value)) {
            // class 처리 모듈화 할 것!
            elem.className = elem.className.replaceAll('emerald', 'red');
            target.className = target.className.replaceAll('green', 'red');
            target.innerText = errMsg;
        } else {
            elem.className = elem.className.replaceAll('red', 'emerald');
            target.className = target.className.replaceAll('red', 'green');
            target.innerText = 'correct!';
        }

        if(document.querySelector('#confirm_password').value !== '' && elem.id.match('password')) {
            confirmPassword(document.querySelector('#confirm_password'), '비밀번호가 일치하지 않습니다.');
        }

    }

    const confirmPassword = (elem, errMsg) => {
        const target = elem.nextElementSibling;
        const originPassword = document.querySelector('#password');

        if(elem.value === '') {
            target.style.visibility = "hidden";
            target.className = target.className.replaceAll('red', 'green');
            return false;
        }
        target.style.visibility = "visible";

        if(elem.value !== '' && originPassword.value !== elem.value) {
            elem.className = elem.className.replaceAll('emerald', 'red');
            target.className = target.className.replaceAll('green', 'red');
            target.innerText = errMsg;
        } else {
            elem.className = elem.className.replaceAll('red', 'emerald');
            target.className = target.className.replaceAll('red', 'green');
            target.innerText = 'correct!';
        }

    }

    const activateButton = () => {
        const submitButton = document.querySelector('button');
        const massages = document.querySelectorAll('span');

        if(Array.from(massages).findIndex((item) => item.innerText !== 'correct!') < 0) {
            submitButton.disabled = false;
            submitButton.className = submitButton.className.replaceAll('gray', 'emerald')
            return false;
        }
        submitButton.disabled = true;
        submitButton.className = submitButton.className.replaceAll('emerald', 'gray');

    }


    const selectors = document.querySelectorAll('input');
    console.log('selectors ', selectors);
    Array.prototype.forEach.call(selectors, (item) => {
        item.addEventListener('keyup', function(e) {

            if(e.target.id.match('email')) {
                checkRegExp(/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/, e.target, "이메일 형식에 어긋납니다.");
            }

            if(e.target.id.match('username')) {
                checkRegExp(/^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,12}$/, e.target, "이름은 한글 2~12글자입니다.");
            }

            if(e.target.id.match('password')) {
                checkRegExp(/^(?=.*?[A-Z|a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,15}$/, e.target, "비밀번호는 8~15자리로 숫자/영문자/특수문자를 포함해야합니다");
            }

            if(e.target.id.match('confirm_password')) {
                confirmPassword(e.target, '비밀번호가 일치하지 않습니다.'); // 비밀번호 일치여부 확인
            }

            activateButton();
        });
    })
}

signup();

// 이메일 형식 검사
// `/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/`
// 1. `^[\w]{4,}@` 시작하고 영문자/숫자/_4글자이상나오고 `@` 가 이어지는지
// 2. `@[\w]+` @이후 영문자/숫자/_가 한글자 이상
// 3. `(\.[\w]+){1,3}$` `\.` . (escaping) 이후 영문자/숫자/_가 1~3글자 나오고 끝나는지