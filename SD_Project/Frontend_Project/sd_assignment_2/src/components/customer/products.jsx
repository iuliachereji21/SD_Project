import {React, useEffect, useState} from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import axios from 'axios'

import './../../index.css';

function Products() {
  let navigate = useNavigate();
    const location = useLocation();
    let token;
    if(location.state)
        if(location.state.token)
            token = location.state.token ;
        else token = "unauthorized";
    else token = "unauthorized"

    console.log(token);

    const [data, setData] = useState([]);
    const [filteredData, setFilteredData] = useState([]);
    const [design, setDesign] = useState([]);
    const [errorMessage, setErrorMessage]= useState("");
    const [isPublic, setIsPublic]= useState(true);
    const [title, setTitle]= useState("");

    const [name, setName]= useState("");
    const [category, setCategory]= useState("None");

    useEffect(()=>{
        axios.get(`http://localhost:8080/sd_project/products`)
            .then(res =>{
                setData(res.data);
                setFilteredData(res.data);
            })
            .catch(err =>{
                if(err.message == "Request failed with status code 401")
                    navigate(`/unauthorized`);
            })
    },[])
    
    function handleSearch(){
      setFilteredData(data);
      if(name!==""){
          setFilteredData(filteredData.filter(product => product.name.toLowerCase().includes(name.toLowerCase())));
      }
      if(category!=="None"){
        setFilteredData(filteredData.filter(product => product.category===category));
      }
    }

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

  let NotLoggedInButtonAddToDesign = ()=>(
    <div>
      <button onClick={()=>{navigate(`/login`);}}>Add to design</button>
    </div>
  );

  let LoggedInButtonAddToDesign = (props)=>(
    <div>
      <button onClick={()=>{addToDesign(props.product);}}>Add to design</button>
    </div>
  );

  const addToDesign = (product)=>{
    console.log(product);
    var design2 = [...design];
    design2.push(product);
    setDesign(design2);
  };

  const finishDesign = () =>{
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
      token: token
    };
    const body = {
      title: title,
      isPublic: isPublic,
      dateAndTime: dateAndTime,
      products: design
    };

    console.log(headers);
    console.log(body);

    axios.post(
      `http://localhost:8080/sd_project/products`,
      body,
      {
          headers:headers
      }
    ).then(response =>{
        console.log(response);
        setErrorMessage("");
        setTitle("");
        setDesign([]);
        
    })
    .catch(({ response }) => { 
        console.log(response.data.message);
        setErrorMessage(response.data.message);
        if(response.data.message == "Request failed with status code 401")
            navigate(`/unauthorized`);
    })
  };

    return (
        <div>
            <div>
              {token === "unauthorized" ? <NotLoggedInButtons/> : <LoggedInButtons/>}
              

                        <div className="input-container">
                            <label>Name </label>
                            <input type="text" name="name"  value= {name}  onChange={(event)=>{setName(event.target.value)}} />
                            
                        </div>
                        <div className="input-container">
                            <label>Category </label>
                            <select name="category"  value= {category}  onChange={(event)=>{setCategory(event.target.value)}}>
                                <option value="None"></option>
                                <option value="gresie">gresie</option>
                                <option value="oglinzi">oglinzi</option>
                                <option value="parchet">parchet</option>
                                <option value="lavabile">lavabile</option>
                            </select>
                        </div>
                <button onClick={handleSearch}>Search</button>

                <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Link</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredData.map((product)=>(
                        <tr>
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.category}</td>
                            <td>{product.link}</td>
                            <td>{token === "unauthorized" ? <NotLoggedInButtonAddToDesign/> : 
                              <LoggedInButtonAddToDesign
                                product = {product}
                              />}
                            </td>
                            
                        </tr>
                    ))}
                </tbody>
            </table>

            <table>
                <thead>
                    <h3>design</h3>
                    <tr>
                      <th>Id</th>
                      <th>Name</th>
                      <th>Price</th>
                      <th>Category</th>
                      <th>Link</th>
                    </tr>
                </thead>
                <tbody>
                    {design.map((product)=>(
                        <tr>
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.category}</td>
                            <td>{product.link}</td>
                        </tr>
                    ))}
                </tbody>
                <select name="category"  value= {isPublic}  onChange={(event)=>{setIsPublic(event.target.value)}}>
                  <option value={true}>Public</option>
                  <option value={false}>Privat</option>
                </select>
                <label>Title </label>
                <input type="text" name="title"  value= {title}  onChange={(event)=>{setTitle(event.target.value)}} />
                <button onClick={()=>finishDesign()}>Finish Design</button>
                <div>
                    <label>{errorMessage} </label>
                </div>
                
            </table>
            
            </div>

        </div>
      );
}

export default Products;