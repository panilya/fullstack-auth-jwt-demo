import React,{ useState } from "react";
import Form from "./Components/Form";

export default function App (){

  const [isLogged,setIsLogged] = useState(false);
  const style = {
    margin:'0'
  }

  return(
      <div>
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

    </div>
  )
}
