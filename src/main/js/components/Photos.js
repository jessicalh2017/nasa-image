import React from "react";
import {Col, Row} from "react-bootstrap";
import {bindActionCreators} from "redux";
import * as roverActions from "../actions/roverActions";
import {connect} from "react-redux";
import ImageViewer from "./ImageViewer";
import {Link} from "react-router-dom";

class Photos extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            photos: undefined,
            rover: undefined,
            date: undefined,
            isPhotosLoading: false,
            error: null,
            open: true
        };

        this.getPhotoUrl = this.getPhotoUrl.bind(this);
    }

    componentDidMount() {
        const {name} = this.props.match.params;
        const {date} = this.props.location.state;
        this.setState({
                rover: name,
                date: date
            }
        )
        this.props.roverActions.fetchRoverPhotos(name, date);
    }

    componentWillReceiveProps(nextProps) {
        this.setState({
                photos: nextProps.photos,
                isPhotosLoading: nextProps.isPhotosLoading,
                error: nextProps.error
            }
        )
    }

    getPhotoUrl(name, id, src) {
        return '/mynasa/rovers/' + name + '/photos/' + id + '/image?img_src=' + src;

    }

    render() {
        return (
            (this.state.isPhotosLoading) ? (<div> loading.... </div>) : (
                <div className="border border-1" id="rovers">
                    {this.state.photos && this.state.photos.length > 0 ?
                        (<div>
                            <Link to={{
                                pathname: '/'
                            }}>Back to rover list
                            </Link>
                            <h2 style={{bottom: 10}}>Photo list of {this.state.rover}</h2>

                            <Row style={{padding: 2, bottom: 10}} className="border border-1 border-dark">
                                <Col xs={1}><label>ID</label></Col>
                                <Col xs={2}><label>Camera</label></Col>
                                <Col xs={3}><label>Camera Full Name</label></Col>
                                <Col xs={2}><label>Earth Date</label></Col>
                                <Col xs={1}><label>Sol</label></Col>
                                <Col xs={3}><label></label></Col>
                            </Row>

                            {this.state.photos.map(photo =>
                                (<Row
                                    style={{padding: 2}}
                                    key={photo.id}>
                                    <Col xs={1}><span>{photo.id}</span></Col>
                                    <Col xs={2}><span>{photo.camera.name}</span></Col>
                                    <Col xs={3}><span>{photo.camera.full_name}</span></Col>
                                    <Col xs={2}><span>{this.state.date}</span></Col>
                                    <Col xs={1}><span>{photo.sol}</span></Col>

                                    <Col xs={3}>
                                        {
                                            photo.img_src && <ImageViewer
                                                src={this.getPhotoUrl(this.state.rover, photo.id, photo.img_src)}
                                            />
                                        }
                                    </Col>
                                </Row>)
                            )}
                        </div>) : (<div>
                            <Link to={{
                                pathname: '/'
                            }}>Back to rover list
                            </Link>
                            <h2 style={{bottom: 10}}>No photo is available for {this.state.rover}</h2>
                        </div>)}
                </div>)
        );
    }
}

function mapStateToProps(state, ownProps) {
    return {
        isPhotosLoading: state.rover.isPhotosLoading,
        photos: state.rover.photos,
        error: state.rover.error
    }
}

function mapDispatchToProps(dispatch) {
    return {
        roverActions: bindActionCreators(roverActions, dispatch),
    };
}

export default connect(mapStateToProps, mapDispatchToProps, null,
    {pure: false})(Photos);
