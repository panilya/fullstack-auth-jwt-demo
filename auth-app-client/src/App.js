import React,{ useState,useEffect } from "react";
import Registry from "./Registration/Registry.js";
import Logining from "./Logining/Logining.js";

export default function App (){
    
    
    const [isLogged,setIsLogged] = useState(false);
      
    return(
        <div>
        {isLogged && <Logining setIsLogged={setIsLogged}/>}
        {!isLogged && <Registry setIsLogged={setIsLogged}/>}
      </div>
    )
}
