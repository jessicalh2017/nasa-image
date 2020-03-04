import * as types from "../actions/types";
import initialState from "./initialState";


export default function roverReducer(state = initialState.rover, action) {
  switch (action.type) {

    case types.FETCH_ROVERS_PENDING:
      return {
        ...state,
        isRoversLoading: true,
        error: undefined
      };
    case types.FETCH_ROVERS_FULFILLED:
      return {
        ...state,
        rovers: action.payload.entity.rovers,
        isRoversLoading: false
      };
    case types.FETCH_ROVERS_REJECTED:
      return {
        ...state,
        isRoversLoading: false,
        error: "Failed to fetch photo"
      };

    case types.FETCH_ROVER_PHOTOS_PENDING:
      return {
        ...state,
        isPhotosLoading: true,
        error: undefined
      };
    case types.FETCH_ROVER_PHOTOS_FULFILLED:
      return {
        ...state,
        photos: action.payload.entity.photos,
        isPhotosLoading: false
      };
    case types.FETCH_ROVER_PHOTOS_REJECTED:
      return {
        ...state,
        isPhotosLoading: false,
        error: "Failed to fetch rover's photos"
      };

    default:
      return state;
  }
}
