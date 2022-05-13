import React, { Component, useState } from 'react';
import axios from 'axios'

import {useNavigate} from 'react-router-dom';

function AddFoodDialog(props) {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");

    let handleSubmit = (event) => {
        // Prevent page reload
        event.preventDefault();
        var { name, description, price, category} = document.forms[0];
        axios.post(`http://localhost:8080/sd_assignment2/admin/${props.adminId}/${props.token}/restaurants`,{name: name.value, description: description.value, price: price.value, category: category.value, restaurant_id: props.restaurantId})
        .then(response =>{
            console.log(response);
            props.onSave({id:response.data.id, name: response.data.name, description: response.data.description, price: response.data.price, category: response.data.category});
        })
        .catch(({ response }) => { 
            setMessage(response.data.message);
            if(response.data.message == "Request failed with status code 401")
                    navigate(`/unauthorized`);
        })
        
    };

    let dialog = (
        <div>
            <div className="form">
                    <form onSubmit= {handleSubmit}>
                        <div className="input-container">
                            <label>Name </label>
                            <input type="text" name="name" required />
                            
                        </div>
                        <div className="input-container">
                            <label>Description </label>
                            <input type="text" name="description" required />
                        
                        </div>
                        <div className="input-container">
                            <label>Price </label>
                            <input type="text" name="price" required />
                        
                        </div>
                        <div className="input-container">
                            <label>Category </label>
                            <select name="category" required>
                                <option value="Breakfast">Breakfast</option>
                                <option value="Lunch">Lunch</option>
                                <option value="Dinner">Dinner</option>
                                <option value="Dessert">Dessert</option>
                            </select>
                        
                        </div>
                        <div>
                            <label>{errorMessage} </label>
                        </div>
                        <div className="button-container">
                            <input type="submit" value="Save" />
                            <button onClick={props.onCancel}>Cancel</button>
                        </div>
                    </form>

                </div>
        </div>);
    if(!props.isvisible)
        dialog=null;
    return ( 
        dialog
     );
}

export default AddFoodDialog;