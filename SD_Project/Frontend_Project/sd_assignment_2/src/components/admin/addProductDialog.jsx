import React, { Component, useState } from 'react';
import axios from 'axios'

import {useNavigate} from 'react-router-dom';

function AddProductDialog(props) {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");
    
    const [name, setName]= useState("");
    const [price, setPrice]= useState("");
    const [category, setCategory]= useState("gresie");
    const [link, setLink]= useState("");

    let handleSubmit = (event) => {
        // Prevent page reload
        event.preventDefault();

        const headers = {
            token:props.token
        };
        const body = {
            name: name,
            price: price,
            category: category,
            link: link
        };

        axios.post(
            `http://localhost:8080/sd_project/admin`,
            body,
            {
                headers:headers
            }
        ).then(response =>{
            props.onSave({id:response.data.id, name: response.data.name, price: response.data.price, category: response.data.category, link: response.data.link});
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
                            <input type="text" name="name"  value= {name} required onChange={(event)=>{setName(event.target.value)}} />
                            
                        </div>
                        <div className="input-container">
                            <label>Price </label>
                            <input type="text" name="price" value= {price} required onChange={(event)=>{setPrice(event.target.value)}} />
                        
                        </div>
                        <div className="input-container">
                            <label>Category </label>
                            <select name="category"  value= {category} required onChange={(event)=>{setCategory(event.target.value)}}>
                                <option value="gresie">gresie</option>
                                <option value="oglinzi">oglinzi</option>
                                <option value="parchet">parchet</option>
                                <option value="lavabile">lavabile</option>
                            </select>
                        </div>
                        
                        <div className="input-container">
                            <label>Link </label>
                            <input type="text" name="link" value= {link} required onChange={(event)=>{setLink(event.target.value)}} />
                        
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

export default AddProductDialog;