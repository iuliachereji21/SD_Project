import React, { Component } from 'react';
import { Link } from 'react-router-dom';
class Home extends Component {
    state = {  } 
    render() { 
        return (
            <div>
                <h1>Food Panda</h1>
                <Link to='/login'>Log in</Link>
            </div>
            
        );
    }
}
 
export default Home;