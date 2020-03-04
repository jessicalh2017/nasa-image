import * as roverActions from "../actions/roverActions";
import * as dateActions from "../actions/dateActions";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import React from "react";
import {Col, Row} from "react-bootstrap";
import {Link} from "react-router-dom";

class Rovers extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isRoversLoading: true,
            rovers: undefined,
            dates: undefined,
            selectedDate: {},
            error: null,
        };
        this.onDateChange = this.onDateChange.bind(this);
    }

    componentDidMount() {
        this.props.roverActions.fetchRovers();
        this.props.dateActions.fetchDates();
    }

    componentWillReceiveProps(nextProps) {

        this.setState({
                rovers: nextProps.rovers,
                isRoversLoading: nextProps.isRoversLoading,
                dates: nextProps.dates,
                error: nextProps.error
            }
        )
    }

    onDateChange(value, name) {
        let selectedDate = this.state.selectedDate;
        selectedDate[name] = value;

        this.setState({
            selectedDate: selectedDate
        });
    }

    render() {

        return (
            (!this.state.dates || this.state.isRoversLoading) ? (<div> loading.... </div>) : (

                <div className="rover_container border border-1" id="rovers">
                    {this.state.rovers && this.state.rovers.length > 1 ?
                        (<div>
                            <h2 style={{bottom: 10}}>Our Rovers</h2>
                            <Row style={{padding: 2, bottom: 10}} className="border border-1 border-dark">
                                <Col xs={2}><label>ID</label></Col>
                                <Col xs={3}><label>Name</label></Col>
                                <Col xs={3}><label>Select phote by dates</label></Col>
                                <Col xs={4}><label></label></Col>
                            </Row>

                            {this.state.rovers.map(rover =>
                                (<Row
                                    style={{padding: 2}}
                                    key={rover.id}>
                                    <Col xs={2}><span>{rover.id}</span></Col>
                                    <Col xs={3}><span>{rover.name}</span></Col>
                                    <Col xs={3}>
                                        <div className="pull-right">
                                            <select className="form-control"
                                                    value={this.state.selectedDate[rover.name]
                                                    || "--"}
                                                    onChange={e => this.onDateChange(
                                                        e.target.value, rover.name)}>
                                                <option key={0} value={"--"}>{"--"}</option>
                                                {this.state.dates.map((date, i) => (
                                                    <option key={i}
                                                            value={date}>{date}</option>))}
                                            </select>
                                        </div>
                                    </Col>
                                    <Col xs={4}>
                                        {this.state.selectedDate[rover.name] ?
                                            <Link to={{
                                                pathname: '/photos/' + rover.name,
                                                state: {
                                                    date: this.state.selectedDate[rover.name]
                                                }
                                            }}>photos</Link> : null}
                                    </Col>
                                </Row>)
                            )}
                        </div>) : <div><h2 style={{bottom: 10}}>No Rover Information is available</h2></div>}
                </div>)
        );
    }
}

function mapStateToProps(state, ownProps) {
    return {
        isRoversLoading: state.rover.isRoversLoading,
        rovers: state.rover.rovers,
        error: state.rover.error,
        dates: state.date.dates,
    }
}

function mapDispatchToProps(dispatch) {
    return {
        roverActions: bindActionCreators(roverActions, dispatch),
        dateActions: bindActionCreators(dateActions, dispatch),
    };
}

export default connect(mapStateToProps, mapDispatchToProps, null,
    {pure: false})(Rovers);
