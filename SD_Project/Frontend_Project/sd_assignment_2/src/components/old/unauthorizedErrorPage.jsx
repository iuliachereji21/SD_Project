import React, { Component } from 'react';
import { Link } from 'react-router-dom';
class UnauthorizedErrorPage extends Component {
    state = {  } 
    render() { 
        return (
            <div>
                <h1>Unauthorized</h1>
                <Link to='/login'>Log in</Link>
            </div>
            
        );
    }
}
 
export default UnauthorizedErrorPage;