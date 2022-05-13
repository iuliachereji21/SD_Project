import React, { useState, useEffect } from 'react';
import { useParams, useNavigate} from 'react-router-dom';
import axios from 'axios'
import AddFoodDialog from './addFoodDialog';

function AdminRestaurantPage() {
    let navigate = useNavigate();
    let {restaurantId} = useParams();
    restaurantId= restaurantId.slice(1);
    let {adminId} = useParams();
    adminId= adminId.slice(1);
    let {token} = useParams();
    token=token.slice(1);

    const [data, setData] = useState([]);
    const [filteredData, setFilteredData] = useState([]);
    const [isAddFoodDialogOpen, setIsAddFoodDialogOpen]= useState(false);

    useEffect(()=>{
        axios.get(`http://localhost:8080/sd_assignment2/admin/${adminId}/${token}/restaurants/${restaurantId}`)
            .then(res =>{
                console.log(res);
                setData(res.data);
                setFilteredData(res.data);
            })
            .catch(err =>{
                console.log(err.message);
                if(err.message == "Request failed with status code 401")
                    navigate(`/unauthorized`);
            })
    },[])

    
    function filterData(){
        var e = document.getElementById("selectInput");
        //var as = document.forms[0].ddlViewBy.value;
        var category = e.options[e.selectedIndex].value;
        if(category=="None"){
            setFilteredData(data);
        }
        else setFilteredData(data.filter(obj => obj.category == category));
        console.log(category);
    }

    function exportMenuPdf(){
        axios.get(`http://localhost:8080/sd_assignment2/admin/${adminId}/${token}/restaurants/${restaurantId}/pdf`,{responseType: 'blob'} )
            .then(response =>{
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', 'menu.pdf'); //or any other extension
                document.body.appendChild(link);
                link.click();
            })
            .catch(err =>{
                console.log(err);
            })
    }

    return ( 
        <div>
            Admin restaurant page {restaurantId}
            <div className="input-container">
                <label>Category </label>
                <select name="category" onChange={filterData} id ="selectInput">
                    <option value="None"></option>
                    <option value="Breakfast">Breakfast</option>
                    <option value="Lunch">Lunch</option>
                    <option value="Dinner">Dinner</option>
                    <option value="Dessert">Dessert</option>
                </select>    
            </div>
            <button onClick={()=>{setIsAddFoodDialogOpen(true)}}>Add</button>
            <AddFoodDialog 
                restaurantId = {restaurantId}
                token = {token}
                adminId = {adminId}
                isvisible = {isAddFoodDialogOpen} 
                onSave = {(obj)=>{
                    console.log(obj);
                    data.push(obj);
                    setIsAddFoodDialogOpen(false)
                    console.log("on Save");
                }}
                onCancel = {()=>{
                    setIsAddFoodDialogOpen(false);
                    }}>
            </AddFoodDialog>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredData.map((food)=>(
                        <tr>
                            <td>{food.id}</td>
                            <td>{food.name}</td>
                            <td>{food.description}</td>
                            <td>{food.price}</td>
                            <td>{food.category}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <button onClick={()=>{exportMenuPdf()}}>Export menu pdf</button>
        </div>
     );
}

export default AdminRestaurantPage;