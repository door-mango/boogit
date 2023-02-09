const apiButton = document.getElementById("searchBook");
apiButton.addEventListener("click", callBookApi);

async function callBookApi() {
    const bookName = document.getElementById("title").value;
    const response = await fetch(
        `http://localhost:8081/kakao?query=${bookName}`
    );
    const bootInfo = await response.json();
    console.log("book Info", bootInfo);
    if(response.ok) {
        let tableTd = ``;
        for(let i = 0; i < bootInfo.documents.length; i++) {
            tableTd += `<tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">`;
            tableTd += `<td class="px-6 py-4"><img src="${bootInfo.documents[i].thumbnail}"/></td>`;
            tableTd += `<td class="px-6 py-4">${bootInfo.documents[i].title}</td>`;
            tableTd += `<td class="px-6 py-4">${bootInfo.documents[i].authors}</td>`;
            tableTd += `<td class="px-6 py-4">${bootInfo.documents[i].publisher}</td>`;
            tableTd += `<td class="px-6 py-4">${bootInfo.documents[i].price}</td>`;
            tableTd += `<td class="px-6 py-4">${bootInfo.documents[i].datetime}</td>`;
            tableTd += `</tr>`;
        }
        document.getElementById("tableBody").innerHTML = tableTd;
    }




}