import React from "react";
import {Modal} from "react-bootstrap";

export default class ImageViewer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false,
            image: props.src
        };
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
    }

    handleShow() {
        this.setState({isOpen: true});
    };

    handleClose() {
        this.setState({isOpen: false});
    };

    render() {
        return (
            <div>
                <button onClick={this.handleShow}> Click to view the photo</button>

                    <Modal style={{alignSelf: 'center'}} show={this.state.isOpen} onHide={this.handleClose}>
                        <Modal.Header closeButton>
                            <Modal.Title> View Image</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <img
                                style={{width: '300px', height: 'auto', alignSelf: 'center',  objectFit: 'overflow'}}
                                src={this.state.image}
                                onClick={this.handleShowDialog}
                                alt="no image"
                            />
                        </Modal.Body>
                    </Modal>
            </div>
        );
    }
}
