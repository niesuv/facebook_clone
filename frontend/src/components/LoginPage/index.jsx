import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { NavLink, useNavigate } from "react-router-dom";
import { getUserByUserName } from "../../util/http/users/enpoints";
import { login } from "../../store/user_slice";

const LoginPage = () => {
  const user = useSelector((state) => state.user);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    if (user.id !== "") navigate("/");
  }, [user, navigate]);

  const [errorMessage, setErrorMessage] = useState("");
  const submit = async ({ username, password }) => {
    try {
      const data = await getUserByUserName(username);
      dispatch(login(data));
      localStorage.setItem("user", JSON.stringify(data));
      navigate("/");
    } catch (err) {
      setErrorMessage("Some errors happened. Please try again!");
    }
  };

  return (
    <div className="h-screen w-full  lg:flex-row lg:items-center items-center justify-center  px-20 gap-4 bg-white flex flex-col">
      <div className="flex flex-col">
        <h1 className="text-blue text-5xl font-bold text-center mb-2">
          facebook
        </h1>
        <h1 className="text-wrap text-2xl text-darkblack ">
          Facebook helps you connect and share with the people in your life.
        </h1>
      </div>
      <div className="rounded-md container items-center w-full px-4 shadow-lg shadow-darkwhite max-w-[33rem] mt-6 py-8 bg-[#fff] flex flex-col">
        <h1 className="text-black text-lg mb-6 font-semibold">
          Log in to Facebook
        </h1>
        <form
          action=""
          className="flex flex-col gap-4 w-full"
          onSubmit={(e) => {
            e.preventDefault();
            setErrorMessage("");
            const formData = new FormData(e.target);
            const data = Object.fromEntries(formData);
            if (data.username.trim() === "")
              setErrorMessage("Username cannot be blank!");
            else submit(data);
          }}
        >
          <input
            className="bg-[#fff] border-whites px-4 border-[1px] rounded-md w-full h-12 text-black"
            type="text"
            name="username"
            placeholder="Email address or phone number"
          />
          <input
            className="bg-[#fff] border-whites px-4 border-[1px] rounded-md w-full h-12"
            type="password"
            name="password"
            placeholder="Password"
          />
          {errorMessage !== "" && (
            <h1 className="text-red-600 text-lg text-center">{errorMessage}</h1>
          )}
          <button
            className="bg-blue w-full rounded-md py-3 text-white font-bold text-xl hover:brightness-110"
            type="submit"
          >
            Log in
          </button>
        </form>

        <div className="w-full h-1 border-darkwhite mt-6 border-b-[1px]"></div>
        <NavLink
          to="/signup"
          className="bg-green text-lg font-semibold text-[#fff] rounded-md py-2 px-4 mt-5 hover:brightness-110"
        >
          Create new account
        </NavLink>
      </div>
    </div>
  );
};

export default LoginPage;
