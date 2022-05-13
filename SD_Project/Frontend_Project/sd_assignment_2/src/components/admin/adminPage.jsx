import {React, useEffect, useState} from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import axios from 'axios'
import AddProductDialog from './addProductDialog';
import UpdateProductDialog from './updateProdutDialog';

import './../../index.css';

function AdminPage() {
    let navigate = useNavigate();
    const location = useLocation();
    let token;
    if(location.state)
        if(location.state.token)
            token = location.state.token ;
        else token = "unauthorized";
    else token = "unauthorized"

    const [data, setData] = useState([]);

    useEffect(()=>{
        axios.get(`http://localhost:8080/sd_project/admin`, {headers:{token:token}})
            .then(res =>{
                setData(res.data);
                console.log(res.data);
            })
            .catch(err =>{
                if(err.message == "Request failed with status code 401")
                    navigate(`/unauthorized`);
            })
    },[])
    
    const [isAddProductDialogOpen, setIsAddProductDialogOpen]= useState(false);

    return (
        <div>
            <div>
                <Link to={"/"}>Log out </Link>

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
                    {data.map((product)=>(
                        <tr>
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.category}</td>
                            <td>{product.link}</td>
                            <td id='updateCell'>
                                <UpdateProductDialog 
                                    product = {product}
                                    token = {token}
                                    onSave = {(obj)=>{
                                        // data.forEach(function(product) {
                                        //     if (product.id === obj.id) {
                                        //         product.name = obj.name;
                                        //         product.price = obj.price;
                                        //         product.category = obj.category;
                                        //         product.link = obj.link;
                                        //     }
                                        // });

                                        window.location.reload(false);
                                    }}>
                                </UpdateProductDialog>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <button onClick={()=>{setIsAddProductDialogOpen(true)}}>Add</button>
            <AddProductDialog 
                token = {token}
                isvisible = {isAddProductDialogOpen} 
                onSave = {(obj)=>{
                    data.push(obj);
                    setIsAddProductDialogOpen(false)
                }}
                onCancel = {()=>{
                    setIsAddProductDialogOpen(false);
                    }}>
            </AddProductDialog>
            </div>

        </div>
      );
}

export default AdminPage;