//import React from 'react';
import { BrowserRouter, Redirect, Route, Switch } from "react-router-dom";
import Home from "./pages/Home";
import Detail from "./pages/Detail";
import Edit from "./pages/Edit";
import NotFound from "./pages/NotFound";

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/edit/:id" component={Edit} />
        <Route exact path="/book/:id" component={Detail} />
        <Route exact path="/" component={Home} />
        <Route component={NotFound} />
        <Redirect path="*.ks" to="/" />
      </Switch>
    </BrowserRouter>
  );
}

export default App;

/**
 * 리액트 라우터에서 제공하는 컴포넌트
 * 1. BrowserRouter : HTML5 히스토리 API를 사용하여 주소를 관리해주는 라우터
 * 2. <Route /> : 요청 경로와 렌더링할 컴포넌트 설정
 * 3. <Switch /> : 하위에 라우터 중 하나를 선택
 * 4. <Redirect /> : 요청 경로를 다른 경로로 리다이렉션 한다.
 */
