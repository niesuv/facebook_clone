import React from 'react'

export const LoadingIndicator = ({className}) => {
  return (
    <div className='flex justify-center '>
        <span className={"loading loading-spinner text-blue " + className}></span>
    </div>
  )
}
