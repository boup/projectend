import React, { Component } from 'react';

class InCity extends Component {
    state = {  
        city:[
            {city_name:'Dakar',longitude:'14.716677°N',latitude:'-17.467686°W'},
            {city_name:'Saint-Louis',longitude:'16.0326°N',latitude:'16.4818°W'},
            {city_name:'Louga',longitude:'15.6142° N',latitude:'16.2287° W'},
            {city_name:'Thies',longitude:'14.7910° N',latitude:'16.9359° W'},
            {city_name:'Kaolack',longitude:'16.0758° W',latitude:'14.1652° N'},
            {city_name:'Diourbel',longitude:'14.6561° N',latitude:'16.2346° W'}
        ],
        line:[
            {link:'http://demdikk.com/',number:'1,4,6,7,8,9,10,13,18,20,23,121',city:'Dakar'},
            {link:'http://demdikk.com/',number:'2,5,11,12,15,16A,16B,217',city:'Dakar'},
            {link:'http://demdikk.com/',number:'218,219,227,228,232',city:'Dakar'}
        ],
        operator:[
            {description:'Public Bus',link:'http://demdikk.com/',name_operator:'Dakar Dem Dikk',city:'Dakar'},
            {description:'Private Bus',link:'http://aftu-senegal.org/',name_operator:'Aftu',city:'Dakar'},
            {description:'Private Bus/Taxi',link:'https://carrapideprestige.business.site/?utm_source=gmb&utm_medium=referral',name_operator:'Car Rapide transport',city:'Dakar'},
            {description:'Private Bus/Taxi',link:'https://courses.rapidosapp.com/',name_operator:'Rapidos Car',city:'Dakar'}
        ],
        ticket:[
            {duration:'End of section 1',price:'150',typeofTicket:'section 1',city:'Dakar'},
            {duration:'End of section 2',price:'175',typeofTicket:'section 2',city:'Dakar'},
            {duration:'End of section 3',price:'200',typeofTicket:'section 3',city:'Dakar'},
            {duration:'End of section 4',price:'250',typeofTicket:'section 4',city:'Dakar'},
            {duration:'End of section 5',price:'300',typeofTicket:'section 5',city:'Dakar'},
            {duration:'End of sous/under section',price:'100',typeofTicket:'sous section',city:'Dakar'}
        ],
        stop:[]
    };
    render() { 
        return ( 
            
                <div class="container">
                     <div class="row">
                         <div class="col">
                         <thead>
                        <th>City Name</th>
                        <th>Longitude</th>
                        <th>Latitude</th>
                  </thead>
                    <tboby>
                   {this.state.city.map(cite=>(
                        <tr> 
                            <td>{cite.city_name}</td>
                            <td>{cite.longitude}</td>
                            <td>{cite.latitude}</td>
                        </tr>))}
                    </tboby>
                 </div>
                 <div class="col">
                 <h1>Different line for the bus</h1>
                 
                        <th>Link</th>
                        <th>Number</th>
                        <th>City</th>
                    
                
                <tbody>
                { this.state.line.map(ligne=>(
                        <tr> 
                        <td>{ligne.link}</td>
                        <td>{ligne.number}</td>
                        <td>{ligne.city}</td>
                        </tr>))}
                </tbody>
                 </div>
                         <div class="w-100">
                         <h3>Operator Information</h3>
                <p>Information related to different operator public/private delivering services</p>
        {/* <table className="table"> */}
             <thead>
                    <th>Description</th>
                    <th>Link </th>
                    <th>Name Operator</th>
                    <th>City</th>
                </thead>
                <tbody>
                   {this.state.operator.map(opera=>(
                                <tr>
                                <td>{opera.description}</td>
                                <td>{opera.link}</td>
                                <td>{opera.name_operator}</td>
                                <td>{opera.city}</td>
                                </tr>
                   ))}
                  
                    
                </tbody>
        
        {/* </table>  */}

         </div>
                         <div class="col"><h3>Details  about the ticket for the Bus</h3>

<p>Where to buy ticket?
you have to buy the ticket inside the bus.
In each bus there is a person (generally call receiver) who sells it.
An important thing you cannot pay the ticket by bank card so you must have cash.
</p>
{/* <table className="table"> */}
    
    <thead>
        <th>Duration</th>
        <th>Price</th>
        <th>Type of Ticket</th>
        <th>City</th>
    </thead>
    <tbody>
        {this.state.ticket.map(ticket =>(
         <tr>
            <td>{ticket.duration}</td>
            <td>{ticket.price}</td>
            <td>{ticket.typeofTicket}</td>
            <td>{ticket.city}</td>
        </tr>) )}
       
    </tbody>
   
{/* </table> */}
</div>
                         <div class="col">col</div>
                     </div>
                </div>
        
         );
    }
}
 
export default InCity;