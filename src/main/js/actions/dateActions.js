import * as types from "./types";

const _rest = require('rest');
const mime = require('rest/interceptor/mime');
const errorCode = require('rest/interceptor/errorCode');
const params = require('rest/interceptor/params');
const rest = _rest.wrap(mime).wrap(params).wrap(errorCode);

export function fetchDates() {
    return dispatch => {
        return dispatch({
            type: types.FETCH_DATES,
            payload: rest({
                method: 'GET', path: '/mynasa/dates',
            })
        })
    }
}