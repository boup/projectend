import React, { Component } from 'react';

class BtwCity extends Component {
    state = {  
        travelbus:[
            {link:'http://demdikk.com/senegal_dem_dikk/',price:'4000',city:'Dakar'},
            {link:'http://rapido.com',price:'5000',city:'Dakar'},
            {link:'http://demdikk.com/senegal_dem_dikk/',price:'7000',city:'Dakar'},
            {link:'http://senegaltours.com',price:'15000',city:'Dakar'}
        ],
        travelbus:[],
        travelfly:[
            {company:'http://edreams.com/flights/dakar-ziguinchor/DKR/ZIG',price:'250000',city:'Dakar'},
            {company:'http://edreams.com/flights/dakar-thies/DKR/THIE',price:'150000',city:'Dakar'},
            {company:'http://edreams.com/flights/dakar-saint-louis/DKR/SAI',price:'170000',city:'Dakar'},
            {company:'http://edreams.com/flights/dakar-diourbel/DKR/DIO',price:'350000',city:'Dakar'}
        ]
    };
   
    render() { 
        return ( 

            <div class="container px-lg-5">
               <div class="row mx-lg-n5">
                    <div class="col py-3 px-lg-5 border bg-light"><h3>Travel By Bus Between City</h3>
                  <table className="table">
                    
                    <thead>
                        <th>Link</th>
                        <th>Price</th>
                        <th>City</th>
                    </thead>
                    <tbody>
                        {this.state.travelbus.map(bus =>(
                         <tr>
                            <td>{bus.link}</td>
                            <td>{bus.price}</td>
                            <td>{bus.city}</td>
                        </tr>) )}
                       
                    </tbody>
                    
                    </table>
              </div>
                    <div class="col py-3 px-lg-5 border bg-light"> <h3>Travel by Plane between City</h3>
                <table className="table">
                    
                    <thead>
                        <th>Company</th>
                        <th>Price</th>
                        <th>City</th>
                    </thead>
                    <tbody>
                        {this.state.travelfly.map(fly =>(
                         <tr>
                            <td>{fly.company}</td>
                            <td>{fly.price}</td>
                            <td>{fly.city}</td>
                        </tr>) )}
                       
                    </tbody>
                    
                </table>
             </div>
                 </div>
            </div>
         );
    }
}
 
export default BtwCity;