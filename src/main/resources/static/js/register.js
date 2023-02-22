import { call } from "./api.js";

let bookList;
let selectedItem;

const apiButton = document.querySelector('#searchBook');
apiButton.addEventListener('click', callBookApi);

async function callBookApi() {
    const bookName = document.getElementById('title').value;
    const response = await fetch(
        `http://localhost:8081/kakao?query=${bookName}`
    );
    const result = await response.json();
    bookList = result.documents;
    console.log('bookList', bookList);
    if(response.ok) {
        let tableTd = ``;
        for(let i = 0; i < result.documents.length; i++) {
            tableTd += `<tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">`;
            tableTd += `<td class="px-2 py-2"><img src="${result.documents[i].thumbnail}"/></td>`;
            tableTd += `<td class="px-2 py-2">${result.documents[i].title}</td>`;
            tableTd += `<td class="px-2 py-2">${result.documents[i].authors}</td>`;
            tableTd += `<td class="px-2 py-2">${result.documents[i].publisher}</td>`;
            tableTd += `<td class="px-2 py-2">${result.documents[i].price}</td>`;
            tableTd += `<td class="px-2 py-2">${result.documents[i].datetime.split('T')[0]}</td>`;
            tableTd += `</tr>`;
        }
        document.querySelector('#tableBody').innerHTML = tableTd;
        const contentElement = document.querySelector('tbody');
        contentElement.addEventListener('click', (event) => closeModal(event));
    }
}

async function closeModal(e) {
    console.log('e.target.parentElement', e.target.parentElement);
    const selectedRowIndex = e.target.parentElement.sectionRowIndex;

    selectedItem = bookList[selectedRowIndex];
    console.log('selected item', selectedItem);

    // 모화면으로 선택된 값 전달
    const formInputs = document.querySelectorAll('input');
    console.log('formInputs', formInputs);
    formInputs.forEach((item) => {
        if(item.id.match('title')) {
            item.value = selectedItem.title;
        }

        if(item.id.match('author')) {
            item.value = selectedItem.authors;
        }

        if(item.id.match('publisher')) {
            item.value = selectedItem.publisher;
        }

        if(item.id.match('publication_date')) {
            item.value = selectedItem.datetime.split('T')[0];
        }

        if(item.id.match('price')) {
            item.value = selectedItem.price;
        }

    })

    document.querySelector('#close').click();
}

const form = document.forms['fetch'];

const handleSubmit = async (e) => {
    e.preventDefault();
    const data = new FormData(e.target);
    const title = data.get("title");
    const author = data.get("author");
    const publisher = data.get("publisher");
    const publication_date = data.get("publication_date");
    const price = data.get("price");
    const totalPageNo = data.get("total_page_no");

    call('/book/register', 'POST', {title : title, author : author, publisher : publisher
        , publication_date : publication_date, price : price, totalPageNo : totalPageNo})
        .then((response) => {
            window.location.href="/index";
        }
    );
}

form.addEventListener('submit', handleSubmit);

