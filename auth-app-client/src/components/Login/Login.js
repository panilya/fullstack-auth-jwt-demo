import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import './Logining.css'
import * as AuthService from "../../services/AuthService";

export default function Logining({setIsLogged}) {

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

  
  const onLogin = (data) => {
    AuthService.login(data.username, data.password)
    // alert('successfully Logined:' + JSON.stringify(data));
    reset()
  }

  return (
    <div className="body">
    <form className='form' onSubmit={handleSubmit(onLogin)}>
      <h1 className='body_title'>Sign in!</h1>
      <label className="labels">Username:
      <br />

      <input className='input' 
      {...register('username',{
        required: "Username is requied"
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
      })}/>
      </label>
      <br />

      <div>
        {errors?.password && <p className="error error_password">{errors?.password?.message || "Try again"}</p>}
      </div>

      <h3 onClick={() => setIsLogged(false)} className='logIned loginedd' > I dont have account :(</h3>

      <div className='div_submit'>
        <input value={'Log in!'}className='submit' disabled={!isValid}type="submit"/>
      </div>
      
    </form>
  </div>
    )
}

