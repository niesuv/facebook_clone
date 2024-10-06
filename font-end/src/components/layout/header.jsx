import { Link, NavLink } from "react-router-dom";
import Logo from "../../svg/logo";
import Search from "../../svg/search";
import HomeActive from "../../svg/homeActive";
import Friends from "../../svg/friends";
import Watch from "../../svg/watch";
import Market from "../../svg/market";
import Gaming from "../../svg/gaming";

const color = "#65676b";
const Header = () => {
  const middle_icon =
    "relative flex items-center justify-center w-[125px] h-[50px] rounded-[10px] cursor-pointer -translate-x-[2px] hover1";
  const active =
    "border-b-4 border-blue-600 rounded-none h-14 transform translate-x-0";
  return (
    <header className="fixed top-0 h-14 z-[99] bg-primary w-full shadow-[1px_8px_15px_-7px_var(--shadow-2)] grid grid-cols-3 text-primary">
      <div className="flex items-center gap-2.5 p-[5px_1rem]">
        <NavLink className="" to="/">
          <div className="w-10 h-10 rounded-full cursor-pointer flex items-center justify-center">
            <Logo />
          </div>
        </NavLink>
        <div className="flex items-center gap-2 bg-[#f0f2f5] p-[10px_32px_10px_10px] rounded-full cursor-text md:max-w-[1040px]:w-10 md:max-w-[1040px]:h-10 md:max-w-[1040px]:p-0 md:max-w-[1040px]:justify-center">
          <Search color={color} />
          <input
            className="outline-none border-none bg-transparent text-[15px] font-inherit transform -translate-y-px"
            type="text"
            placeholder="Search Facebook"
          />
        </div>
      </div>
      <div className="flex items-center gap-[14px] translate-x-[3px]">
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
          <div className="absolute top-[3px] right-[1.2rem] bg-[#e41e3f] rounded-full px-[5px] py-[1px] text-[13px] text-white">
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
      <div className="flex absolute right-[8px] top-1/2 transform -translate-y-1/2">
      <NavLink>

      </NavLink>
      
      </div>
    </header>
  );
};
export default Header;
