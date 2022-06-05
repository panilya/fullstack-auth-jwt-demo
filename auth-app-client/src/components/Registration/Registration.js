import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import './RegistryStyle.css'
import * as AuthService from "../../services/AuthService";

export default function Registry({setIsLogged}) {

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
    AuthService.signup(data.username, data.password)
    // alert('successfully registered:' + JSON.stringify(data));
    reset()
  }

  return (
    <div className="body">
      <form className='form' onSubmit={handleSubmit(onRegister)}>
        <h1 className='body_title'>Sign up!</h1>
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
        className='logIned'
          onClick={() => setIsLogged(true)}
        >I have account</h4>

        <div className='div_submit'>
          <input value={'Register!'}className='submit' disabled={!isValid}type="submit"/>
        </div>
        
      </form>
    </div>
    )
}

