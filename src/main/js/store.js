import { applyMiddleware, createStore, combineReducers } from 'redux';
import { createPromise } from 'redux-promise-middleware'
import { default as thunk } from 'redux-thunk';
import rover from "./reducers/roverReducer";
import date from "./reducers/dateReducer";

const reducer = combineReducers({
  rover, date
});
const middleware = applyMiddleware(  createPromise(),  thunk);

export default function configureStore() {
  return createStore(reducer, middleware);
}






