import React from 'react';
import { Link } from 'react-router-dom';

const Main=(props)=>{
    return(
        <>
        <h3>안녕하세요. 메인페이지입니다.</h3>
        <ul>
            <Link to="/signup"><li>회원가입</li></Link>
            <Link to="/api/v2/members"><li>회원목록</li></Link>
            <Link to="/api/items/register"><li>아이템 등록</li></Link>
            <Link to="/api/items"><li>아이템 목록</li></Link>
        </ul>
        </>
    );
};

export default Main;