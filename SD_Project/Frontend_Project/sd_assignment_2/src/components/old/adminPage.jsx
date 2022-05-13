import React from 'react';
import { Outlet, useParams } from 'react-router-dom';
import NavBarLogOut from './navBarLogOut';
import { Link } from 'react-router-dom';
import {Route, Routes} from 'react-router-dom';
import AdminRestaurants from './adminRestaurants';
import AdminOrders from './adminOrders';

function AdminPage() {
    let {adminId} = useParams();
    adminId= adminId.slice(1);
    let {token} = useParams();
    token= token.slice(1);
    return (
        <div>
            <NavBarLogOut/>
            <h1>Admin page {adminId}</h1>
            
            <div>
                <Link to={"/admin/:"+adminId+"/:"+token+"/restaurants"}>Restaurants</Link>
                
                <Link to={"/admin/:"+adminId+"/:"+token+"/orders"}>Orders</Link>
            </div>
            <Outlet/>

        </div>
      );
}

export default AdminPage;