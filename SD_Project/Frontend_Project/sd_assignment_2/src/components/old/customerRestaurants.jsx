import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'

function CustomerRestaurants() {
    let navigate = useNavigate();
    let {customerId} = useParams();
    customerId= customerId.slice(1);
    let {token} = useParams();
    token=token.slice(1);

    const [data, setData] = useState([]);
    const [filteredData, setFilteredData] = useState([]);

    useEffect(()=>{
        axios.get(`http://localhost:8080/sd_assignment2/customer/${customerId}/${token}/restaurants`)
            .then(res =>{
                setData(res.data);
                setFilteredData(res.data);
            })
            .catch(err =>{
                console.log(err);
                if(err.message == "Request failed with status code 401")
                    navigate(`/unauthorized`);
            })
    },[]);

    function filterData(){
        var e = document.getElementById("searchInput");
        //var as = document.forms[0].ddlViewBy.value;
        var text = e.value;
        if(text==""){
            setFilteredData(data);
        }
        else setFilteredData(data.filter(obj => obj.name.toLowerCase().includes(text.toLowerCase())));
    }

    return (
        <div>
            <h1>Customer restaurants {customerId}</h1>
            <input type="text" id="searchInput"></input>
            <button onClick={filterData}>Search by name</button>
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
                    {filteredData.map((restaurant)=>(
                        <tr>
                            <td>{restaurant.id}</td>
                            <td>{restaurant.name}</td>
                            <td>{restaurant.location}</td>
                            <td>{restaurant.available_delivery_zones}</td>
                            <td>
                                <Link to={"/customer/:"+customerId+"/:"+token+"/restaurants/:"+restaurant.id}>View Menu</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
      );
}

export default CustomerRestaurants;