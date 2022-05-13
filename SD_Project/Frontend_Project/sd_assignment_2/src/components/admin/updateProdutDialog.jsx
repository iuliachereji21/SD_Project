import React, { Component, useState } from 'react';
import axios from 'axios'

import {useNavigate} from 'react-router-dom';

function UpdateProductDialog(props) {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");

    const [name, setName]= useState(props.product.name);
    const [price, setPrice]= useState(props.product.price);
    const [category, setCategory]= useState(props.product.category);
    const [link, setLink]= useState(props.product.link);

    let handleSubmit = (event) => {
        // Prevent page reload
        event.preventDefault();
        const headers = {
            token:props.token
        };
        const body = {
            id : props.product.id,
            name: name,
            price: price,
            category: category,
            link: link
        };

        axios.patch(
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
                            <input type="text" name="name" value= {name} required onChange={(event)=>{setName(event.target.value)}}/>
                            
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
                            <input type="submit" value="Update" />
                        </div>
                    </form>

                </div>
        </div>);
    return ( 
        dialog
     );
}

export default UpdateProductDialog;