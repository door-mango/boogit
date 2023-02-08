const apiButton = document.getElementById("searchBook");
apiButton.addEventListener("click", callBookApi);

async function callBookApi() {
    const bookName = document.getElementById("search").value;
    const response = await fetch(
        `http://localhost:8081/kakao?query=${bookName}`
    );
    const bootInfo = await response.json();
    console.log("book Info", bootInfo);
}