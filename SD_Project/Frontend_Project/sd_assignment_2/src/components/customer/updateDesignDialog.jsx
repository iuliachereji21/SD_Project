import React, { Component, useState } from 'react';
import axios from 'axios'

import {useNavigate} from 'react-router-dom';

function UpdateDesignDialog(props) {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");

    const [title, setTitle]= useState(props.design.title);
    const [isPublic, setIsPublic]= useState(props.design.isPublic ? "yes":"no");

    const[design, setDesign] = useState(props.design);

    let handleSubmit = (event) => {
        // Prevent page reload
        event.preventDefault();

        let today = new Date();
        let dateAndTime = today.getFullYear() + '-';
        if(today.getMonth() + 1 < 10){
            dateAndTime += "0" + (today.getMonth() + 1) + '-';
        }
        else{
            dateAndTime += (today.getMonth() + 1) + '-';
        }
        if(today.getDate() < 10){
            dateAndTime += "0" + today.getDate();
        }
        else{
            dateAndTime += today.getDate();
        }
        if(today.getHours() < 10){
            dateAndTime += 'T'+"0" + today.getHours() + ':';
        }
        else{
            dateAndTime += 'T'+today.getHours() + ':';
        }
        if(today.getMinutes() < 10){
            dateAndTime += "0" + today.getMinutes() + ':' ;
        }
        else{
            dateAndTime += today.getMinutes() + ':' ;
        }
        if(today.getSeconds() < 10){
            dateAndTime += "0" + today.getSeconds();
        }
        else{
            dateAndTime += today.getSeconds();
        }

        const headers = {
            token:props.token
        };

        design.title = title;
        design.isPublic = (isPublic === "yes");
        design.dateAndTime = dateAndTime;
        const body = design;

        console.log(headers);
        console.log(body);

        axios.patch(
            `http://localhost:8080/sd_project/myDesigns`,
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
                            <label>Title </label>
                            <input type="text" name="title" value= {title} required onChange={(event)=>{setTitle(event.target.value)}}/>
                            
                        </div>

                        <label>Public </label>
                        <select name="isPublic"  value= {isPublic} required onChange={(event)=>{setIsPublic(event.target.value)}}>
                            <option value="yes">yes</option>
                            <option value="no">no</option>
                        </select>
                        
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

export default UpdateDesignDialog;