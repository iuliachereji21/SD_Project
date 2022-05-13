import React, { useEffect, useState  } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios'
import Select from 'react-select';
import {useNavigate} from 'react-router-dom';

function AdminOrders() {
    let navigate = useNavigate();
    let {adminId} = useParams();
    adminId= adminId.slice(1);
    let {token} = useParams();
    token=token.slice(1);
    const [data, setData] = useState([]);
    const [updated, setUpdated] = useState(0);
    const [filteredData, setFilteredData] = useState([]);

    useEffect(()=>{
        axios.get(`http://localhost:8080/sd_assignment2/admin/${adminId}/${token}/orders`)
            .then(res =>{
                var data = res.data;
                data.map((order)=>{
                    switch(order.status){
                        case "PENDING": 
                            order.options = [{value: "DECLINED", label: "DECLINED"},{value: "ACCEPTED", label: "ACCEPTED"},];
                            break;
                        case "ACCEPTED":
                            order.options = [{value: "IN DELIVERY", label: "IN DELIVERY"}];
                            break
                        case "IN DELIVERY":
                            order.options = [{value: "DELIVERED", label: "DELIVERED"}];
                            break
                        default:
                            order.options=[];
                            break;
                    }
                });
                setData(data);
                setFilteredData(data);
                console.log(data);
            })
            .catch(err =>{
                console.log(err);
                if(err.message == "Request failed with status code 401")
                    navigate(`/unauthorized`);
            })
    },[updated]);

    const updateStatus = (selected, order)=>{
        console.log(order);
        console.log(selected);
        axios.patch(`http://localhost:8080/sd_assignment2/admin/${adminId}/${token}/orders/${order.id}`,{status: selected.value})
            .then(res=>{
                console.log(res);
                var up = updated;
                up++;
                setUpdated(up);
            })
            .catch(err =>{
                console.log(err);
                if(err.message == "Request failed with status code 401")
                    navigate(`/unauthorized`);
            })
    };

    function filterData(){
        var e = document.getElementById("selectInput");
        //var as = document.forms[0].ddlViewBy.value;
        var status = e.options[e.selectedIndex].value;
        if(status=="None"){
            setFilteredData(data);
        }
        else setFilteredData(data.filter(obj => obj.status == status));
    }

    return (
        <div>
            <h1>Admin orders {adminId}</h1>
            <label>Status</label>
                <select name="status" onChange={filterData} id ="selectInput">
                    <option value="None"></option>
                    <option value="PENDING">PENDING</option>
                    <option value="ACCEPTED">ACCEPTED</option>
                    <option value="IN DELIVERY">IN DELIVERY</option>
                    <option value="DELIVERED">DELIVERED</option>
                    <option value="DECLINED">DECLINED</option>
                </select>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Customer</th>
                        <th>Restaurant</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredData.map((order)=>(
                        <tr>
                            <td>{order.id}</td>
                            <td>{order.customer_name}</td>
                            <td>{order.restaurant_name}</td>
                            <td>{order.status}</td>
                            <td>
                                <Select options={order.options} onChange={(selected)=>updateStatus(selected,order)}></Select>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
      );
}

export default AdminOrders;