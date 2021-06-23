// 사이드메뉴
// collapsed = 트리메뉴 사용여부
// collapseData = 첫번째 트리메뉴 정보
// subData = 두번째 트리메뉴 정보

const sideNavMainTitleApi = [
  {
    id: "myservice",
    title: "My Service",
    icon: "fa-user",
    collapsed: true,
    subMenu: false,
    ref: "#",
    collapseData: [
      {
        id: "goto",
        title: "출|퇴관리",
        className: "",
        ref: "../myService/inOutManager.erp",
        onClick: "",
        collapsed: false,
      },
      {
        id: "salary",
        title: "급여관리",
        className: "",
        ref: "../myService/empSal.jsp",
        onClick: "",
        collapsed: false,
      },
      {
        id: "personal",
        title: "개인일정",
        className: "",
        ref: "../myService/empSchedule.jsp",
        onClick: "",
        collapsed: false,
      },
    ],
  },
  {
    id: "work",
    title: "Work",
    icon: "fas fa-edit",
    collapsed: true,
    subMenu: true,
    ref: "#",
    collapseData: [
      {
        id: "approval",
        title: "전자결재",
        className: "",
        ref: "#",
        onClick: "",
        collapsed: true,
        subData: [
          {
            id: "approval_my",
            title: "내 결제함",
            className: "",
            ref: "../work/approval_my.jsp",
            onClick: "",
          },
          {
            id: "approval_form",
            title: "결재양식",
            className: "",
            ref: "../work/approval_form.jsp",
            onClick: "",
          },
          {
            id: "approval_send",
            title: "결재신청",
            className: "",
            ref: "../work/approval_send.jsp",
            onClick: "",
          },
          {
        	id: "approval_wb",
        	title: "휴지통",
        	className: "",
        	ref: "../work/approval_wb.jsp",
        	onClick: "",
          },
        ],
      },
      {
        id: "rank",
        title: "인사",
        className: "",
        ref: "#",
        onClick: "",
        collapsed: true,
        subData: [
          {
            id: "group",
            title: "조직도",
            className: "",
            ref: "#",
            onClick: "",
          },
          {
            id: "management",
            title: "사원관리",
            className: "",
            ref: "../work/emp_manage.jsp",
            onClick: "",
          },
          {
            id: "dispatch",
            title: "파견사원",
            className: "",
            ref: "../work/outsideWorker.jsp",
            onClick: "",
          },
        ],
      },
      {
        id: "department",
        title: "부서일정",
        className: "",
        ref: "../work/deptEmp.erp",
        onClick: "",
        collapsed: false,
      },
      {
        id: "admin",
        title: "관리자",
        className: "",
        ref: "../work/manager_log.jsp",
        onClick: "location.href='../work/manager_log.jsp'",
        collapsed: false,
      },
    ],
  },
  {
    id: "conference",
    title: "Conference",
    icon: "fas fa-calendar",
    collapsed: false,
    ref: "../conference/conference.jsp",
  },
  {
    id: "chat",
    title: "Chat",
    icon: "fas fa-comments",
    collapsed: false,
    ref: "#",
  },
];

//정보수정,로그아웃 버튼
const avatarBoxButtonApi = [
  {
    text: "정보수정",
    classname: "avatar_button btn btn-info",
    id: "avatarBoxButton_edit",
    ref: "http://localhost:5000/erpProject_final/main/emp_edit.jsp",
  },
  {
    text: "로그아웃",
    classname: "avatar_button btn btn-danger",
    id: "avatarBoxButton_logout",
  },
];

const sideNav = document.querySelector("#layoutSidenav_nav");

const nav = document.createElement("nav"); //네비게이션 wrapper
const navClasslist = "sb-sidenav accordion sb-sidenav-dark"; //네비게이션 wrapper class
const navId = "sidenavAccordion"; //네비게이션 wrapper id
const navStyle = "background-color: #323232;"; //네비게이션 wrapper style

const avatarBoxCreate = document.createElement("div"); //네비게이션 아바타 warpper
const avatarBoxClassname = "avatar_box"; //네비게이션 아바타 warpper class

const avatarBoxImage = document.createElement("img"); //네비게이션 아바타 img
const avatarBoxImageSrc = "../common/img/woman.png"; //네비게이션 아바타 img src
const avatarBoxImageAlt = "Avatar"; //네비게이션 아바타 img alt
const avatarBoxImageClassname = "avatar"; //네비게이션 아바타 img class

const avatarButtonBoxCreate = document.createElement("div"); //네비게이션 아바타 button
const avatarButtonBoxClassname = "avatar_button_box"; //네비게이션 아바타 button class

const mainMenuWrapper = document.createElement("div");
const mainMenuWrapperClassname = "sb-sidenav-menu";

const mainMenuBox = document.createElement("nav");
const mainMenuBoxClassName = "nav";

const mainMenuTextBoxClassname = "sb-nav-link-icon";

//side nav dom 생성
function generateSideNav() {
  sideNav.appendChild(nav);

  nav.classList = navClasslist;
  nav.id = navId;
  nav.style = navStyle;
  nav.appendChild(avatarBoxCreate);

  avatarBoxCreate.classList = avatarBoxClassname;
  avatarBoxCreate.appendChild(avatarBoxImage);
  avatarBoxImage.src = avatarBoxImageSrc;
  avatarBoxImage.alt = avatarBoxImageAlt;
  avatarBoxImage.classList = avatarBoxImageClassname;

  avatarBoxCreate.appendChild(avatarButtonBoxCreate);
  avatarButtonBoxCreate.classList = avatarButtonBoxClassname;

  avatarBoxButtonApi.map((val) => {
    const avatarButton = document.createElement("button");
    const buttonAction = () => {
      window.location.href = val.ref;
    };
    avatarButton.addEventListener("click", buttonAction);
    avatarButton.classList = val.classname;
    avatarButton.id = val.id;
    avatarButton.innerHTML = val.text;
    avatarButton.click = "d";
    avatarButtonBoxCreate.appendChild(avatarButton);
  });

  nav.appendChild(mainMenuWrapper);
  mainMenuWrapper.classList = mainMenuWrapperClassname;
  mainMenuWrapper.appendChild(mainMenuBox);
  mainMenuBox.classList = mainMenuBoxClassName;

  sideNavMainTitleApi.map((val) => {
    const mainMenu = document.createElement("div");
    if (val.collapsed === true && val.collapseData) {
      const enbleCollapse = `<a class="nav-link collapsed" href=${val.ref} id="enbleCollapse" data-toggle="collapse" data-target="#${val.id}" aria-expanded="false" aria-controls="collapseLayouts">`;
      const mainMenuIcon = `<div class="sb-nav-link-icon"><i class="fas ${val.icon}"></i></div>`;
      const mainMenuText = `${val.title}<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div></a>`;

      //서브메뉴 사용안할때 반환
      const falseSubBox = `<div class="collapse" id=${val.id} aria-labelledby=${val.id} data-parent="#sidenavAccordion"><nav class="sb-sidenav-menu-nested nav">`;
      const filterSubFalse = val.collapseData.filter((val) => {
        return !val.collapsed;
      });
      const falseSubMenuText = filterSubFalse.map((data) => {
        const subMenuContents = ` <a class="nav-link ${data.className}" href=${data.ref}>${data.title}</a>`;
        return subMenuContents;
      });

      //서브메뉴 사용할때 반환
      const filterSubTrue = val.collapseData.filter((val) => {
        return val.collapsed === true;
      });
      const trueSubBox = `<div class="collapse" id=${val.id} aria-labelledby=${val.id} data-parent="#sidenavAccordion"><nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">`;
      const trueSubMenuText = filterSubTrue.map((data) => {
        const trueSubMenuTexts = `<a class="nav-link collapsed" href=${data.ref} data-toggle="collapse" data-target="#${data.id}" aria-expanded="false" aria-controls=${data.id}>${data.title}<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div></a>`;
        const truesub_2 = `<div class="collapse" id="${data.id}" aria-labelledby=${data.id} data-parent="#sidenavAccordionPages"><nav class="sb-sidenav-menu-nested nav">`;
        const truesub_3 = data.subData.map((data) => {
          return `<a class="nav-link" href=${data.ref}>${data.title}</a>`;
        });
        return (
          trueSubMenuTexts + truesub_2 + truesub_3.join("") + `</nav></div>`
        );
      });
      const enalbeCollapse =
        enbleCollapse +
        mainMenuIcon +
        mainMenuText +
        trueSubBox +
        trueSubMenuText.join("") +
        `</nav></div>` +
        falseSubBox +
        falseSubMenuText.join("");
      mainMenuBox.appendChild(mainMenu);
      mainMenu.innerHTML = enalbeCollapse;
    } else {
      const disableCollapse = `<a class="nav-link" href=${val.ref}><div class="sb-nav-link-icon"><i class="fas ${val.icon}"></i></div>${val.title}</a>`;
      mainMenuBox.appendChild(mainMenu);
      mainMenu.innerHTML = disableCollapse;
    }
  });
}

generateSideNav();

//사이드 메인타이틀 생성