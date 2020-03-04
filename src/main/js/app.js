'use strict';
import Rovers from "./components/Rovers";
import Photos from "./components/Photos";
import {Provider} from "react-redux";
import configureStore from './store';
import {
  BrowserRouter as Router,
  Route,
  Switch
} from "react-router-dom";

const React = require('react');
const ReactDOM = require('react-dom');

const store = configureStore();

class App extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
        <Router>
          <Switch>
            <Route exact path="/" component={Rovers}></Route>
            <Route path="/photos/:name" component={Photos}></Route>
          </Switch>
        </Router>
    )
  }
}

ReactDOM.render(
    <Provider store={store}>
      <App/>
    </Provider>, document.getElementById('rovers'));


