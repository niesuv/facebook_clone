import React, { useState } from "react";
import { createUser, getUserByID } from "../../util/http/users/enpoints";
import ErrorMessage from "./ErrorMessage";
import { useDispatch } from "react-redux";
import { login } from "../../store/user_slice";
import { useNavigate } from "react-router-dom";

const SignUpPage = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate()
  const [err, setErr] = useState({});
  const submit = async ({
    userName,
    birthDay,
    password,
    email,
    fullName,
    gender,
  }) => {
    setErr({});
    if (birthDay === "") {
      setErr({
        birthDay: "You must enter a birthday!"
      });
      return;
    }
    try {
      const id = await createUser({
        userName,
        birthDay,
        password,
        email,
        fullName,
        gender,
      });
    
      const newUser = await getUserByID(id);
      dispatch(login(newUser));
      navigate("/")
      


    } catch (e) {
      if (e.response) {
        if (e.response.data) setErr(e.response.data);
      }
    }
  };

  return (
    <div className="h-full p-8 bg-white items-center justify-start flex flex-col">
      <h1 className="text-5xl text-blue font-bold py-8">facebook</h1>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          const formData = new FormData(e.target);
          const data = Object.fromEntries(formData);
          submit(data);
        }}
        className="bg-[#fff] rounded-md w-[28rem]  p-4 max-w-[35rem] flex shadow-darkwhite shadow-md flex-col items-center "
      >
        <h1 className="text-black text-2xl font-bold">Create a new account</h1>
        <h1 className="text-black ">It's quick and easy.</h1>
        <div className="w-full h-[1px] border-b-[1px] border-darkwhite my-4" />
        <input
          className="bg-[#fff] border-whites px-2 border-[1px]  rounded-md w-full h-10 mb-4 text-black"
          type="text"
          name="fullName"
          placeholder="Fullname"
        />
        {err.fullName && <ErrorMessage message={err.fullName}></ErrorMessage>}
        <p className="text-start w-full text-black text-sm mb-2">
          Date of birth
        </p>

        <input
          className="bg-[#fff] border-whites px-2 border-[1px]  rounded-md w-full h-10 mb-4 text-black"
          type="date"
          name="birthDay"
          placeholder="dd-mm-yyyy"
        />
        {err.birthDay && <ErrorMessage message={err.birthDay}></ErrorMessage>}

        <p className="text-start w-full text-black text-sm mb-2">Gender</p>
        <div className="flex w-full justify-between gap-4">
          <div className="bg-[#fff] border-whites justify-between px-2 border-[1px] flex rounded-md items-center w-full h-10 mb-4 text-black">
            <p>Female</p>
            <input
              className="bg-[#fff] border-whites px-2 border-[1px] rounded-md w-4 h-10 text-black"
              type="radio"
              value="Female"
              name="gender"
            />
          </div>

          <div className="bg-[#fff] border-whites justify-between px-2 border-[1px] flex rounded-md items-center w-full h-10 mb-4 text-black">
            <p>Male</p>
            <input
              className="bg-[#fff] border-whites px-2 border-[1px] rounded-md w-4 h-10 text-black"
              type="radio"
              value="Male"
              name="gender"
            />
          </div>
        </div>
        <input
          className="bg-[#fff] border-whites px-2 border-[1px]  rounded-md w-full h-10 mb-4 text-black"
          type="text"
          name="userName"
          placeholder="Username"
        />
        {err.userName && <ErrorMessage message={err.userName}></ErrorMessage>}

        <input
          className="bg-[#fff] border-whites px-2 border-[1px]  rounded-md w-full h-10 mb-4 text-black"
          type="email"
          name="email"
          placeholder="Email"
        />
        {err.email && <ErrorMessage message={err.email}></ErrorMessage>}

        <input
          className="bg-[#fff] border-whites px-2 border-[1px]  rounded-md w-full h-10 mb-4 text-black"
          type="password"
          name="password"
          placeholder="Password"
        />

        {err.password && <ErrorMessage message={err.birthDay}></ErrorMessage>}
        {err.timestamp || typeof err === 'string' && <ErrorMessage message={"Some errors happened, please try again."}></ErrorMessage>}
        <button
          type="submit"
          className="bg-darkGreen text-[#fff] py-1 px-14 hover:brightness-110 font-semibold text-lg rounded-md"
        >
          Sign Up
        </button>
      </form>
    </div>
  );
};

export default SignUpPage;
