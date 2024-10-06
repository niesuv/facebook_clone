import React, { useState } from 'react'

const NavBarIcon = ({children, onClick, icon}) => {
    const [isOpen, setOpen] = useState(false);
    return (
    <div className='relative' onClick={() => {
        setOpen(o => !o);
        onClick();
    }}>
        <i className={'text-blue bg-transparent rounded-full text-3xl ' + icon}></i>
        { isOpen && children}
    </div>
  )
}

export default NavBarIcon