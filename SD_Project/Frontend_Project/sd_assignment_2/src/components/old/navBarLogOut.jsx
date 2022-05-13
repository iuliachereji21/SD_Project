import React from 'react';
import { Link } from "react-router-dom";

function NavBarLogOut() {
    return ( 
        <div>
            <Link to="/">Home</Link>
            <Link to="/login">Log out</Link>
        </div>
     );
}

export default NavBarLogOut;
