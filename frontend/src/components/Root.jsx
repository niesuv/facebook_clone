
import { Outlet, useFetcher, useLocation, useNavigate } from "react-router-dom";
import Header from "./NavBar/Header";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { login } from "../store/user_slice";


const Root = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const user =  useSelector(state => state.user);
  const location = useLocation()
  const showHeader = ["/login", '/signup'].indexOf(location.pathname) === -1;
  
  // useEffect(() => {
  //   const u = JSON.parse(localStorage.getItem('user'))
  //   if (u)
  //     dispatch(login(u))
  // },[dispatch])
  
  useEffect(() => {
    if (user.id === '' && showHeader)
      navigate("/login")
  }, [navigate, user])

  
  return (
        <div className="flex flex-col w-full dark:bg-darkblack h-full">
          {showHeader && <Header></Header>}
          <Outlet></Outlet>
        </div>
  );
};

export default Root;
