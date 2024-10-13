import Search from "../../svg/search";
import { menu, create } from "../../data/allMenu";
import AllMenuItem from "./AllMenuItem";
const AllMenu = () => {
  const all_menu_group = "w-full mt-3 border-b-1 border-[#ced0d4]";
  const all_menu_group_header = "font-semibold text-base p-[10px_0]"
  return (
    <div className="absolute top-11 right-[-9rem] p-[10px_1rem] w-[630px] h-[88vh] bg-secondary rounded-lg select-none shadow-[2px_-1px_5px_2px_rgba(0,0,0,0.2)]">
      <div className="text-2xl font-bold mb-[10px]">Menu</div>
      <div className="grid grid-cols-3 gap-3 overflow-y-auto h-[95%]">
        <div className="col-span-2 bg-white rounded-md shadow-[0px_1px_2px_rgba(0,0,0,0.2)] p-3">
          <div className="flex items-center gap-1 bg-secondary p-2 rounded-full">
            <Search />
            <input
              className="ml-2 border-none outline-none w-full text-sm bg-transparent"
              type="text"
              placeholder="Search Menu"
            />
          </div>
          <div className="mt-3  border-[#ced0d4]">
            <div className={all_menu_group}>
              <div className={all_menu_group_header}>Social</div>
              {menu.slice(0, 6).map((item, i) => (
              <AllMenuItem
                name={item.name}
                description={item.description}
                icon={item.icon}
                key={i}
              />
            ))}
            </div>
            <div className={all_menu_group}>
              <div className={all_menu_group_header}>Entertainment</div>
              {menu.slice(6, 9).map((item, i) => (
              <AllMenuItem
                name={item.name}
                description={item.description}
                icon={item.icon}
                key={i}
              />
            ))}
            </div>
            <div  className={all_menu_group}>
              <div  className={all_menu_group_header}>Shopping</div>
              {menu.slice(9, 11).map((item, i) => (
              <AllMenuItem
                name={item.name}
                description={item.description}
                icon={item.icon}
                key={i}
              />
            ))}
            </div>
            <div  className={all_menu_group}>
              <div  className={all_menu_group_header}>Personal</div>
              {menu.slice(11, 15).map((item, i) => (
              <AllMenuItem
                name={item.name}
                description={item.description}
                icon={item.icon}
                key={i}
              />
            ))}
            </div>
            <div  className={all_menu_group}>
              <div  className={all_menu_group_header}>Professional</div>
              {menu.slice(15, 17).map((item, i) => (
              <AllMenuItem
                name={item.name}
                description={item.description}
                icon={item.icon}
                key={i}
              />
            ))}
            </div>
            <div  className={all_menu_group}>
              <div  className={all_menu_group_header}>Community Resources</div>
              {menu.slice(17, 21).map((item, i) => (
              <AllMenuItem
                name={item.name}
                description={item.description}
                icon={item.icon}
                key={i}
              />
            ))}
            </div>
            <div  className={all_menu_group}>
              <div  className={all_menu_group_header}>More from Meta</div>
              {menu.slice(21, 23).map((item, i) => (
              <AllMenuItem
                name={item.name}
                description={item.description}
                icon={item.icon}
                key={i}
              />
            ))}
            </div>
          </div>
        </div>
        <div className="col-span-1 bg-secondary rounded-md shadow-[0px_1px_2px_rgba(0,0,0,0.2)] p-3 ">
          <div className="text-xl font-bold mb-[15px]">
            Create
          </div>
          {create.map((item) => (
            <div className="flex items-center font-semibold gap-[10px] text-sm cursor-pointer p-[5px_10px] rounded-sm mb-[2px] hover1">
              <div className="w-10 h-10 rounded-full flex items-center justify-center bg-third ">
                <i className={item.icon}></i>
              </div>
              {item.name}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};
export default AllMenu;
