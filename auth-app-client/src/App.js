import React,{useState} from "react";
import {Form} from "./Components/Form";
import { ThemeContext } from "./Contexts/ThemeContext";
import './App.css'


export default function App (){
  
  const [theme, setTheme] = useState('dark');
  const toggleTheme = () =>{
      setTheme(theme === 'light' ? 'dark' : 'light')
  }

  const [isLogged,setIsLogged] = useState(false);
  const style = {
    margin:'0'
  }


  return(
    <div className="App" id ={theme}>
      <ThemeContext.Provider value={{theme,toggleTheme}}>
        {!isLogged && <Form
          setIsLogged={setIsLogged}
          title={'SIGN UP!'}
          HaveAccButton={'I have account'}
          SumbitName={'Register!'}
          />}

        {isLogged && <Form
          isLogged={isLogged}
          setIsLogged={setIsLogged}
          title={'SIGN IN!'}
          Pstyle={style}
          HaveAccButton={'Have`nt any accounts?Click here'}
          SumbitName={'Authorize!'}
          />}
      </ThemeContext.Provider>
    </div>
  )
}
