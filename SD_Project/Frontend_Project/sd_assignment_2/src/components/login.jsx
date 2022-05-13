import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios'

function LogIn(){
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");

    let handleSubmit = (event) => {
        // Prevent page reload
        event.preventDefault();
        var { email, pass} = document.forms[0];
        axios.post("http://localhost:8080/sd_project/login",{email: email.value, password: pass.value})
        .then(response =>{
            if(response.data.customer)
                navigate(`/products`,{state : {token: response.data.token}});
            else
                navigate(`/admin`,{state : {token: response.data.token}});
            
        })
        .catch(({ response }) => { 
            setMessage(response.data.message);
        })
      };
    return (
        <div>
            <Link to='/'>Home</Link>
            <div className="form">
                <form onSubmit= {handleSubmit}>
                    <div className="input-container">
                        <label>Email </label>
                        <input type="text" name="email" required />
                        
                    </div>
                    <div className="input-container">
                        <label>Password </label>
                        <input type="password" name="pass" required />
                    
                    </div>
                    <div>
                        <label>{errorMessage} </label>
                    </div>
                    <div className="button-container">
                        <Link to="/register">Register</Link>
                        <input type="submit" value="Log in"/>
                    </div>
                </form>
            </div>
        </div>
        
    );
    
}
 
export default LogIn;