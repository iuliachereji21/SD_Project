import React, { Component } from 'react';
import { Link } from 'react-router-dom';
class Home extends Component {
    state = {  } 
    render() { 
        return (
            <div>
                <Link to='/products'>Products </Link>
                <Link to='/designs'>Designs </Link>
                <Link to='/login'>Log in </Link>
                <h1>Welcome</h1>
            </div>
            
        );
    }
}
 
export default Home;