import React, {useEffect, useState} from 'react';
import { Link } from "react-router-dom";
import axios from 'axios';

function MemberList(props){
   const [data, setData] = useState([]);

    useEffect(() => {
        axios.get('/api/v2/members')
        .then(response => setData(response.data.data))
        .catch(error => console.log(error))
    }, []);

    return(
    <div>
        <h2>회원 목록:</h2>
        <ul>
            {data.map(item=>(
            <li key={item.name}>{item.name}</li>
            ))}
        </ul>
    </div>
    );
}

export default MemberList;