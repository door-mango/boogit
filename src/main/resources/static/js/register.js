const register = () => {

    let bookList;

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

        const selectedItem = bookList[selectedRowIndex];
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
}

register();