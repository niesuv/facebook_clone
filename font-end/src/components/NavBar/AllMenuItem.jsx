export default function AllMenuItem({ name, description, icon }) {
  return (
    <div className="flex items-center w-full mb-1 p-1 cursor-pointer rounded-sm hover1">
      <img className="w-9 h-9 mr-[10px]" src={`/left/${icon}.png`} alt="" />
      <div className="flex flex-col">
        <span className="text-sm font-medium">{name}</span>
        <span className="text-xs text-[#65676b]">{description}</span>
      </div>
    </div>
  );
}
