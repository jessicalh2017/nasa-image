import * as types from "../actions/types";
import initialState from "./initialState";


export default function roverReducer(state = initialState.date, action) {
    switch (action.type) {

        case types.FETCH_DATES_PENDING:
            return {
                ...state,
                isDatesLoading: true,
                error: undefined
            };
        case types.FETCH_DATES_FULFILLED:
            return {
                ...state,
                dates: action.payload.entity,
                isDatesLoading: false
            };
        case types.FETCH_DATES_REJECTED:
            return {
                ...state,
                isDatesLoading: false,
                error: "Failed to fetch dates"
            };

        default:
            return state;
    }
}
