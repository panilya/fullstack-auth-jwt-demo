import React from "react";
import Registry from "./Registration/Registry.js";
import Logining from "./Logining/Logining.js";

export default class App extends React.Component{
    constructor(props) {
        super(props);
        this.handleLoginClick = this.handleLoginClick.bind(this);
        this.handleLogoutClick = this.handleLogoutClick.bind(this);
        this.state = {isLogged: false};
      }
    
      handleLogged() {
        this.setState({isLogged: true});
      }
    
      handleNotLogged() {
        this.setState({isLogged: false});
      }
    
    render(){

        let content = null;

        if (this.props.state.isLogged) {
              content = <Registry 
              handleLogged={this.handleLogged}
              />
          } else if (!this.props.state.isLogged) {
            content = 
              <Logining
              handleNotLogged={this.handleNotLogged}
              />
          } else{
              <h1>nothing here</h1>
          }

          return(
          
              {content}
          )
    }
}
