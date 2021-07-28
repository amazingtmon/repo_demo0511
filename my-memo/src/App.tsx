//import React from 'react';
import { BrowserRouter, Redirect, Route, Switch } from "react-router-dom";
import Home from "./pages/Home";
import Detail from "./pages/Detail";
import Edit from "./pages/Edit";
import NotFound from "./pages/NotFound";
import Trash from "./pages/Trash";
import Memo from "./pages/Memo";
import Error from "./pages/Error";
import { ErrorBoundary } from "react-error-boundary";

function App() {
  return (
    <ErrorBoundary FallbackComponent={Error}>
      <BrowserRouter>
        <Switch>
          <Route exact path="/edit/:id" component={Edit} />
          <Route exact path="/book/:id" component={Detail} />
          <Route path="/memo/:id" exact component={Memo} />
          <Route path="/trash/:id" exact component={Trash} />
          <Route exact path="/" component={Home} />
          <Route component={NotFound} />
          <Redirect path="*.ks" to="/" />
        </Switch>
      </BrowserRouter>
    </ErrorBoundary>
  );
}

export default App;

/**
 * 리액트 라우터에서 제공하는 컴포넌트
 * 1. BrowserRouter : HTML5 히스토리 API를 사용하여 주소를 관리해주는 라우터
 * 2. <Route /> : 요청 경로와 렌더링할 컴포넌트 설정
 * 3. <Switch /> : 하위에 라우터 중 하나를 선택
 * 4. <Redirect /> : 요청 경로를 다른 경로로 리다이렉션 한다.
 * 
 * 
 * 리액트 라우터 구성 실습
    작업지시서
    1. npx create-reate-app 프로젝트이름 --template typescript

    2. npm i react-router-dom
    타입스크립트에서 리액트 라우터를 사용하려면 react-router-dom 뿐만아니라 
    타입정보가 있는 @types/react-router-dom 패키지도 필요하다.
    BrowserRouter, Switch, Router, Redirect 컴포넌트를 이용해 라우터 컴포넌트를 만들 수 있다.

    npm i --save-dev @types/react

    npm i --save-dev @types/react-router-dom

    :import { BrowserRouter} from 'react-router-dom';에서 BrowserRouter 임포트시 필요

    3. npm start - 리액트 서버 올리기

    4. 라우팅 설정하기
    :src아래 pages 폴더 만들고 그 아래 Home.tsx, Add.tsx, Signin.tsx .. 등으로 추가
    App.tsx에서 2번에서 설정한 BrowserRouter컴포넌트를 추가하고 그 하위에 Switch컴포넌트
    그리고 그 아래 Route컴포넌트를 추가한 뒤 path속성과 component속성에 라우터를 통해
    이동할 페이지 정보를 등록한다.

    5. 잘못된 요청 URL에 대한 처리를 추가한다.

    npm i react-error-boundary => <ErrorBoundary></ErrorBoundary>

    npm i redux react-redux redux-saga =>
    
    npm i redux-devtools-extension redux-actions 

    npm i @types/react-redux @types/redux-actions -D

    npm i connected-react-router => <ConnectedRouter></ConnectedRouter>
 */
