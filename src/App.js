import React from 'react';
// import logo from './logo.svg';
import './App.css';
// import Operator from './component/operator';
// import Ticket from './component/ticket';
// import Line from './component/line';
// import City from './component/city';
// import TravelBus from './component/travelbus';
// import TravelFly from './component/travelfly';
import InCity from './component/inCity';
import BtwCity from './component/btwCity';
import {Route,Switch,Link,Redirect,Router } from 'react-router-dom';
import Home from './component/home';

import history from './component/history';

function App() {
  return (
    <main className="container">
       
      { <InCity/> }
      { /*<BtwCity/>*/ }
    
    {/* router to router is the home page
    Incity shows all information inside the city
    BtwCity shows all information between the city */}
     <Router history={history}>
     <Switch>
        <Route path="/" exact component={Home} /> 
        <Route path="/inCity" Component={InCity} /> 
        <Route path="/btwCity" Component={BtwCity} />  
         
  
                
     </Switch>
     </Router>
    </main>
  );
}

export default App;