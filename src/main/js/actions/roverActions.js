import * as types from "./types";

const _rest = require('rest');
const mime = require('rest/interceptor/mime');
const errorCode = require('rest/interceptor/errorCode');
const params = require('rest/interceptor/params');
const rest = _rest.wrap(mime).wrap(params).wrap(errorCode);

export function fetchRovers() {
  return dispatch => {
    return dispatch({
      type: types.FETCH_ROVERS,
      payload: rest({
        method: 'GET', path: '/mynasa/rovers',
      })
    })
  }
}

export function fetchRoverPhotos(name, earthDate) {
  const params = {
    "earthDate": earthDate
  };
  return dispatch => {
    return dispatch({
      type: types.FETCH_ROVER_PHOTOS,
      payload: rest({
        method: 'GET',
        params: params,
        path: '/mynasa/rovers/' + name +'/photos'
      })
    })
  }
}