
import React, {useContext } from "react";
import { useForm } from "react-hook-form";
import {login,signup} from "../services/AuthService";
import { ThemeContext } from "../Contexts/ThemeContext";


const Form = ({setIsLogged, title,HaveAccButton,SumbitName,isLogged,Pstyle,}) =>{

  const { 
    register,
    formState: {
      errors, isValid
    },
    handleSubmit,
    reset,
  } = useForm({
    mode: "onBlur"
  });

  const onRegister = (data) => {
    signup(data.username, data.password);
    reset();
  }

  const onLogin = (data) => {
    login(data.username, data.password);
    reset();
    alert('successfully Logined:' + JSON.stringify(data));
  }
  
  const {theme, toggleTheme} = useContext(ThemeContext)
  return (
    <>
      <button className="switch" onClick={() => toggleTheme()}>Theme: {theme}</button>
      <form id={theme} className='form' onSubmit={handleSubmit(isLogged? onLogin : onRegister)}>

        <h1 className='body_title'>{title}</h1>
        <label className="labels">Username:
        <br />

        <input className='input' 
        {...register('username',{
          required: "Username is requied",
          minLength : {
            value: 3,
            message: "At least 3 symbols!"
          },
          maxLength: {
            value: 15,
            message: "Maximum 15 symbols!"
          }
        
        })}/>
        </label>
        <br />

        <div>
          {errors?.username && <p className="error">{errors?.username?.message || "Try again"}</p>}
        </div>

        <label className="labels">Password:
        <br />

        <input className='input' type='password'
        {...register('password',{
          required: "Password is requied",
          minLength : {
            value: 5,
            message: "Password has to contain at least 5 symbols!"
          },
          maxLength: {
            value: 20,
            message: "Maximum 20 symbols!"
          }
        })}/>
        </label>
        <br />

        <div>
          {errors?.password && <p className="error error_password">{errors?.password?.message || "Try again"}</p>}
        </div>

        <h4
        style={Pstyle}
        className='logIned'
          onClick={() => setIsLogged(!isLogged)}
          >{HaveAccButton}</h4>
        <div className='div_submit'>
          <input value={SumbitName}className='submit' disabled={!isValid}type="submit"/>
        </div>
      </form>
    </>
  )
}

export {Form}
