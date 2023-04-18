import React,{useState} from "react";
import {Link} from "react-router-dom";

function ItemRegister(){
    const [name,setName]=useState("");
    const [price,setPrice]=useState("");
    const [stockQuantity,setStockQuantity]=useState("");

    const handleSubmit=(event)=>{

        event.preventDefault();

        const data={
            name:name,
            price:price,
            stockQuantity:stockQuantity
        };

        fetch('/api/items/register',{
        method:'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
    };

    const handleChangeName=(event)=>{
            setName(event.target.value);
        };

    const handleChangePrice=(event)=>{
        setPrice(event.target.value);
    };

    const handleChangeStockQuantity=(event)=>{
        setStockQuantity(event.target.value);
    };

    return(
    <form onSubmit={handleSubmit}>
        <div>
            <label htmlFor="name">상품명:</label>
            <input type="text" id="name" value={name} onChange={handleChangeName} />
        </div>

        <div>
            <label htmlFor="price">가격:</label>
            <input type="text" id="price" value={price} onChange={handleChangePrice} />
        </div>
        <div>
            <label htmlFor="stockQuantity">수량:</label>
            <input type="text" id="stockQuantity" value={stockQuantity} onChange={handleChangeStockQuantity} />
        </div>

        <button>등록</button>
    </form>
    )
}

export default ItemRegister;