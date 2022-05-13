import {React, useEffect, useState} from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import axios from 'axios'

import './../../index.css';
import UpdateDesignDialog from './updateDesignDialog';

function MyDesigns() {
  let navigate = useNavigate();
    const location = useLocation();
    let token;
    if(location.state)
        if(location.state.token)
            token = location.state.token ;
        else token = "unauthorized";
    else token = "unauthorized"

    const [data, setData] = useState([]);

    console.log(token);

    useEffect(()=>{
        axios.get(`http://localhost:8080/sd_project/myDesigns`, {headers:{token:token}})
            .then(res =>{
                setData(res.data);
            })
            .catch(err =>{
                if(err.message == "Request failed with status code 401")
                    navigate(`/unauthorized`);
            })
    },[])
    

    let getListOfProducts = (design)=>{
        let str = "";
        design.products.forEach(product => {
            str = str + product.name + "; ";
        });
        return str;
    };

    let NotLoggedInButtons = ()=>(
        <div>
          <button onClick={()=>{navigate(`/products`,{state : {token: token}});}}>Products</button>
          <button onClick={()=>{navigate(`/designs`,{state : {token: token}});}}>Designs</button>
          <button onClick={()=>{navigate(`/login`);}}>Log in</button>
        </div>
      );
    
      let LoggedInButtons = ()=>(
        <div>
          <button onClick={()=>{navigate(`/products`,{state : {token: token}});}}>Products</button>
          <button onClick={()=>{navigate(`/designs`,{state : {token: token}});}}>Designs</button>
          <button onClick={()=>{navigate(`/login`);}}>Log out</button>
          <button onClick={()=>{navigate(`/myDesigns`,{state : {token: token}});}}>My designs</button>
        </div>
      );
      
      let generatePdf = (design)=>{
        let body = {
            title : design.title,
            products : design.products
        }
        console.log(body);
      axios.post(`http://localhost:8080/sd_project/designs/pdf`,body, {responseType: 'blob'} )
          .then(response =>{
              const url = window.URL.createObjectURL(new Blob([response.data]));
              const link = document.createElement('a');
              link.href = url;
              link.setAttribute('download', 'shoppingList.pdf'); //or any other extension
              document.body.appendChild(link);
              link.click();
          })
          .catch(err =>{
              console.log(err);
          })
    }
      
    return (
        <div>
            <div>
            {token === "unauthorized" ? <NotLoggedInButtons/> : <LoggedInButtons/>}

                <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Date</th>
                        <th>Products</th>
                        <th>Public</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((design)=>(
                        <tr>
                        <td>{design.id}</td>
                        <td>{design.title}</td>
                        <td>{design.dateAndTime}</td>
                        <td>{getListOfProducts(design)}</td>
                        <td>{design.isPublic ? "yes":"no"}</td>
                        <td id='updateCell'>
                                <UpdateDesignDialog 
                                    design = {design}
                                    token = {token}
                                    onSave = {()=>{

                                        window.location.reload(false);
                                    }}>
                                </UpdateDesignDialog>
                        </td>
                        <td><button onClick={()=>generatePdf(design)}>Download Shopping List</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
            
            </div>

        </div>
      );
}

export default MyDesigns;