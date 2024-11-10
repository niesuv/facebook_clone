import React from 'react'

const ErrorMessage = ({message}) => {
  return (
    <p className='text-red-500 -mt-4 text-md text-start w-full'>{message}</p>
  )
}

export default ErrorMessage