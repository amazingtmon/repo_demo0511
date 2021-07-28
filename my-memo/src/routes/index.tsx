import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Home from "../pages/Home";
import Memo from "../pages/Memo";
import NotFound from "../pages/NotFound";
import Signin from "../pages/Signin";
import Trash from "../pages/Trash";

const Root: React.FC = () => (
  <BrowserRouter>
    <Switch>
      <Route path="/" exact component={Home} />
      <Route path="/signin" exact component={Signin} />
      <Route path="/memo/:id" exact component={Memo} />
      <Route path="/trash/:id" exact component={Trash} />
      <Route component={NotFound} />
    </Switch>
  </BrowserRouter>
);

export default Root;
