import { Link, NavLink } from "react-router-dom";
import { useRef, useState } from "react";
import Logo from "../../svg/logo";
import Search from "../../svg/search";
import HomeActive from "../../svg/homeActive";
import Friends from "../../svg/friends";
import Watch from "../../svg/watch";
import Market from "../../svg/market";
import Gaming from "../../svg/gaming";
import Menu from "../../svg/menu";
import Messenger from "../../svg/messenger";
import Notifications from "../../svg/notifications";
import AllMenu from "./AllMenu";
import { useSelector } from "react-redux";

const Header = () => {
  const isDarkMode = document.documentElement.classList.contains('dark');
  let color = "#65676b";
  if (isDarkMode)
    color = '#B9BBBE'
  
  const user = useSelector(state => state.user);
  

  const [showAllMenu, setShowAllMenu] = useState(false);
  const middle_icon =
    "relative flex items-center  justify-center w-1/2 h-12 rounded-[10px] cursor-pointer transform -translate-x-[2px] dark:hover:bg-gray";
  const active =
    "border-b-4 border-blue rounded-none h-14 transform translate-x-0";
  return (
    <header className="sticky md:justify-between top-0 h-14 z-10 bg-primary w-full flex dark:border-b-[1px] dark:border-b-gray
     text-primary shadow-[1px_8px_15px_-7px_var(--shadow-2)] dark:bg-black ">
      <div className="flex items-center gap-2.5 p-[5px_1rem] w-1/3">
        <NavLink className="" to="/">
          <div className="w-10 h-10 rounded-full cursor-pointer flex items-center justify-center">
            <Logo />
          </div>
        </NavLink>

        <div className="flex items-center gap-2 dark:bg-gray dark:text-white bg-[#f0f2f5] p-[14px] xl:p-[10px_32px_10px_10px] rounded-full cursor-text ">
          <Search color={color} />
          <input
            className="outline-none border-none bg-transparent  text-[15px] font-inherit transform -translate-y-px hidden xl:flex"
            type="text"
            placeholder="Search Facebook"
          />
        </div>
      </div>
      <div className="hidden items-center gap-[14px]  md:flex w-1/2">
        <NavLink
          to="/"
          className={({ isActive }) =>
            `${middle_icon} ${isActive ? active : ""}`
          }
        >
          <HomeActive />
        </NavLink>
        <NavLink
          to="/friends"
          className={({ isActive }) =>
            `${middle_icon} ${isActive ? active : ""}`
          }
        >
          <Friends color={color} />
        </NavLink>
        <NavLink
          to="/watch"
          className={({ isActive }) =>
            `${middle_icon} ${isActive ? active : ""}`
          }
        >
          <Watch color={color} />
          <div className="absolute top-[3px] right-[30%] bg-[#e41e3f] rounded-full px-[5px] py-[1px] text-[13px] text-white">
            9+
          </div>
        </NavLink>
        <NavLink
          to="/market"
          className={({ isActive }) =>
            `${middle_icon} ${isActive ? active : ""}`
          }
        >
          <Market color={color} />
        </NavLink>
        <NavLink
          to="/gaming"
          className={({ isActive }) =>
            `${middle_icon} ${isActive ? active : ""}`
          }
        >
          <Gaming color={color} />
        </NavLink>
      </div>

      <div className="flex items-center w-1/3 ">
        <div className="flex items-center absolute gap-2 right-2">
          <div className="relative flex rounded-full dark:bg-gray  cursor-pointer w-10 h-10 items-center bg-third justify-center">
            <div
              onClick={() => {
                setShowAllMenu((prev) => !prev);
              }}
            >
              <Menu />
            </div>
            {showAllMenu && <AllMenu />}
          </div>
          <div className="relative flex rounded-full dark:bg-gray dark:fill-white cursor-pointer w-10 h-10 items-center bg-third justify-center">
            <Messenger />
          </div>
          <div className="relative flex rounded-full dark:bg-gray dark:fill-white cursor-pointer w-10 h-10 items-center bg-third justify-center">
            <Notifications />
          </div>
          <NavLink className="mr-4 md:mr-1 flex items-center gap-1 cursor-pointer  border border-darkwhite rounded-full ">
            <img
              className="w-10 h-10 rounded-full"
              src="" alt=""
            />
          </NavLink>
        </div>
      </div>
    </header>
  );
};
export default Header;
