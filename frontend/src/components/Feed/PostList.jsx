import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { loadNewData } from '../../store/post_slice';
import { getNewPost } from '../../util/http';
import PostItem from './PostItem';
import LoadingIndicator from '../LoadingIndicator';

let load = true

const PostList = () => {
    const dispatch = useDispatch();
    const data = useSelector(state => state.post);
    useEffect(() => {
        if (load)
            dispatch(loadNewData(getNewPost));
        load = false;
    }, [dispatch])
  return (
    <div className='h-full  md:max-w-[59%] w-full flex flex-col gap-4 pt-4'>
        {data.post && data.post.map(p => <PostItem post={p} key={p.postId}></PostItem>)}
        {data.isLoading && <LoadingIndicator className={"w-12"}></LoadingIndicator>}
    </div>
  )
}

export default PostList