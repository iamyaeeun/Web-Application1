import React, {useEffect, useState} from 'react';
import { Link } from "react-router-dom";
import axios from 'axios';

function ItemList(props){
   const [data, setData] = useState([]);

    useEffect(() => {
        axios.get('/api/items')
        .then(response => setData(response.data.data))
        .catch(error => console.log(error))
    }, []);

    return(
    <div>
        <h2>상품 목록:</h2>
        <ul>
            {data.map(item=>(
            <li key={item.name}> 상품명: {item.name} 가격: {item.price} 수량: {item.stockQuantity}</li>
            ))}
        </ul>
    </div>
    );
}

export default ItemList;