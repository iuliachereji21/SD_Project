import React, { Component, useState } from 'react';

import axios from 'axios'
import {useNavigate} from 'react-router-dom';

function AddRestaurantDialog(props) {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");

    let handleSubmit = (event) => {
        // Prevent page reload
        event.preventDefault();
        var { name, location, av_del_zones} = document.forms[0];
        console.log(name.value);
        axios.post(`http://localhost:8080/sd_assignment2/admin/${props.token}`,{name: name.value, location: location.value, available_delivery_zones: av_del_zones.value, admin_id: props.adminId})
        .then(response =>{
            console.log(response);
            props.onSave({id:response.data.id, name: response.data.name, location: response.data.location, available_delivery_zones: response.data.available_delivery_zones});
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
                            <label>Location </label>
                            <input type="text" name="location" required />
                        
                        </div>
                        <div className="input-container">
                            <label>Available delivery zones </label>
                            <input type="text" name="av_del_zones" required />
                        
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

export default AddRestaurantDialog;