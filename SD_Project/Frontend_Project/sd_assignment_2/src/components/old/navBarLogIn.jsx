import React, { Component } from 'react';
import { Link } from "react-router-dom";

function NavBarLogIn() {
    return ( 
        <div>
            <Link to="/">Home</Link>
            <Link to="/login">Login</Link>
        </div>
     );
}

export default NavBarLogIn;
