const container = document.getElementById("root");
const content = document.createElement("div");
const ajax = new XMLHttpRequest();
const NEWS_URL = "https://api.hnpwa.com/v0/news/1.json";
const CONTENT_URL = "https://api.hnpwa.com/v0/item/@id.json";

function getData(url) {
  ajax.open("GET", url, false);
  ajax.send();

  return JSON.parse(ajax.response);
}

function newsFeed() {
  const newsFeed = getData(NEWS_URL);
  const newsList = [];
  newsList.push("<ul>");
  for (let i = 0; i < 10; i++) {
    const div = document.createElement("div");
    newsList.push(
      `<li>
        <a href='#${newsFeed[i].id}'>
          ${newsFeed[i].title} [count: ${newsFeed[i].comments_count}]
        </a>
    </li>
    `
    );
  }
  newsList.push("</ul>");
  container.innerHTML = newsList.join("");
}

function newsDetail() {
  const id = location.hash.substr(1);
  const newsContent = getData(CONTENT_URL.replace("@id", id));
  const title = document.createElement("h1");
  container.innerHTML = `
    <h1>${newsContent.title}</h1>
    <div>
      <a href='#'>목록으로</a>
    </div>
  `;
}

//라우터에서 화면 전환하기 구현
function router() {
  const routePath = location.hash;
  if (routePath === "") {
    newsFeed();
  } else {
    newsDetail();
  }
}

window.addEventListener("hashchange", router);
router();

/**
 * DOM API를 이용해서 UI 구조가 잘 드러나지 않는 문제점을
 *
 */
