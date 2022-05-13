import React from 'react';
import { Outlet, useParams, useLocation } from 'react-router-dom';
import NavBarLogOut from './navBarLogOut';
import { Link } from 'react-router-dom';

function CustomerPage() {
    const location = useLocation();
    console.log(location.state);
    let {customerId} = useParams();
    customerId=customerId.slice(1);
    let {token} = useParams();
    token=token.slice(1);
    return (
        <div>
            <NavBarLogOut/>
            <h1>Customer page {customerId}</h1>
            <div>
                  <Link to={"/customer/:"+customerId+"/:"+token+"/restaurants"}>Restaurants</Link>
                  <Link to={"/customer/:"+customerId+"/:"+token+"/orders"}>Orders</Link>
              </div>
              <Outlet/>
        </div>
      );
}

export default CustomerPage;