import React, { Component } from 'react';
function AdminRestaurantsTable(props) {
    console.log("PROPS: "+props.restaurants[0].id);
    return ( 
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Available delivery zones</th>
                    </tr>
                </thead>
                <tbody>
                    {props.restaurants.map((restaurant)=>{
                        <tr>
                            <td>{restaurant.id}</td>
                            <td>{restaurant.name}</td>
                            <td>{restaurant.location}</td>
                            <td>{restaurant.available_delivery_zones}</td>
                        </tr>
                    })}
                </tbody>
            </table>
        </div>
     );
}

export default AdminRestaurantsTable;