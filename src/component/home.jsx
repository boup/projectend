import React,{Component} from 'react';
//import HandleAction from './handleAction';

import history from './history';

class Home extends Component {
   
    handleIn = () => {
        // Navigate to /products
        this.props.history.push("/inCity");
      };
      handleBtw = () => {
        // Navigate to /products
        this.props.history.push("/btwCity");
      };
    render()
     { 
        return ( 

          <div>
            <h1>Welcome to Senegal </h1>
            <p>Where you would like to go?</p>
            <button type="button" class="btn btn-primary btn-lg" onClick={this.handleIn}>Inside City</button>
            <button type="button" class="btn btn-secondary btn-lg" onClick={this.handleBtw}>Between City</button>
          </div>
         );
    }
    }

 
export default Home;