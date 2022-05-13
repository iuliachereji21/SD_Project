import logo from './logo.svg';
import './App.css';
import { React } from 'react';
import { Component } from 'react';
import Home from './components/home';
import {Route, Routes} from 'react-router-dom';

import LogIn from './components/login';
import AdminPage from './components/admin/adminPage';
import Products from './components/customer/products';
import UnauthorizedErrorPage from './components/unauthorizedErrorPage';
import Designs from './components/customer/designs';
import MyDesigns from './components/customer/myDesigns';
import Register from './components/register';


class App extends Component {
  state = {  } 
  render() { 
    return (
      <div className="App">
        <div className='content'>
          <Routes>
          <Route path='/login' element={<LogIn/>}></Route> 
          <Route path='/register' element={<Register/>}></Route> 
          <Route path='/unauthorized' element={<UnauthorizedErrorPage/>}></Route> 
          <Route path='/admin' element={<AdminPage/>}></Route> 
          <Route path='/products' element={<Products/>}></Route> 
          <Route path='/designs' element={<Designs/>}></Route> 
          <Route path='/myDesigns' element={<MyDesigns/>}></Route> 
          <Route path='/' element={<Home />}></Route>
          </Routes>
        </div>
      </div>);
  }
}
 
export default App;
